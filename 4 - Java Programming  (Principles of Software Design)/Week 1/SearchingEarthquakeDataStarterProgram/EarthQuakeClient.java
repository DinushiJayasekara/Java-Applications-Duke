import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if(qe.getMagnitude() >= magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            Location loc = qe.getLocation();
            if((loc.distanceTo(from) / 1000) <= distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if(depth < maxDepth && depth > minDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            if(where.equals("start")) {
                if(info.indexOf(phrase) == 0) {
                    answer.add(qe);
                }
            } else if(where.equals("end")) {
                if(info.endsWith(phrase)) {
                    answer.add(qe);
                }
            } else if(where.equals("any")) {
                if(info.indexOf(phrase) != -1) {
                    answer.add(qe);
                }
            }
            
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBigQuakes = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : listBigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+listBigQuakes.size()+" quakes that match the criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> listQuakes = filterByDistanceFrom(list, 1000, city);
        for(QuakeEntry qe : listQuakes) {
            System.out.println(qe.getInfo());
        }
        System.out.println("Found "+listQuakes.size()+" quakes that match the criteria");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listQuakes = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry qe : listQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+listQuakes.size()+" quakes that match the criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listQuakes = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe : listQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+listQuakes.size()+" quakes that match the criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
