
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        
        MarkovWord markovWord = new MarkovWord(5); 
        markovWord.setRandom(844);
        runModel(markovWord, st, 200); 
    } 
    
    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        EfficientMarkovWord m = new EfficientMarkovWord(2);
        m.setTraining(st);
        m.setRandom(65);
        System.out.println("running with " + m.toString());
        String t= m.getRandomText(50);
        printOut(t);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        long nano1 = System.nanoTime();
        EfficientMarkovWord m = new EfficientMarkovWord(2);
        runModel(m, st, 100, 42);
        long nano2 = System.nanoTime();
        System.out.println("Efficient Time: " + ((nano2 - nano1) * 0.000000001) + "\n");
        
        nano1 = System.nanoTime();
        MarkovWord markovWord = new MarkovWord(2); 
        runModel(markovWord, st, 100, 42);
            
        nano2 = System.nanoTime();
        System.out.println("Time: " + ((nano2 - nano1) * 0.000000001) + "\n");
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
