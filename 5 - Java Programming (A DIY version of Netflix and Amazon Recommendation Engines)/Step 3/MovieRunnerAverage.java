
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of Movies: " + secondRatings.getMovieSize());
        System.out.println("Number of Raters: " + secondRatings.getRaterSize());
        
        System.out.println();
        
        ArrayList<Rating> avgRating = secondRatings.getAverageRatings(12);
        Collections.sort(avgRating);
        for(Rating r : avgRating) {
            System.out.println(r.getValue() + " - " + secondRatings.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String title = "Vacation";
        String id = secondRatings.getID(title);
        System.out.println("\n" + title + " - " + secondRatings.getAverageByID(id, 0));
    }
    
}
