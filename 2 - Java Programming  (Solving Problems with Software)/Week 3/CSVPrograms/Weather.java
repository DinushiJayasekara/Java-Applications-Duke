
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for(CSVRecord record : parser) {
            if(coldest == null) {
                coldest = record;
            } else {
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                if(currentTemp < coldestTemp) {
                    coldest = record;
                }
            }
        }
        return coldest;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser =  fr.getCSVParser();
        
        CSVRecord result = coldestHourInFile(parser);
        System.out.println(result.get("TemperatureF") + " Farenheit at " + result.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord coldest = null;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser =  fr.getCSVParser();
            
            if(coldestFile == null) {
                coldestFile = f;
            } else {
                for(CSVRecord record : parser) {
                    if(coldest == null) {
                        coldest = record;
                    } else {
                        double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                        double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                        if(currentTemp != -9999) {
                            if(currentTemp < coldestTemp) {
                                coldest = record;
                                coldestFile = f;
                            }
                        }
                    }
                }                                               
            }
        }
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were ");
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser =  fr.getCSVParser();
        for(CSVRecord record : parser) {
            String date = (record.get("DateUTC")).substring(0,10);
            String time = (record.get("DateUTC")).substring(10);
            System.out.println(date + time + " - " + record.get("TemperatureF"));
        }
        return coldestFile.getName();
    }
    
    public void testFileWithColdestTemperature() {
        fileWithColdestTemperature();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord humidity = null;
        for(CSVRecord record : parser) {
            if(humidity == null) {
                humidity = record;
            } else {
                if(!(record.get("Humidity")).equals("N/A")) {
                    double currentHumidity = Double.parseDouble(record.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(humidity.get("Humidity"));
                    if(currentHumidity < lowestHumidity) {
                        humidity = record;
                    }
                }
            }
        }
        return humidity;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser =  fr.getCSVParser();
        
        CSVRecord result = lowestHumidityInFile(parser);
        String date = (result.get("DateUTC")).substring(0,10);
        String time = (result.get("DateUTC")).substring(10);
        System.out.println("Lowest Humidity was " + result.get("Humidity") + " at " + date + time);
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        File lowestHumidityFile = null;
        CSVRecord lowest = null;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser =  fr.getCSVParser();
            
            if(lowestHumidityFile == null) {
                lowestHumidityFile = f;
            } else {
                for(CSVRecord record : parser) {
                    if(lowest == null) {
                        lowest = record;
                    } else {
                        if(!(record.get("Humidity").equals("N/A"))) {
                            double currentTemp = Double.parseDouble(record.get("Humidity"));
                            double coldestTemp = Double.parseDouble(lowest.get("Humidity"));
                            if(currentTemp < coldestTemp) {
                                lowest = record;
                                lowestHumidityFile = f;
                            }
                        }
                    }
                }                                               
            }
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord result = lowestHumidityInManyFiles();
        String date = (result.get("DateUTC")).substring(0,10);
        String time = (result.get("DateUTC")).substring(10);
        System.out.println("Lowest Humidity was " + result.get("Humidity") + " at " + date + time);
    }
    
    public Double averageTemperatureInFile(CSVParser parser) {
        double total = 0;
        double count = 0;
        for(CSVRecord record : parser) {
           total += Double.parseDouble(record.get("TemperatureF"));
           count++;
        }
        return total/count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser =  fr.getCSVParser();
        double result = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + result);
    }
    
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0;
        double count = 0;
        double avg = 0;
        for(CSVRecord record : parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if(humidity >= value) {
                total += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        avg = total/count;
        return avg;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser =  fr.getCSVParser();
        double result = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(Double.isNaN(result)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when high humidity is " + result);
        }
    }
    
}
