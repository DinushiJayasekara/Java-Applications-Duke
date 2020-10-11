
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> avgRating = fourthRatings.getAverageRatings(35);
        Collections.sort(avgRating);
        System.out.println("\nFound " + avgRating.size() + " movies with minimum raters");
        for(Rating r : avgRating) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        YearAfterFilter yearFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> result = fourthRatings.getAverageRatingsByFilter(8, allFilters);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet year,genre criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> result = fourthRatings.getSimilarRatings("71", 20, 5);
        System.out.println("\nFound " + result.size() + " similar movies");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        GenreFilter genreFilter = new GenreFilter("Mystery");
        
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, genreFilter);
        System.out.println("\nFound " + result.size() + " similar movies which meet genre criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        DirectorFilter directorFilter = new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter("120", 10, 2, directorFilter);
        System.out.println("\nFound " + result.size() + " similar movies which meet director criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        GenreFilter genreFilter = new GenreFilter("Drama");
        MinutesFilter minutesFilter = new MinutesFilter(80, 160);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, allFilters);
        System.out.println("\nFound " + result.size() + " similar movies which meet genre,minutes criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        System.out.println("\nRead data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        YearAfterFilter yearFilter = new YearAfterFilter(1975);
        MinutesFilter minutesFilter = new MinutesFilter(70, 200);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, allFilters);
        System.out.println("\nFound " + result.size() + " similar movies which meet year,minutes criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }
    
}
