
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3 {

    public void processGenes(StorageResource sr) {
        int longStrings = 0;
        int highCgRatio = 0;
        int longestGene = 0;
        Part2 CG = new Part2();
        
        for(String s : sr.data()){
            if(s.length() > 60){
                longStrings++;
            }
        }
        System.out.println("\nNumber of genes: " + sr.size() + " (69)\n");
        System.out.println("Strings that are longer than 60 chars: " + longStrings + " (23)\n");
        
        for(String s : sr.data()){
            if(CG.cgRatio(s) > 0.35){
                highCgRatio++;
            }
            if(s.length() > longestGene){
                longestGene = s.length();
            }
        }
        System.out.println("Strings whose C-G-ratio is higher than 0.35: " + highCgRatio + " (40)");
       
        System.out.println("\nLength of the longest gene: " + longestGene + "\n");
    }
    
    
    public void testProcessGenes() {
        Part1 find = new Part1();
        Part2 count = new Part2();
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        
        StorageResource geneList = new StorageResource();
        
        int startIndex = 0;
        int c = 0;
        while(true){
            String gene = find.findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            System.out.println(c + " " + gene);
            c++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        processGenes(geneList);
        
        System.out.println("CTG Count: " + (count.countCTG(dna)));
    }
    
}
