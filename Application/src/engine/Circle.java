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
public class Circle extends Element {
    
    public Circle(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        return Double.parseDouble(this.attributes.get("cx")) + Double.parseDouble(this.attributes.get("r"));
    }

    @Override
    public double getMinX() {
        return Double.parseDouble(this.attributes.get("cx")) - Double.parseDouble(this.attributes.get("r"));
    }

    @Override
    public double getMaxY() {
        return Double.parseDouble(this.attributes.get("cy")) + Double.parseDouble(this.attributes.get("r"));
    }

    @Override
    public double getMinY() {
        return Double.parseDouble(this.attributes.get("cy")) - Double.parseDouble(this.attributes.get("r"));
    }
    
}
