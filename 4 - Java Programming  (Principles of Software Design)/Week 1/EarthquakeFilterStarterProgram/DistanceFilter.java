
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{

    private double distMax; 
    private Location from;
    
    public DistanceFilter(double distMax, Location from) { 
        this.distMax = distMax;
        this.from = from;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        if((qe.getLocation().distanceTo(from) / 1000) <= distMax) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String getName() {
        return "Distance";
    }
    
}
