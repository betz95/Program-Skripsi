/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author Ermengarde
 */
public class Line extends Element {
    
    public Line(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        return Double.parseDouble(this.attributes.get("x2"));
    }

    @Override
    public double getMinX() {
        return Double.parseDouble(this.attributes.get("x1"));
    }

    @Override
    public double getMaxY() {
        return Double.parseDouble(this.attributes.get("y2"));
    }

    @Override
    public double getMinY() {
        return Double.parseDouble(this.attributes.get("y1"));
    }
    
}
