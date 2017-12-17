package engine;

/**
 * @author Albert - 2014730007
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
