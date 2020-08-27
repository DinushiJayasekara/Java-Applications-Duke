
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            if(((currIndex) - startIndex) % 3 == 0) {
                break;
            } else {
                currIndex = dna.indexOf(stopCodon, (currIndex + 3));
            }
        }
        return currIndex;
    }
    
    public void testFindStopCodon() {
        Part1 test = new Part1();
        
        String testDna = "AAWJUNJSMATGHUJKOINJKATTJIK";
        int result = test.findStopCodon(testDna, 0, "ATT");
        System.out.println(result);
        
        testDna = "AAWJHUJKOINJKATTJIK";
        result = test.findStopCodon(testDna, 0, "ATT");
        System.out.println(result);
        
        testDna = "AAWJHUJKOINJKATJIK";
        result = test.findStopCodon(testDna, 0, "ATT");
        System.out.println(result);
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        } else {
            int findTAA = findStopCodon(dna, startIndex, "TAA");
            int findTAG = findStopCodon(dna, startIndex, "TAG");
            int findTGA = findStopCodon(dna, startIndex, "TGA");
            int min = 0;
            /*int tem = Math.min(findTAA, findTAG);
            min = Math.min(tem, findTGA);*/
            if(findTAA != -1 || (findTGA != -1 && findTGA < findTAA)) {
                min = findTAA;
            } else {
                min = findTGA;
            }
            
            if(min == -1 || (findTAG != -1 && findTAG < min)){
                min = findTAG;
            }
            
            if(min == -1){
                return "";
            } else {
                return dna.substring(startIndex, min + 3);
            }
            
        }
    }
    
    public void testFindGene() {
        Part1 test = new Part1();
        
        String testDna = "BNGHIJKOI";
        String result = test.findGene(testDna, 0);
        System.out.println(testDna + " - " + result);
        
        testDna = "ERDATGCGFBHJTAAERD";
        result = test.findGene(testDna, 0);
        System.out.println(testDna + " - " + result);
        
        testDna = "ERDATGCGFTGATAAERDTAGQED";
        result = test.findGene(testDna, 0);
        System.out.println(testDna + " - " + result);
        
        testDna = "ERDATGWCGFTAABHWJTAAERDWTAGQED";
        result = test.findGene(testDna, 0);
        System.out.println(testDna + " - " + result);
        
        testDna = "AACCCTAACCCAATGDAATDATAADAA";
        result = test.findGene(testDna, 0);
        System.out.println(testDna + " - " + result);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public void testPrintAllGenes() {
        Part1 test = new Part1();
        
        String testDna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("*********************\n");
        test.printAllGenes(testDna);
        
        testDna = "";
        System.out.println("*********************\n");
        test.printAllGenes(testDna);
        
        testDna = "DFCATGWERCFGTAGATGERDWERTAAATG";
        System.out.println("*********************\n");
        test.printAllGenes(testDna);
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return geneList;
    }
    
    public void testGetAllGenes(String dna) {
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public void test(){
        testGetAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testGetAllGenes("");
        testGetAllGenes("DFCATGWERCFGTAGATGERDWERTAAATG");
    }
}

