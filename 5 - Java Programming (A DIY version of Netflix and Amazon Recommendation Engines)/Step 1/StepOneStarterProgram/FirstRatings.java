
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName) {
        FileResource fr = new FileResource("data/" + fileName);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(CSVRecord movie : parser){
            String id = movie.get("id");
            String title = movie.get("title");
            String year = movie.get("year");
            String genres = movie.get("genre");
            String director = movie.get("director");
            String country = movie.get("country");
            String poster = movie.get("poster");
            int minutes = Integer.parseInt(movie.get("minutes"));
            Movie movieDetails = new Movie(id, title, year, genres, director, country, poster, minutes);
            movieList.add(movieDetails);
        }
        return movieList;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
        int comedies = 0;
        int longMovies = 0;
        for(Movie m : movieList) {
            //System.out.println(m.toString());
            if(m.getGenres().contains("Comedy")) {
                comedies += 1;
            }
            if(m.getMinutes() > 150) {
                longMovies += 1;
            }
        }
        System.out.println("Number of Movies: " + movieList.size());
        System.out.println("Number of Comedies: " + comedies);
        System.out.println("Number of Long Movies: " + longMovies);
        
        HashMap<String, Integer> directedMovies = new HashMap<String, Integer>();
        for(Movie m : movieList) {
            String [] directors = (m.getDirector()).split("\\s*,\\s*");
            for(String d : directors) {
                if(!directedMovies.containsKey(d)) {
                    directedMovies.put(d, 1);
                } else {
                    directedMovies.put(d, directedMovies.get(d) + 1);
                }
            }
        }
        int maxDir = 0;
        String maxName = "";
        for(String dir : directedMovies.keySet()) {
            if(directedMovies.get(dir) > maxDir) {
                maxDir = directedMovies.get(dir);
                maxName = dir;
            }
        }
        System.out.print("Most Directed: " + maxDir + " movies by ");
        for(String dir : directedMovies.keySet()) {
            if(directedMovies.get(dir) == maxDir) {
                System.out.print(dir + ", ");
            }
        }
        System.out.println();
    }
    
    public ArrayList<Rater> loadRaters(String fileName) {
        FileResource fr = new FileResource("data/" + fileName);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        for(CSVRecord rater : parser) {
            String id = rater.get("rater_id");
            Rater raterDetails = new Rater(id);
            raterList.add(raterDetails);
        }
        return raterList;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        HashMap<String, ArrayList<String>> raters = new HashMap<String, ArrayList<String>>();
        FileResource fr = new FileResource("data/ratings.csv");
        for(int i = 0; i < raterList.size(); i++) {
            CSVParser parser = fr.getCSVParser();
            ArrayList<String> ratings = new ArrayList<String>();
            for(CSVRecord rater : parser) {
                String id = rater.get("rater_id");
                if((raterList.get(i).getID()).equals(id)) {
                    String addRate = rater.get("movie_id") + " - " + rater.get("rating") + "\n";
                    ratings.add(addRate);
                }
            }
            if(!raters.containsKey(raterList.get(i).getID())) {
                raters.put(raterList.get(i).getID(), ratings);
            }
        }
        
        System.out.println("Number of Raters: " + raters.size() + "\n");
        for(String r : raters.keySet()) {
            ArrayList<String> ratingsMovies = raters.get(r);
            System.out.println("Rater Id: " + r);
            System.out.println("Number of Ratings: " + ratingsMovies.size());
            for(String rm : ratingsMovies) {
                System.out.print(rm);
            }
            System.out.println();
        }
        
        String raterId = "193";
        for(String r : raters.keySet()) {
            if(r.equals(raterId)){
                ArrayList<String> ratingsMovies = raters.get(r);
                System.out.println("Number of Ratings for Rater " + raterId + ": " + ratingsMovies.size());
            }
        }
        
        int maxRatings = 0;
        for(String r : raters.keySet()) {
            ArrayList<String> ratingsMovies = raters.get(r);
            if(ratingsMovies.size() > maxRatings) {
                maxRatings = ratingsMovies.size();
            }
        }
        System.out.println("\nMaximum No. of Ratings: " + maxRatings);
        
        System.out.println("\nRaters with Maximum No. of Ratings: ");
        for(String r : raters.keySet()) {
            ArrayList<String> ratingsMovies = raters.get(r);
            if(ratingsMovies.size() == maxRatings){
                System.out.println(r);
            }
        }
        
        String movieId = "1798709";
        int movieRaters = 0;
        for(String r : raters.keySet()) {
            ArrayList<String> ratingsMovies = raters.get(r);
            for(String rm : ratingsMovies) {
                if(rm.indexOf(movieId) != -1) {
                    movieRaters++;
                }
            }
        }
        System.out.println("\nMovie " + movieId + " rated by: " + movieRaters + " raters");
        
        ArrayList<String> movies = new ArrayList<String>();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rater : parser) {
            String movie = rater.get("movie_id");
            if(!movies.contains(movie)) {
                movies.add(movie);
            }
        }
        System.out.println("\nNo. of Different Movies: " + movies.size());
    }
    
}
