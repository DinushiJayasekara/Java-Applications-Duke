
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part2 {
    
    public void test(){
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(countCTG("ATGCTGASDERTCTGCTG"));
    }
    
    public double cgRatio(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C'){
                count++;
            }
            if(dna.charAt(i) == 'G'){
                count++;
            }
        }
        int len = dna.length();
        return ((double)count)/len;
    }
    
    public int countCTG(String dna) {
        int index = dna.indexOf("CTG");
        int count = 0;
        while(true){
            if(index != -1){
                count++;
            } else {
                break;
            }
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }
    
}
