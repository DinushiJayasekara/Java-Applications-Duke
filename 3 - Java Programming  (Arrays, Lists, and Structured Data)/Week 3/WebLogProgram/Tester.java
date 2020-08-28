
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.countUniqueIPs();
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.uniqueIPVisitsOnDay("Sep 24");
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.countUniqueIPsInRange(200, 299);
        la.countUniqueIPsInRange(300, 399);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIP()));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.iPsForDays();
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.dayWithMostIPVisits(la.iPsForDays());
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
    }
}
