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
        int totalPoints = 0;
        for (Point point: s.getPoints()){
            totalPoints += 1;
        }
        
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        double totalPerimeter = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        return totalPerimeter/totalPoints;
    }

    public double getLargestSide(Shape s) {
        // loop through every side
        // if the current side length is greater than the current longest side, make that the longest side
        double longestSide = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            if (currDist > longestSide){
                longestSide = currDist;
            }
        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        // loop through every point
        // see if the x is greater than current greatest
        // if so, make it the greatest
        double greatestX = 0;
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > greatestX){
                greatestX = currPt.getX();
            }
        }
        return greatestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > largestPerimeter){
                largestPerimeter = currPerimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        File fileWithLargestPerimeter = null;
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > largestPerimeter){
                largestPerimeter = currPerimeter;
                fileWithLargestPerimeter = f;
            }
        }
        
        return fileWithLargestPerimeter.getName();
    }

    public void testShape() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int pointNum = getNumPoints(s);
        System.out.println("Number of points: " + pointNum);
        double aveSideLength = getAverageLength(s);
        System.out.println("Average Side Length: " + aveSideLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side Length: " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest Point X Coordinate: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
       double largestPerimeter = getLargestPerimeterMultipleFiles();
       System.out.println("Largest Perimeter: " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter: " + fileWithLargestPerimeter);
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
        //pr.testPerimeterMultipleFiles();
        //pr.testShape();
        pr.testFileWithLargestPerimeter();
    }
}
