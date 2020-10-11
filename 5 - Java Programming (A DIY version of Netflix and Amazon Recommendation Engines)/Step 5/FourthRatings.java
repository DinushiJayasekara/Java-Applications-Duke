
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsFile) {
        RaterDatabase.initialize(ratingsFile);
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double total = 0;
        double avg = 0.0;
        ArrayList<Rater> ratersList = new ArrayList<Rater>();
        for(Rater rater : RaterDatabase.getRaters()) {
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
        if(ratersList.size() > minimalRaters) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        ArrayList<String> meItems = me.getItemsRated();
        ArrayList<String> rItems = r.getItemsRated();
        for(String meItem : meItems) {
            if(rItems.contains(meItem)) {
                double ratingMe = me.getRating(meItem);
                double ratingR = r.getRating(meItem);
                dotProduct += ((ratingMe - 5) * (ratingR - 5));
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : allRaters) {
            String rId = r.getID();
            if(!(rId.equals(id))) {
                double dotProduct = dotProduct(me, r);
                if(dotProduct >= 0) {
                    Rating rating = new Rating(rId, dotProduct);
                    similarities.add(rating);
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieId : movies) {
            double weightedRating = 0;
            int count = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating rating = similarRaters.get(i);
                String ratedId = rating.getItem();
                double ratingValue = rating.getValue();
                
                Rater rater = RaterDatabase.getRater(ratedId);
                
                if (rater.hasRating(movieId)) {
                    double value = rater.getRating(movieId) * ratingValue;
                    weightedRating += value;
                    count++;
                }
            }
            double weightedAvgRating = weightedRating/count;
            if(count >= minimalRaters) {
                similarRatings.add(new Rating(movieId, weightedAvgRating));
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieId : movies) {
            double weightedRating = 0;
            int count = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating rating = similarRaters.get(i);
                String ratedId = rating.getItem();
                double ratingValue = rating.getValue();
                
                Rater rater = RaterDatabase.getRater(ratedId);
                
                if (rater.hasRating(movieId)) {
                    double value = rater.getRating(movieId) * ratingValue;
                    weightedRating += value;
                    count++;
                }
            }
            double weightedAvgRating = weightedRating/count;
            if(count >= minimalRaters) {
                similarRatings.add(new Rating(movieId, weightedAvgRating));
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }
    
}
