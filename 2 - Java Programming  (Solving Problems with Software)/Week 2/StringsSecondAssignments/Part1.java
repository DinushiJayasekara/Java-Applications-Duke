
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        if((currIndex - startIndex) % 3 == 0) {
            return currIndex;
        } else {
            return dna.length();
        }
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
            if(findTAA != -1 && findTAA < findTAG){
                min = findTAA;
                if(findTGA != -1 && findTGA < min){
                    min = findTGA;
                }
            } else {
                min = findTAG;
            }
            if(min == dna.length()){
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
        
        testDna = "ERDATGCGFBHJERDQED";
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
    
    public void testprintAllGenes() {
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
    
}
