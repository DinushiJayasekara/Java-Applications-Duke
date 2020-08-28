
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    
    public void testCaesarCipher() {
        System.out.println("\nCaesar Cipher Encrypt\n----------------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher CCipher = new CaesarCipher(6);
        String output = CCipher.encrypt(message);
        System.out.println(output);
    }
    
    public void testCaesarCracker() {
        System.out.println("\nCaesar Cracker\n----------------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCracker CCracker = new CaesarCracker();
        String output = CCracker.decrypt(message);
        System.out.println(output);
    }
    
    public void testVigenereCipher() {
        System.out.println("\nVigenere Cipher\n----------------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        int [] key = {17, 14, 12, 4};          //rome
        VigenereCipher VCipher = new VigenereCipher(key);
        String output = VCipher.encrypt(message);
        System.out.println(output);
    }
    
    public void testVigenereBreaker1() {
        System.out.println("\n\nVigenere Breaker 1\n----------------------------------");
        FileResource fr = new FileResource();
        String msg = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        
        int[] key = vb.tryKeyLength(msg, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(msg));
        System.out.print("\nKey: ");
        for(int i = 0; i < key.length; i++) {
            System.out.print(key[i] + ", ");
        }
    }
    
    public void testVigenereBreaker2() {
        System.out.println("\n\nVigenere Breaker 2\n----------------------------------");
        FileResource fr = new FileResource();
        String msg = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        
        FileResource fr2 = new FileResource();
        String enc = fr2.asString();
        HashSet<String> dic = vb.readDictionary(fr2);
        System.out.println("\n\n-------------------------------------------");
        System.out.println(vb.breakForLanguage(msg, dic, 'e'));
    }
    
    public void testVigenereBreaker3() {
        System.out.println("\n\nVigenere Breaker 3\n----------------------------------");
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
}
