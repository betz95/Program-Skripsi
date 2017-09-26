package engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.geom.Point2D;

/**
 *
 * @author Ermengarde
 */
public class Vertex {
    private int number;
    private Point2D.Double location;
    
    public Vertex(int number, Point2D.Double location){
        this.number = number;
        this.location = location;
    }

    public Vertex(Point2D.Double location){
        this.location = location;
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
}
