
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String q1 = qe1.getInfo();
        String q2 = qe2.getInfo();
        if((q1.substring(q1.lastIndexOf(" ") + 1)).equals(q2.substring(q2.lastIndexOf(" ") + 1))) {
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        } else {
            return ((q1.substring(q1.lastIndexOf(" ") + 1)).compareTo(q2.substring(q2.lastIndexOf(" ") + 1)));
        }
    }
}
