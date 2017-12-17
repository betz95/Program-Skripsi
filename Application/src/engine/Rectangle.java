package engine;

/**
 * @author Albert - 2014730007
 */
public class Rectangle extends Element {
    
    public Rectangle(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        return this.getMinX() + Double.parseDouble(this.attributes.get("width"));
    }

    @Override
    public double getMinX() {
        if(!this.attributes.containsKey("x")){
            return 0;
        }
        else return Double.parseDouble(this.attributes.get("x"));
    }

    @Override
    public double getMaxY() {
        return this.getMinY() + Double.parseDouble(this.attributes.get("height"));
    }

    @Override
    public double getMinY() {
        if(!this.attributes.containsKey("y")){
            return 0;
        }
        else return Double.parseDouble(this.attributes.get("y"));
    }
}
