import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for(int i = whichSlice; i < message.length(); i+=totalSlices) {
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = (cc.getKey(slice));
        }
        return key;
    }

    public void breakVigenere() {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> langDic = new HashMap<String, HashSet<String>>();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource("dictionaries/" + f.getName());
            System.out.println("Reading... " + f.getName());
            HashSet<String> words = readDictionary(fr);
            langDic.put(f.getName(), words);
        }
        FileResource fr = new FileResource();
        String msg = fr.asString();
        breakForAllLangs(msg, langDic);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for(String line : fr.lines()) {
            line = line.toLowerCase();
            words.add(line);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int validWords = 0;
        String [] splitWords = message.split("\\W+");
        for(String word : splitWords) {
            word = word.toLowerCase();
            if(dictionary.contains(word)) {
                validWords++;
            }
        }
        return validWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char common) {
        VigenereCipher vc;
        int max = 0;
        int [] accurate = null;
        for(int i = 1; i < 100; i++) {
            int [] key = tryKeyLength(encrypted, i, common);
            vc = new VigenereCipher(key);
            String testDecrypt = vc.decrypt(encrypted);
            if(countWords(testDecrypt, dictionary) > max) {
                max = countWords(testDecrypt, dictionary);
                accurate = key;
            }
            
        }
        
        vc = new VigenereCipher(accurate);
        System.out.print("\nKey: ");
        for(int i = 0; i < accurate.length; i++) {
            System.out.print(accurate[i] + ", ");
        }
        System.out.print("\nKey Length: " + (accurate.length));
        System.out.print("\nValid Words: " + (countWords(vc.decrypt(encrypted), dictionary)));
        System.out.println();
        return vc.decrypt(encrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostCommon = 'e';
        HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
        for(String word : dictionary) {
            int count[] = new int[dictionary.size()]; 
            int len = word.length(); 
            for (int i=0; i<len; i++) 
                count[word.charAt(i)]++; 
           
            int max = -1;
            char result = ' ';
           
            for (int i = 0; i < len; i++) { 
                if (max < count[word.charAt(i)]) { 
                    max = count[word.charAt(i)]; 
                    result = word.charAt(i); 
                } 
            } 
           
            if(!letterCount.containsKey(result)) {
                letterCount.put(result, 1);
            } else {
                letterCount.put(result, letterCount.get(result) + 1);
            }
        }
        
        int max = 0;
        for(char c : letterCount.keySet()) {
            if(letterCount.get(c) > max) {
                max = letterCount.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for(String lang : languages.keySet()) {
            System.out.println("\n--------------------------------------------------------");
            System.out.println("Language: " + lang);
            char common = mostCommonCharIn(languages.get(lang));
            System.out.println("Most Common Char: " + common);
            String decrypt = breakForLanguage(encrypted, languages.get(lang), common);
            System.out.println("\n" + decrypt);
        }
    }
    
}
