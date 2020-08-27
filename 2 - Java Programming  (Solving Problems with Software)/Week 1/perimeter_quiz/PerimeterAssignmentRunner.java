import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for(Point point : s.getPoints()){
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        int numPoints = getNumPoints(s);
        double totalLen = getPerimeter(s);
        double average = totalLen/numPoints;
        return average;
    }

    public double getLargestSide(Shape s) {
        double maxSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > maxSide){
                maxSide = currDist;
            }
            prevPt = currPt;
        }
        return maxSide;
    }

    public double getLargestX(Shape s) {
        double maxX = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            if(currPt.getX() > maxX){
                maxX = currPt.getX();
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > maxPerimeter){
                maxPerimeter = perimeter;
            }
        }
        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > maxPerimeter){
                maxPerimeter = perimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("Number of Points: " + numPoints);
        double avgLength = getAverageLength(s);
        System.out.println("Average Length: " + avgLength);
        double maxSide = getLargestSide(s);
        System.out.println("Largest Side: " + maxSide);
        double maxX = getLargestX(s);
        System.out.println("Largest X: " + maxX);
    }
    
    public void testPerimeterMultipleFiles() {
        double maxPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter is " + maxPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String maxPerimeterFile = getFileWithLargestPerimeter();
        System.out.println("The file with the largest perimeter is " + maxPerimeterFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
