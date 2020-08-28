
/**
 * Write a description of TestCaesarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher2 {

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
    
    private String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher2 cc2 = new CaesarCipher2(26 - dkey);
        System.out.println("\nBreak Cipher: \n" + "Key: " + dkey);
        return cc2.encrypt(input);
    }
    
    public void simpleTests() {
        CaesarCipher2 cc2 = new CaesarCipher2(15);
        
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc2.encrypt(message);
        System.out.println("Encrypted: \n" + message + "\n" + encrypted);
        
        String decrypted = cc2.decrypt(encrypted, 15);
        System.out.println("Decrypted: \n" + "\n" + encrypted + "\n" + decrypted);
        
        String breakCipher = breakCaesarCipher(encrypted);
        System.out.println(encrypted + "\n" + breakCipher);
    }
    
}
