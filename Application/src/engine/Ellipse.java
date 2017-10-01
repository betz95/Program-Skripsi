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
public class Ellipse extends Element {
    
    public Ellipse(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        return Double.parseDouble(this.attributes.get("cx")) + Double.parseDouble(this.attributes.get("rx"));
    }

    @Override
    public double getMinX() {
        return Double.parseDouble(this.attributes.get("cx")) - Double.parseDouble(this.attributes.get("rx"));
    }

    @Override
    public double getMaxY() {
        return Double.parseDouble(this.attributes.get("cy")) + Double.parseDouble(this.attributes.get("ry"));
    }

    @Override
    public double getMinY() {
        return Double.parseDouble(this.attributes.get("cy")) - Double.parseDouble(this.attributes.get("ry"));
    }
    
}
