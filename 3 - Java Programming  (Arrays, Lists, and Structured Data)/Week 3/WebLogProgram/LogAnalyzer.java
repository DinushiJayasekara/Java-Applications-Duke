
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private WebLogParser wlp;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         wlp =  new WebLogParser();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         LogEntry le;
         for(String line : fr.lines()) {
            le = wlp.parseEntry(line);
            records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(!unique.contains(ip)) {
                 unique.add(ip);
             }
         }
         System.out.println("There are " + unique.size() + " unique IP addresses");
         return unique.size();
     }
     
     public void printAllHigherThanNum(int num) {
         System.out.println("\nEntries with status code higher than " + num);
         for(LogEntry le : records) {
             int status = le.getStatusCode();
             if(status > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> dates = new ArrayList<String>();
         for(LogEntry le : records) {
             String date = ((le.getAccessTime()).toString()).substring(4, 10);
             String ip = le.getIpAddress();
             if(date.equals(someday) && (!dates.contains(ip))) {
                 dates.add(ip);
             }
         }
         System.out.println("\nUnique IP visits on " + someday);
         System.out.println("Number of unique IP visits: " + dates.size());
         for(String ip : dates) {
             System.out.println(ip);
         }
         return dates;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         int count = 0;
         ArrayList<String> ips = new ArrayList<String>();
         System.out.print("\nUnique IPs with status codes between " + low + " and " + high);
         
         for(LogEntry le : records) {
             int status = le.getStatusCode();
             String ip = le.getIpAddress();
             if(!ips.contains(ip)) {
                 if((status >= low) && (status <= high)){
                     ips.add(ip);
                     count++; 
                 }
             }
         }
         System.out.println(": " + count);
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
        HashMap <String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             } else {
                counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
         int max = 0;
         for(int num : counts.values()) {
             if(num > max) {
                max = num;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String ip : counts.keySet()) {
             if(counts.get(ip) == max) {
                ips.add(ip);
             }
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> dateMap = new HashMap<String, ArrayList<String>>();
         ArrayList<String> ips;
         for(LogEntry le : records) {
             ips = new ArrayList<String>();
             String date = ((le.getAccessTime()).toString()).substring(4, 10);
             String ip = le.getIpAddress();
             if((!dateMap.containsKey(date))) {
                 ips.add(ip);
                 dateMap.put(date, ips);
             } else {
                ips = dateMap.get(date);
                ips.add(ip);
                dateMap.put(date, ips);
             }
         }
         System.out.println("\nIPs for days: " + dateMap);
         return dateMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap) {
         int max = 0;
         String date = "";
         for(String w : dateMap.keySet()) {
             if((dateMap.get(w)).size() > max) {
                 max = (dateMap.get(w)).size();
                 date = w;
             }
         }
         System.out.println("Date with most IP visits: " + date);
         return date;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dates, String day) {
         ArrayList<String> visitsOnDay = dates.get(day);
         HashMap<String, Integer> ips = new HashMap<String, Integer>();
         for(String ip : visitsOnDay) {
             if(!ips.containsKey(ip)) {
                 ips.put(ip, 1);
             } else {
                ips.put(ip, ips.get(ip) + 1);
             }
         }
         return iPsMostVisits(ips);
     }
}
