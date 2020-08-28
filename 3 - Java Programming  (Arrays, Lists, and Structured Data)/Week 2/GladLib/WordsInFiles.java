
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> fileNames;
    private String directory = "QuizGladLibsData/";
    private DirectoryResource dr;
    
    public WordsInFiles() {
        fileNames = new HashMap<String, ArrayList<String>>();
        dr = new DirectoryResource();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(directory + f.getName());
       
        for(String word : fr.words()) {
            //word = word.toLowerCase();
            ArrayList<String> file = new ArrayList<String>();
            if(!fileNames.keySet().contains(word)) {
                file.add(f.getName());
                fileNames.put(word, file);
            } else {
                file = fileNames.get(word);
                if(!file.contains(f.getName())) {
                    file.add(f.getName());
                }
                fileNames.put(word, file);
            }
        }
    }
    
    public void buildWordFileMap() {
        fileNames.clear();
        
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
        for(String w : fileNames.keySet()) {
            System.out.print(w + " - ");
            for(String s : fileNames.get(w)) {
                System.out.print(s + " | ");
            }
            System.out.println();
        }
    }
    
    public int maxNumber() {
        int max = 0;
        int num = 0;
        
        for(String w : fileNames.keySet()) {
            num = (fileNames.get(w)).size();
            if(num > max) {
                max = num;
            }
        }
        System.out.println("\nMaximum " + max + "\n");
        return max;
    }
    
    public ArrayList wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<String>();
        
        for(String w : fileNames.keySet()) {
            if((fileNames.get(w)).size() == number) {
                result.add(w);
            }
        }
        return result;
    }
    
    public void printFilesIn(String word) {
        for(String w : fileNames.keySet()) {
            if(w.equals(word)) {
                System.out.print(w + " - ");
                for(String s : fileNames.get(w)) {
                    System.out.print(s + " | ");
                }
                System.out.println();
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        
        ArrayList<String> words = wordsInNumFiles(max);
        System.out.println("words appear in " + max + " files: " + wordsInNumFiles(max).size());
        
        for(String w : words) {
            printFilesIn(w);
        }
        
        int count = 0;
        for(String w : fileNames.keySet()) {
            if((fileNames.get(w)).size() == 4) {
                count++;
            }
        }
        
        System.out.println("Count: " + count);
        
        printFilesIn("tree");
    }
    
}
