
/**
 * Write a description of TestCaesarCipherTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwoKeys {

    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for(int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] vals) {
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++) {
            if(vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    private String halfOfString(String message, int start) {
        String everyOther = "";
        for(int i = start; i < message.length(); i += 2) {
            everyOther += message.charAt(i);
        }
        return everyOther;
    }
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    private String breakCaesarCipher(String input) {
        String firstString = halfOfString(input, 0);
        String secString = halfOfString(input, 1);
        
        int key1 = getKey(firstString);
        int key2 = getKey(secString);
        
        System.out.println("Break Cipher: \n" + "Key 1: " + key1 + " Key 2: " + key2);
        
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(21, 8);
        
        String message = fr.asString();
        //message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted: \n" + message + "\n" + encrypted);
        
        //encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = cc.decrypt(encrypted, 14, 24);
        System.out.println("\nDecrypted: \n" + encrypted + "\n" + decrypted);
        
        //encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String breakCipher = breakCaesarCipher(message);
        System.out.println("\n" + encrypted  + "\n" + breakCipher);
    }
    
}
