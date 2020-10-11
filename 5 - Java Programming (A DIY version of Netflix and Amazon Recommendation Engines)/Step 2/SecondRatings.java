
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    HashMap<String, ArrayList<String>> raters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        for(Movie movie : myMovies) {
            double avg = getAverageByID(movie.getID(), minimalRaters);
            if(avg != 0.0) {
                Rating rating = new Rating(movie.getID(), avg);
                avgRating.add(rating);
            }
        }
        return avgRating;
    }
    
    public String getTitle(String id) {
        String ret = "NO SUCH MOVIE :(";
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                ret = movie.getTitle();
            }
        }
        return ret;
    }
    
    public String getID(String title) {
        String ret = "NO SUCH TITLE :(";
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                ret = movie.getID();
            }
        }
        return ret;
    }
    
}
