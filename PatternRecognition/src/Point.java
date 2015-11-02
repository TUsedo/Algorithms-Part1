import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

  private int x;
  private int y;
  
  public Point(int x , int y) {
    this.x = x;
    this.y = y;
  }
  
  public void draw() {
    StdDraw.point(x, y);
  }
  
  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }
  
  public String toString() {
    return ("(" + x + ", " + y + ")");
  }
  
  public int compareTo(Point that) {
    if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
    if (this.x == that.x && this.y == that.y) return 0;
    return 1;
  }
  
  public double slopeTo(Point that) {
    if (that == null)
      throw new NullPointerException();
    if (this.x == that.x) {
      if (this.y == that.y) {
        return Double.NEGATIVE_INFINITY;
      }
      return Double.POSITIVE_INFINITY;
    }
    if (this.y == that.y)
      return 0.0;
     
    return (double) (that.y - this.y)/(that.x - this.x);
  }
  
  public Comparator<Point> slopeOrder() {
    return slopeOrder;
  }
  
  private final Comparator<Point> slopeOrder = new Comparator<Point>() {
    public int compare(Point a, Point b) {
      if (slopeTo(a) < slopeTo(b)) return -1;
      if (slopeTo(a) > slopeTo(b)) return 1;
      return 0;
    }
  };    
}
