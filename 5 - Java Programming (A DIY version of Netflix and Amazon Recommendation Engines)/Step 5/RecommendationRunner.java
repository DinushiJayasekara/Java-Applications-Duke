
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender{

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> itemsToRate = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int r = rand.nextInt(movies.size());
            String title = movies.get(r);
            if (!itemsToRate.contains(title)) {
                itemsToRate.add(title);
            }
        }
        return itemsToRate;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, 10, 3);
        
        if (similarRatings.size() == 0) {
            System.out.println("<br><h1 style=\"background: #bc9cd3; color:#4f0c7f; padding: 2%;\">Oops! No matching movies were found</h1>");
        } else {
            System.out.println("<br><h1 style=\"background: #4f0c7f; color:#cbb1dd; padding: 2%;\">We found " + similarRatings.size() + " movies for you!</h1><br>");
            System.out.println("<br><div><table style=\"text-align: center; width: 100%\">");
            System.out.println("<tr style=\"background: #cbb1dd;color: #5c168e;\">");
            System.out.println("<th style=\"padding: 1% 0; border-bottom: 3px solid; border-top: 3px solid;\">#</th>");
            System.out.println("<th style=\"border-bottom: 3px solid; border-top: 3px solid;\">Poster</th>");
            System.out.println("<th style=\"border-bottom: 3px solid; border-top: 3px solid;\">Title</th>");
            System.out.println("<th style=\"border-bottom: 3px solid; border-top: 3px solid;\">Genre</th>");
            System.out.println("<th style=\"border-bottom: 3px solid; border-top: 3px solid;\">Year</th>");
            System.out.println("<th style=\"border-bottom: 3px solid; border-top: 3px solid;\">Time</th>");
            System.out.println("</tr><tr>");
            for(int i=0; i < similarRatings.size(); i++) {
                int num = i + 1;
                String movieId = similarRatings.get(i).getItem();
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\">"+ num +"</td>");
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\"><img height=\"50%\" style=\"margin: 8% 0;\" src=");
                System.out.println("\""+MovieDatabase.getPoster(movieId)+"\"");
                System.out.println("/></td>");
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\">"+MovieDatabase.getTitle(movieId)+"</td>");
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\">"+MovieDatabase.getGenres(movieId)+"</td>");
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\">"+MovieDatabase.getYear(movieId)+"</td>");
                System.out.println("<td style=\"border-bottom: 1px solid #cbb1dd;\">"+MovieDatabase.getMinutes(movieId)+" Minutes"+"</td>");
                System.out.println("</tr><tr>");
            }   
            System.out.println("</table></div>");
        }
    }
    
}
