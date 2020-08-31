
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    
    private double minMag; 
    private double maxMag; 
    
    public MagnitudeFilter(double minMag, double maxMag) { 
        this.minMag = minMag;
        this.maxMag = maxMag;
    } 
    
    public boolean satisfies(QuakeEntry qe) { 
        if((qe.getMagnitude() >= minMag) && (qe.getMagnitude() <= maxMag)) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String getName() {
        return "Magnitude";
    }

}
