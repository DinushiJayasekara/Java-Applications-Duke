
/**
 * Write a description of EfficientMarkovModel2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private int nChar;
    private HashMap<String, ArrayList<String>> buildHash;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        nChar = n;
        buildHash = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
	
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
	printHashMapInfo();
    }
	
    public String toString() {
        return "Markov model of order " + nChar;
    }

    public void buildMap() {
	int start = 0;
	do {
	    String key = myText.substring(start, start + nChar);
	    if (!buildHash.containsKey(key)) {
		ArrayList<String> follows = new ArrayList<String>();
		int index = 0;
		do {
		    index = myText.indexOf(key, index);
		    if (index != -1) {
			if(key.length() + index < myText.length()) {
			    follows.add(myText.substring(index + key.length(), index + key.length() + 1));
			    index = index + 1;
			} else {
			    break;
			}
                    } else {
                        break;
                    }			
		} while (index != -1);
		buildHash.put(key, follows);
            }
            start += 1;
        } while ((start + nChar) <= myText.length());
    }

    public ArrayList<String> getFollows(String key) {		
	return buildHash.get(key);		
    }
	
    public String getRandomText(int numChars){
	if (myText == null){
	    return "";
	}
	int index = myRandom.nextInt(myText.length() - nChar);
	String key = myText.substring(index, index + nChar);		
	StringBuilder sb = new StringBuilder();
	sb.append(key);
	for(int k=0; k < numChars; k++) {
	   ArrayList<String> follows = getFollows(key);
	   if (follows.size() == 0) {
	       break;
	   }
	   index = myRandom.nextInt(follows.size());
	   key = key.substring(1) + follows.get(index);
	   sb.append(follows.get(index));
	}			
	return sb.toString();
    }
    
    public void printHashMapInfo() {
        System.out.println("\nPrint the HashMap");
        int max = 0;
        for(String k : buildHash.keySet()) {
            //System.out.println(k + " " + buildHash.get(k));
            if(buildHash.get(k).size() > max) {
                max = buildHash.get(k).size();
            }
        }
        System.out.println("Number of keys: " + buildHash.size());
        System.out.println("Largest ArrayList Size: " + max);
        System.out.print("Keys with max size: ");
        for(String k : buildHash.keySet()) {
            if((buildHash.get(k)).size() == max) {
                System.out.print(k + " ");
            }
        }
        System.out.println();
    }
    
}
