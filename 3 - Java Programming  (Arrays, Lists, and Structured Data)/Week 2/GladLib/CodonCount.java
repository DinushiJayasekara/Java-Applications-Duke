
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CodonCount {

    private HashMap<String, Integer> dnaCount;
    
    public CodonCount() {
        dnaCount = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        dnaCount.clear();
        for(int i = start; i < dna.length(); i+=3) {
            if((dna.length() - i) > 3) {
                String code = dna.substring(i, i + 3);
                if(!dnaCount.keySet().contains(code)) {
                    dnaCount.put(code, 1);
                } else {
                    dnaCount.put(code, dnaCount.get(code) + 1);
                }
            }
        }
    }
    
    private String getMostCommonCodon() {
        int max = 0;
        String ans = "";
        for(String w : dnaCount.keySet()) {
            if(dnaCount.get(w) > max) {
                max = dnaCount.get(w);
                ans = w;
            }
        }
        return ans;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Size " + dnaCount.size());
        for(String w : dnaCount.keySet()) {
            if(dnaCount.get(w) >= start && dnaCount.get(w) <= end) {
                System.out.println(w + " - " + dnaCount.get(w));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        
        System.out.println("\nReading frame starting with 0");
        buildCodonMap(0, dna);
        String common = getMostCommonCodon();
        System.out.println("Most common codon is " + common + " with count " + dnaCount.get(common));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(0, 7);
        
        System.out.println("\nReading frame starting with 1");
        buildCodonMap(1, dna);
        common = getMostCommonCodon();
        System.out.println("Most common codon is " + common + " with count " + dnaCount.get(common));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 6);
        
        System.out.println("\nReading frame starting with 2");
        buildCodonMap(2, dna);
        common = getMostCommonCodon();
        System.out.println("Most common codon is " + common + " with count " + dnaCount.get(common));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(0, 5);
    }
    
}
