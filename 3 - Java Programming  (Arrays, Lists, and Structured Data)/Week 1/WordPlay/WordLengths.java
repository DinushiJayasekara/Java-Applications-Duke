
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int [] counts) {
        int len = 0;
        System.out.println("Note this file has words that are:");
        for(String word : resource.words()) {
            word = word.toLowerCase();
            len = word.length();
            if(!Character.isLetter(word.charAt(0))) {
                len--;
            }
            if(!Character.isLetter(word.charAt(word.length() - 1))) {
                len--;
            }
            for(int i = 0; i < counts.length; i++) {
                if(len == counts.length) {
                    counts[counts.length - 1] += 1;
                }
                if(len == (i + 1)) {
                    counts[i] += 1;
                }
            }
        }
        
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] > 0)
            System.out.println(counts[i] + " words of length " + (i + 1));
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource("data/QuizCryptographyData/manywords.txt");
        int [] count = new int [100];
        System.out.println();
        countWordLengths(fr, count);
        System.out.println();
        System.out.println("Max index: " + indexOfMax(count));
    }
    
    public int indexOfMax(int [] values) {
        int maxIndex = 0;
        for(int i = 0; i < values.length; i++) {
            if(values[i] > maxIndex) {
                maxIndex = i;
            }
        }
         maxIndex += 1;
        return maxIndex;
    }
}
