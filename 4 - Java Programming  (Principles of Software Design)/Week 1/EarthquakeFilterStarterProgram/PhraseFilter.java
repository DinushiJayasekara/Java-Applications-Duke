
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{

    private String where; 
    private String phrase;
    
    public PhraseFilter(String where, String phrase) { 
        this.where = where;
        this.phrase = phrase;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        String info = qe.getInfo();
            if(where.equals("start")) {
                if(info.indexOf(phrase) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if(where.equals("end")) {
                if(info.endsWith(phrase)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if(info.indexOf(phrase) != -1) {
                    return true;
                } else {
                    return false;
                }
            }
    } 
    
    public String getName() {
        return "Phrase";
    }
    
}
