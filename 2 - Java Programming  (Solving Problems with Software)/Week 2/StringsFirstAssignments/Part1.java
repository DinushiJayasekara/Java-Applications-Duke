
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public String findSimpleGene(String dna) {
        String gene = "";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if(startIndex == -1 || stopIndex == -1){
            gene = "Missing gene codon";
        } else {
            gene = dna.substring(startIndex, stopIndex + 3);
            if(gene.length() % 3 == 0) {
                return gene;
            } else {
                gene = "Invalid gene length";
            }
        }
        return gene;
    }
    
    public void testSimpleGene(){
        Part1 findGene = new Part1();
        
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        String result = findGene.findSimpleGene(dna);
        System.out.println(result);
        
        dna = "VGTDXSATGRE";
        result = findGene.findSimpleGene(dna);
        System.out.println(result);
        
        dna = "QWERTYUI";
        result = findGene.findSimpleGene(dna);
        System.out.println(result);
        
        dna = "ASDFGHATGQAZWSXEDFTAACVF";
        result = findGene.findSimpleGene(dna);
        System.out.println(result);
        
        dna = "UHJATGREYJUTAAKI";
        result = findGene.findSimpleGene(dna);
        System.out.println(result);
    }
    
}
