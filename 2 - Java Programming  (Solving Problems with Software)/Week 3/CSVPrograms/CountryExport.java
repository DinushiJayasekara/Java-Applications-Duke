
/**
 * Write a description of CountryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExport {

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String result = countryInfo(parser, "Nauru");
        System.out.println(result + "\n");
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        System.out.println("\n" + numberOfExporters(parser, "cocoa") + "\n");
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        String answer = "";
        for(CSVRecord record: parser){
            String checkCountry = record.get("Country");
            if(checkCountry.equals(country)){
                System.out.println();
                answer = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return answer;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record: parser){
            String check = record.get("Exports");
            if(check.contains(exportItem1) && check.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for(CSVRecord record: parser){
            String check = record.get("Exports");
            if(check.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        int count = 0;
        for(CSVRecord record: parser){
            String check = record.get("Value (dollars)");
            if(check.length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
}
