
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        double largest = 0;
        int index = 0;
        for(int i = 0; i < quakeData.size(); i++) {
            QuakeEntry qe = quakeData.get(i);
            if(qe.getMagnitude() > largest) {
                largest = qe.getMagnitude();
                index = i;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        double largest = 0;
        for(int i = 0; i < howMany; i++) {
            int index = indexOfLargest(quakeData);
            answer.add(quakeData.get(index));
            quakeData.remove(index);
        }
        return answer;
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        //Location jakarta  = new Location(-6.211,106.845);

        int index = indexOfLargest(list);
        System.out.println("Index is " + index + "\n" + (list.get(index)));
        
        ArrayList<QuakeEntry> listQuakes = getLargest(list, 50);
        for(QuakeEntry qe : listQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+listQuakes.size()+" quakes that match the criteria");
    }
    
}
