
/**
 * Write a description of CaesarCipherTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwoKeys {
    
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    
    public CaesarCipherTwoKeys(int key1, int key2) {
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        String answer = "";
        for(int i = 0; i < input.length(); i++) {
            if(Character.isLowerCase(input.charAt(i))) {
                alphabet = alphabet.toLowerCase();
                shiftedAlphabet1 = shiftedAlphabet1.toLowerCase();
                shiftedAlphabet2 = shiftedAlphabet2.toLowerCase();
            } else {
                alphabet = alphabet.toUpperCase();
                shiftedAlphabet1 = shiftedAlphabet1.toUpperCase();
                shiftedAlphabet2 = shiftedAlphabet2.toUpperCase();
            }
            int index = alphabet.indexOf(input.charAt(i));
            if(i % 2 == 0) {
                if(index != -1) {
                    answer += shiftedAlphabet1.charAt(index);
                } else {
                    answer += input.charAt(i);
                }
            } else {
                if(index != -1) {
                    answer += shiftedAlphabet2.charAt(index);
                } else {
                    answer += input.charAt(i);
                }
            }
        }
        return answer;
    }
    
    public String decrypt(String input, int key1, int key2) {
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
    
}
