
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> avgRating = thirdRatings.getAverageRatings(35);
        Collections.sort(avgRating);
        System.out.println("\nFound " + avgRating.size() + " movies with minimum raters");
        for(Rating r : avgRating) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(20, yearFilter);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet year criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        GenreFilter genreFilter = new GenreFilter("Comedy");
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(20, genreFilter);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet genre criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        MinutesFilter minutesFilter = new MinutesFilter(105, 135);
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(5, minutesFilter);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet minutes criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (Time: " + MovieDatabase.getMinutes(r.getItem()) + ")");
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        DirectorFilter directorFilter = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(4, directorFilter);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet director criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        YearAfterFilter yearFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(8, allFilters);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet year,genre criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("\nRead data for " + thirdRatings.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        MinutesFilter minutesFilter = new MinutesFilter(90, 180);
        DirectorFilter directorFilter = new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorFilter);
        
        ArrayList<Rating> result = thirdRatings.getAverageRatingsByFilter(3, allFilters);
        Collections.sort(result);
        System.out.println("\nFound " + result.size() + " movies which meet director,minutes criteria");
        for(Rating r : result) {
            System.out.println(r.getValue() + " - " + MovieDatabase.getTitle(r.getItem()) + " (Time: " + MovieDatabase.getMinutes(r.getItem()) + ")");
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
}
