package engine;


/**
 * @author Albert - 2014730007
 */
public class Polyline extends Element {
    
    public Polyline(String name, String attributes) {
        super(name, attributes);
    }

    @Override
    public double getMaxX() {
        String points[] = this.attributes.get("points").split("\\s+");
        int numPoints = points.length;
        double maxX = Double.MIN_VALUE;
        for(int i=0;i<numPoints;i+=2){
            double cur = Double.parseDouble(points[i]);
            if(cur>maxX)maxX = cur;
        }
        return maxX;
    }

    @Override
    public double getMinX() {
        String points[] = this.attributes.get("points").split("\\s+");
        int numPoints = points.length;
        double minX = Double.MAX_VALUE;
        for(int i=0;i<numPoints;i+=2){
            double cur = Double.parseDouble(points[i]);
            if(cur<minX)minX = cur;
        }
        return minX;
    }

    @Override
    public double getMaxY() {
        String points[] = this.attributes.get("points").split("\\s+");
        int numPoints = points.length;
        double maxY = Double.MIN_VALUE;
        for(int i=1;i<numPoints;i+=2){
            double cur = Double.parseDouble(points[i]);
            if(cur>maxY)maxY = cur;
        }
        return maxY;
    }

    @Override
    public double getMinY() {
        String points[] = this.attributes.get("points").split("\\s+");
        int numPoints = points.length;
        double minY = Double.MAX_VALUE;
        for(int i=1;i<numPoints;i+=2){
            double cur = Double.parseDouble(points[i]);
            if(cur<minY)minY = cur;
        }
        return minY;
    }
}
