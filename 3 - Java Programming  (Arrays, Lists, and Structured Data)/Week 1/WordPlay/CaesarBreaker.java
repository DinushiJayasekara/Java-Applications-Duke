
/**
 * Write a description of Arrays here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CaesarBreaker {
    
    public void Simulate(int rolls) {
        Random rand = new Random();
        int [] counts = new int [13];
        
        for(int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1 + d2] += 1;
        }
        
        for(int k = 2; k <= 12; k++) {
            System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k]/rolls);
        }
    }

    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;
        
        for(int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if(d1 + d2 == 2) {
                twos += 1;
            } else if(d1 +d2 == 12) {
                twelves += 1;
            }
        }
        
        System.out.println("2's=\t" + twos + "\t" + 100.0 * twos/rolls);
        System.out.println("12's=\t" + twelves + "\t" + 100.0 * twelves/rolls);
    }
    
    public String[] getCommon() {
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : resource.words()) {
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public int indexOf(String[] list, String word) {
        for(int k = 0; k < list.length; k++) {
            if(list[k].equals(word)) {
                return k;
            }
        }
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts) {
        for(String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if(index != -1) {
                counts[index] += 1;
            }
        }
    }
    
    public void countShakespeare() {
        String [] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        String [] common = getCommon();
        int [] counts = new int[common.length];
        
        for(int k = 0; k < plays.length; k++) {
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource, common, counts);
            System.out.println("done with " + plays[k]);
        }
        
        for(int k = 0; k < common.length; k++) {
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
    
    public int[] countLetters(String message) {
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
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++) {
            if(vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println();
        System.out.println("Maxdex " + maxDex);
        System.out.println("Dkey " + dkey);
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public String halfOfString(String message, int start) {
        String everyOther = "";
        for(int i = start; i < message.length(); i += 2) {
            everyOther += message.charAt(i);
        }
        return everyOther;
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstString = halfOfString(encrypted, 0);
        String secString = halfOfString(encrypted, 1);
        
        int key1 = getKey(firstString);
        int key2 = getKey(secString);
        
        System.out.println("Key 1: " + key1 + " Key 2: " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    
    public void testDecrypt() {
        //FileResource fr = new FileResource();
        System.out.println(decrypt("Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth"));
        //System.out.println(decrypt("WZIJK CVXZFE RKKRTB VRJK WCREB"));
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource("data/PracticeBreakingCaesarData/mysteryTwoKeysPractice.txt");
        String message = fr.asString();
        System.out.println(decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
    }
}
