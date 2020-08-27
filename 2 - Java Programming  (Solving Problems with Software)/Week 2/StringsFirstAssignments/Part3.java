
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb) {
        int secIndex;
        boolean answer = false;
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex > -1){
            secIndex = stringb.indexOf(stringa,firstIndex + 1);
            if(secIndex > -1) {
                answer = true;
            } else {
                answer = false;
            }
        } 
        return answer;
    }
    
    public String lastPart(String stringa, String stringb) {
        int secIndex;
        String answer = stringb;
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex > -1){
            answer = stringb.substring(firstIndex + stringa.length());
        }
        return "The part of the string after " + stringa + " in " + stringb + " is " + answer + ".";
    }
    
    public void testing(){
        Part3 two = new Part3();
        
        boolean result = two.twoOccurences("by", "A story by Abby Long");
        System.out.println(result);
        
        result = two.twoOccurences("atg", "ctgtatgta");
        System.out.println(result);
        
        result = two.twoOccurences("a", "banana");
        System.out.println(result);
        
        System.out.println(two.lastPart("an", "banana"));
        
        System.out.println(two.lastPart("zoo", "forest"));
      
    }
}
