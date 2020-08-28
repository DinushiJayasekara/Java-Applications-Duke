import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> existingWords;
    private ArrayList<String> usedWords;
    
    private Random myRandom;
    
    private int replacedWords = 0;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedWords = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", 
        "animal", "timeframe", "verb", "fruit"};
        
        existingWords = new ArrayList<String>();
        ArrayList<String> file = new ArrayList<String>();
        for(String s : categories) {
            file = readIt(source + "/" + s + ".txt");
            myMap.put(s, file);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for(String w : myMap.keySet()) {
            if(label.equals(w)) {
                usedWords.add(w);
                return randomFrom(myMap.get(w));
            }
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(existingWords.indexOf(sub) != -1) {
            sub = getSubstitute(w.substring(first+1,last));
            replacedWords++;
        }
        existingWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        existingWords.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\n" + replacedWords + " words were replaced");
        totalWordsInMap();
        totalWordsConsidered();
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for(ArrayList x : myMap.values()) {
            total += x.size();
        }
        System.out.println("\nTotal Words in Map: " + total);
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        
        for(String s : usedWords) {
            if(myMap.get(s) != null)
            total += (myMap.get(s)).size();
        }
        System.out.println("\nTotal Words Considered: " + total);
        return total;
    }

}
