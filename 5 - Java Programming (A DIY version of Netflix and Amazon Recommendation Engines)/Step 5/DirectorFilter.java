
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter{

    private String myDirectors;
    
    public DirectorFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String directors[] = myDirectors.split(",");
        for(String director : directors) {
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
    
}
