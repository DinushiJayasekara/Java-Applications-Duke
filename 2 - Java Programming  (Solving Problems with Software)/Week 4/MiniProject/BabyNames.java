
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        int totalNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if(record.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames++;
            } else {
                totalGirls += numBorn;
                totalGirlNames++;
            }
            totalNames++;
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Girl Births = " + totalGirls);
        System.out.println("Total Boy Births = " + totalBoys);
        System.out.println("Total Names = " + totalNames);
        System.out.println("Total Girl Names = " + totalGirlNames);
        System.out.println("Total Boy Names = " + totalBoyNames);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = -1;
        int totalGirlNames = 0;
        int rowCount = 1;
        FileResource fr = new FileResource("E:/Windows/Courses/Coursera/Java Programming and Software Engineering Fundamentals/2 - Java Programming  (Solving Problems with Software)/Week 4/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals("F")) {
                totalGirlNames++;
            }
        }
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if((record.get(0).equals(name)) && (record.get(1).equals(gender))) {
                if(gender.equals("M")) {
                    if(rowCount > totalGirlNames) {
                        rank = rowCount - totalGirlNames;
                    }
                } else {
                    rank = rowCount;
                }
            }
            rowCount++;
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        int rowCount = 1;
        FileResource fr = new FileResource("E:/Windows/Courses/Coursera/Java Programming and Software Engineering Fundamentals/2 - Java Programming  (Solving Problems with Software)/Week 4/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals("F")) {
                totalGirlNames++;
            } else {
                totalBoyNames++;
            }
        }
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(gender.equals("F")) {
                if(rank < totalGirlNames && rowCount == rank) {
                    name = record.get(0);
                }
            } else {
                if(rank < totalBoyNames && (rowCount == (totalGirlNames + rank))) {
                    name = record.get(0);
                }
            }
            rowCount++;
        }
        return name;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rankInYear = getRank(year, name, gender);
        String nameInNewYear = getName(newYear, rankInYear, gender);
        
        System.out.println(name + " born in " + year + " would be " + nameInNewYear + " if she was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = 0;
        int rankInYear = 0;
        String fileName = "";
        int yearIndex = 0;
        int highest = -1;
        int highestYear = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            fileName = f.getName();
            yearIndex = fileName.indexOf("yob") + 3;
            year = Integer.parseInt(fileName.substring(yearIndex, yearIndex + 4));
            rankInYear = getRank(year, name, gender);
            if(rankInYear > -1) {
                if(rankInYear > highest) {
                    highest = year;
                }
            }
        }
        
        for(File f : dr.selectedFiles()) {
            fileName = f.getName();
            yearIndex = fileName.indexOf("yob") + 3;
            year = Integer.parseInt(fileName.substring(yearIndex, yearIndex + 4));
            rankInYear = getRank(year, name, gender);
            if(rankInYear > -1) {
                if(rankInYear < getRank(highest, name, gender)) {
                    highestYear = year;
                }
            }
        }
        return highestYear;
    }
    
    public double getAverageRank(String name, String gender) {
        int year = 0;
        int rankInYear = 0;
        String fileName = "";
        int yearIndex = 0;
        double total = 0;
        int fileCount = 0;
        double avg = -1;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            fileName = f.getName();
            yearIndex = fileName.indexOf("yob") + 3;
            year = Integer.parseInt(fileName.substring(yearIndex, yearIndex + 4));
            rankInYear = getRank(year, name, gender);
            if(rankInYear > -1) {
                total += rankInYear;
            }
            fileCount++;
        }
        
        avg = total/fileCount;
        
        return avg;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int rank = getRank(year, name, gender);
        int totalGirlNames = 0;
        int rowCount = 1;
        int higherRankedBirths = 0;
        FileResource fr = new FileResource("E:/Windows/Courses/Coursera/Java Programming and Software Engineering Fundamentals/2 - Java Programming  (Solving Problems with Software)/Week 4/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals("F")) {
                totalGirlNames++;
            }
        }
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(gender.equals("M")) {
                if((rowCount > totalGirlNames) && (rowCount <= (rank + totalGirlNames))) {
                    if(!(record.get(0).equals(name))) {
                        higherRankedBirths += Integer.parseInt(record.get(2));
                    }
                }
            } else {
                if((rowCount <= totalGirlNames)  && (rowCount <= rank)) {
                    if(!record.get(0).equals(name)) {
                        higherRankedBirths += Integer.parseInt(record.get(2));
                    }
                }
            }
            rowCount++;
        }
        return higherRankedBirths;
    }
    
    public void tester() {
        //FileResource fr = new FileResource();
        //totalBirths(fr);
        
        //System.out.println("Rank: " + (getRank(1971, "Frank", "M")));
        
        //System.out.println("Name: " + (getName(1980, 350, "F")));
        
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        
        System.out.println("Highest Ranking: " + (yearOfHighestRank("Genevieve", "F")));
        
        //System.out.println("Average Rank: " + (getAverageRank("Robert", "M")));
        
        //System.out.println("Higher Ranked Births: " + (getTotalBirthsRankedHigher(1990, "Drew", "M")));
    }
    
}
