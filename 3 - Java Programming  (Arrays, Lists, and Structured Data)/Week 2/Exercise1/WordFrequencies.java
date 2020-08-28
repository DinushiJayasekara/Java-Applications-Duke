
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        
        for(String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int max = 0;
        String word = "";
        for(int i = 0; i < myWords.size(); i++){
            if(myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                word = myWords.get(i);
            }
        }
        return myWords.indexOf(word);
    }
    
    public void tester() {
        findUnique();
        System.out.println("No. of unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " - " + myFreqs.get(i));
        }
        
        int val = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myFreqs.get(val) + " - " + myWords.get(val));
    }
    
}
