
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    HashMap<String, ArrayList<String>> raters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double total = 0;
        double avg = 0.0;
        ArrayList<Rater> ratersList = new ArrayList<Rater>();
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                ratersList.add(rater);
            }
        }
        for(Rater rater : ratersList) {
            if(ratersList.size() >= minimalRaters) {
                total += rater.getRating(id);
            } else {
                return 0.0;
            }
        }
        if(ratersList.size() > 0) {
            avg = total/ratersList.size();
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieId : movies) {
            double avg = getAverageByID(movieId, minimalRaters);
            if(avg != 0.0) {
                Rating rating = new Rating(movieId, avg);
                avgRating.add(rating);
            }
        }
        return avgRating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> meetCriteria = new ArrayList<Rating>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(filterCriteria);
        for(String movieId : movieIds) {
            double avg = getAverageByID(movieId, minimalRaters);
            if(avg > 0) {
                Rating rating = new Rating(movieId, avg);
                meetCriteria.add(rating);
            }
        }
        return meetCriteria;
    }
    
}
