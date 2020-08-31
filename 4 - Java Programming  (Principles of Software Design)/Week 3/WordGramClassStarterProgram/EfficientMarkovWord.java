
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> buildHash;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        buildHash = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public String toString() {
        return "Efficient Markov Order " + myOrder;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
	printHashMapInfo();
    }
    
    public void buildMap() {
        for(int i = 0; i < myText.length - myOrder; i++) {
            WordGram w = new WordGram(myText, i, myOrder);
            String next = myText[i + myOrder];
            if(buildHash.containsKey(w)) {
                buildHash.get(w).add(next);
            } else {
               ArrayList<String> list = new ArrayList<String>();
               list.add(next);
               buildHash.put(w, list);
            }
        }
    }
    
    public String getRandomText(int numWords) {
        if (myText == null){
	    return "";
	}
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords - myOrder; k++){
            if(getFollows(key).isEmpty()) {
                System.out.println("ddd");
            }
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty() || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String [] words, WordGram target, int start) {
        for(int i = start; i < words.length - myOrder; i++) {
            WordGram wg = new WordGram(words, i, myOrder);
            if(wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram key) {
        return buildHash.get(key);
    }
    
    public void printHashMapInfo() {
        System.out.println("\nPrint the HashMap");
        int max = 0;
        for(WordGram k : buildHash.keySet()) {
            //System.out.println(k + " " + buildHash.get(k));
            if(buildHash.get(k).size() > max) {
                max = buildHash.get(k).size();
            }
        }
        System.out.println("Number of keys: " + buildHash.size());
        System.out.println("Largest ArrayList Size: " + max);
        System.out.print("Keys with max size: ");
        for(WordGram k : buildHash.keySet()) {
            if((buildHash.get(k)).size() == max) {
                System.out.print(k + " ");
            }
        }
        System.out.println();
    }

}
