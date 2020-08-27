
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String stringa, String stringb) {
        int index = 0;
        int count = 0;
        while(true){
            if(stringb.indexOf(stringa, index) == -1){
                break;
            }
            count++;
            index = stringb.indexOf(stringa, index) + stringa.length();
        }
        return count;
    }
    
    public void testHowMany() {
        Part2 test = new Part2();
        
        System.out.println(test.howMany("GAA", "ATGAACGAATTGAATC"));
        
        System.out.println(test.howMany("AA", "ATAAAA"));
    }

}
