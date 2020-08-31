
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       for (int i = 0; i< 70; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i,qmin);
            in.set(maxIdx,qi);
        }
        
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {       
        for(int i = 0; i < (quakeData.size() - 1); i++) {
            QuakeEntry temp = quakeData.get(i);
            if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
        
        for(int i = 0; i < (quakeData.size()); i++) {
            //System.out.println(quakeData.get(i));
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < (in.size() - 1); i++) {
            System.out.println("Printing Quakes after pass " + i);
            onePassBubbleSort(in, i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i = 0; i < (quakes.size() - 1); i++) {
            QuakeEntry temp = quakes.get(i);
            if((quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude())) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int i = 0;
        while(i < (in.size() - 1)) {
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass " + i);
            if(checkInSortedOrder(in)) {
                System.out.println("\n" + (i + 1) + " passes were needed");
                break;
            }
            i++;
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
    		System.out.println("Number of passes: " + i);
    		break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        //System.out.println("\nRead data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/ 
        
        /*System.out.println("\nRead data for "+list.size()+" quakes");    
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/ 
        
        //System.out.println("\nRead data for "+list.size()+" quakes");
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
