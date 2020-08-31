
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;
import java.util.*;

public class Tester {

    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println(follows);
        System.out.println(follows.size());
    }
    
}
