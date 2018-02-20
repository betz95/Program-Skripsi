package engine;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Albert - 2014730007
 */
public class Vertex {
    public static final double EPSILON = 0.0000001;
    
    private int number;
    private Point2D.Double location;
    private int degree;
    private ArrayList<Integer> displayNumbers;
    
    public Vertex(int number, Point2D.Double location){
        this.number = number;
        this.location = location;
        this.degree = 0;
        this.displayNumbers = new ArrayList<>();
    }

    public Vertex(Point2D.Double location){
        this.location = location;
        this.displayNumbers = new ArrayList<>();
    }

    public ArrayList<Integer> getDisplayNumbers() {
        return displayNumbers;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Point2D.Double getLocation() {
        return location;
    }

    public void setLocation(Point2D.Double location) {
        this.location = location;
    }
    
    public void setDegree(int newDegree){
        this.degree = newDegree;
    }
    
    public int getDegree(){
        return this.degree;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Vertex){
            Vertex v = (Vertex)o;
            if(Math.abs(v.location.x-this.location.x)<EPSILON && Math.abs(v.location.y-this.location.y)<EPSILON)return true;
            else return false;
        }
        else return false;
    }
}
