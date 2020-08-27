
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
            String gene = "";
            int startIndex = dna.indexOf(startCodon);
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            if(startIndex == -1 || stopIndex == -1){
                gene = "Missing gene codon";
            } else {
                gene = dna.substring(startIndex, stopIndex + 3);
                if(gene.length() % 3 == 0) {
                    if(dna.equals(dna.toUpperCase())){
                        gene = gene.toUpperCase();
                    } else {
                        gene = gene.toLowerCase();
                    } 
                } else {
                    gene = "Invalid gene length";
                }
            }
            return gene;
    }
    
    public void testSimpleGene(){
        Part2 findGene = new Part2();
        
        String dna = "ASDEFRCDTAAQW";
        String result = findGene.findSimpleGene(dna,"ATG","TAA");
        System.out.println(result);
        
        dna = "VGTDXSATGRE";
        result = findGene.findSimpleGene(dna,"ATG","TAA");
        System.out.println(result);
        
        dna = "QWERTYUI";
        result = findGene.findSimpleGene(dna,"ATG","TAA");
        System.out.println(result);
        
        dna = "ASDFGHATGQAZWSXEDFTAACVF";
        result = findGene.findSimpleGene(dna,"ATG","TAA");
        System.out.println(result);
        
        dna = "UHJATGREYJUTAAKI";
        result = findGene.findSimpleGene(dna,"ATG","TAA");
        System.out.println(result);
        
        dna = "abcatghijkiltaanjk";
        result = findGene.findSimpleGene(dna,"atg","taa");
        System.out.println(result);
    }
    
}
