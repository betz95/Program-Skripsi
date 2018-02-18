package engine;

/**
 * @author Albert - 2014730007
 */
public class Line extends Element {
    
    public Line(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        return Math.max(Double.parseDouble(this.attributes.get("x2")), Double.parseDouble(this.attributes.get("x1")));
    }

    @Override
    public double getMinX() {
        return Math.min(Double.parseDouble(this.attributes.get("x1")), Double.parseDouble(this.attributes.get("x2")));
    }

    @Override
    public double getMaxY() {
        return Math.max(Double.parseDouble(this.attributes.get("y2")), Double.parseDouble(this.attributes.get("y1")));
    }

    @Override
    public double getMinY() {
        return Math.min(Double.parseDouble(this.attributes.get("y1")), Double.parseDouble(this.attributes.get("y2")));
    }
    
}
