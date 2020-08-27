
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    
    public void findYoutube() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String line : ur.lines()) {
            //System.out.println(line);
            if(line.indexOf("href") > -1) {
                if(line.indexOf("\"") > -1){
                    String link = line.substring(line.indexOf("\""), line.lastIndexOf("\"")-1);
                    String lowerLink = link.toLowerCase();
                    if(lowerLink.indexOf("youtube.com") > -1){
                        link = line.substring(line.indexOf("http"), line.lastIndexOf(">")-1);
                        System.out.println(link);
                    }
                }
            }
        }
    }
    
    public void testing(){
        Part4 youtube = new Part4();
        
        youtube.findYoutube();
      
    }
    
}
