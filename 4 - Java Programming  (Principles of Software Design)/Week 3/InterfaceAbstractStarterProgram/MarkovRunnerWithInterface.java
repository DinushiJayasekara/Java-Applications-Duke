
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 4);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 5);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 6);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 7);
        
        EfficientMarkovModel ef = new EfficientMarkovModel(5);
        runModel(ef, st, size, 531);

    }
    
    public void testHashMap() {
        EfficientMarkovModel m = new EfficientMarkovModel(2);
        m.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        m.setRandom(42);
        System.out.println("running with " + m.toString());
        for(int k=0; k < 3; k++){
            String st= m.getRandomText(50);
            printOut(st);
        }
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        long nano1 = System.nanoTime();
        EfficientMarkovModel ef = new EfficientMarkovModel(2);
        runModel(ef, st, 1000, 42);
        long nano2 = System.nanoTime();
        System.out.println("Efficient Time: " + ((nano2 - nano1) * 0.000000001) + "\n");
        
        nano1 = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, 1000, 42);
            
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
