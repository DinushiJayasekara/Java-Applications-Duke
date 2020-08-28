
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        String compute = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newString = compute.substring(key) + compute.substring(0, key);
        String answer = "";
        for(int i = 0; i < input.length(); i++) {
            if(Character.isLowerCase(input.charAt(i))) {
                compute = compute.toLowerCase();
                newString = newString.toLowerCase();
            } else {
                compute = compute.toUpperCase();
                newString = newString.toUpperCase();
            }
            int index = compute.indexOf(input.charAt(i));
            if(index != -1) {
                answer += newString.charAt(index);
            } else {
                answer += input.charAt(i);
            }
        }
        return answer;
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String compute = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newString1 = compute.substring(key1) + compute.substring(0, key1);
        String newString2 = compute.substring(key2) + compute.substring(0, key2);
        String answer = "";
        for(int i = 0; i < input.length(); i++) {
            if(Character.isLowerCase(input.charAt(i))) {
                compute = compute.toLowerCase();
                newString1 = newString1.toLowerCase();
                newString2 = newString2.toLowerCase();
            } else {
                compute = compute.toUpperCase();
                newString1 = newString1.toUpperCase();
                newString2 = newString2.toUpperCase();
            }
            int index = compute.indexOf(input.charAt(i));
            if(i % 2 == 0) {
                if(index != -1) {
                    answer += newString1.charAt(index);
                } else {
                    answer += input.charAt(i);
                }
            } else {
                if(index != -1) {
                    answer += newString2.charAt(index);
                } else {
                    answer += input.charAt(i);
                }
            }
        }
        return answer;
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("Key: " + key + " Message: " + message + "Encrypted: " + encrypted);
        
        key = 17;
        encrypted = encrypt(message, key);
        System.out.println("Key: " + key + " Message: " + message + "Encrypted: " + encrypted);
        
        encrypted = encryptTwoKeys("First Legion", 23, 17);
        System.out.println("Encrypted with 2 keys: " + encrypted);
        
        key = 15;
        message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        encrypted = encrypt(message, key);
        System.out.println("Key: " + key + " Message: " + message + "Encrypted: " + encrypted);
        
        key = 15;
        message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        encrypted = encrypt(message, key);
        System.out.println("Key: " + key + " Message: " + message + "Encrypted: " + encrypted);
        
        encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println("Encrypted with 2 keys: " + encrypted);
        
        encrypted = encryptTwoKeys("abcdefghijklmnopqrstuvwxyz", 20, 2);
        System.out.println("abcdefghijklmnopqrstuvwxyz\n" + encrypted);
    }
    
}
