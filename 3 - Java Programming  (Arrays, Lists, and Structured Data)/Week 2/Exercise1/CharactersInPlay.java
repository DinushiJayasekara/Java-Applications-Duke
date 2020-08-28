
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {

    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            freqs.add(1);
        } else {
            int value = freqs.get(index);
            freqs.set(index, value + 1);
        }
    }
    
    private void findAllCharacters() {  
        FileResource fr = new FileResource();
        
        for(String line : fr.lines()) {
            int lineIndex = line.indexOf(".");
            if(lineIndex != -1) {
                String possibleName = line.substring(0, lineIndex);
                update(possibleName);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters with more than " + num1 + " parts: ");
        for(int i = 0; i < names.size(); i++){
            if(freqs.get(i) >= num1 && freqs.get(i) <= num2) {
                System.out.println(names.get(i) + " - " + freqs.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        System.out.println("Main Characters");
        for(int i = 0; i < names.size(); i++){
            if(freqs.get(i) > 5) {
                System.out.println(names.get(i) + " - " + freqs.get(i));
            }
        }
        
        charactersWithNumParts(10, 15);
    }
    
}
