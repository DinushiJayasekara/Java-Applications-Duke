
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        if((currIndex - startIndex) % 3 == 0) {
            return currIndex;
        } else {
            return dna.length();
        }
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
    
    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            count++;
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        Part3 test = new Part3();
        
        System.out.println(test.countGenes("ATGTAAGATGCCCTAGT"));
    }
    
}
