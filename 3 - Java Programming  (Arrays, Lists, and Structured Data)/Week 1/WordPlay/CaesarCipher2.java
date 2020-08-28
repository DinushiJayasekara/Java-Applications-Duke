
/**
 * Write a description of CaesarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher2 {
    
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaesarCipher2(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input) {
        String answer = "";
        for(int i = 0; i < input.length(); i++) {
            if(Character.isLowerCase(input.charAt(i))) {
                alphabet = alphabet.toLowerCase();
                shiftedAlphabet = shiftedAlphabet.toLowerCase();
            } else {
                alphabet = alphabet.toUpperCase();
                shiftedAlphabet = shiftedAlphabet.toUpperCase();
            }
            int index = alphabet.indexOf(input.charAt(i));
            if(index != -1) {
                answer += shiftedAlphabet.charAt(index);
            } else {
                answer += input.charAt(i);
            }
        }
        return answer;
    }
    
    public String decrypt(String input, int key) {
        CaesarCipher2 cc = new CaesarCipher2(26 - key);
        return cc.encrypt(input);
    }
    
}