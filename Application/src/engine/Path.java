package engine;

import java.awt.geom.Point2D;

/**
 * @author Albert - 2014730007
 */
public class Path extends Element {
    public Path(String name, String attributes) {
        super(name, attributes);
    }
   
    @Override
    public double getMaxX() {
        String cmd[] = this.attributes.get("d").split("\\s+");
        int cmdLen = cmd.length;
        String curCommand = "";
        String curProcessed = "";
        double cumulativeX = 0;
        double cumulativeY = 0;
        int i = 0;
        double maxX = Double.MIN_VALUE;
        
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX = currentProcessed;
                    if(cumulativeX>maxX)maxX = cumulativeX;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    if(cumulativeX>maxX)maxX = cumulativeX;
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[0][0]+rads.getMatrix()[0][0]>maxX)maxX = cP.getMatrix()[0][0]+rads.getMatrix()[0][0];
                    cumulativeX = currentProcessed3;
                    cumulativeY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3+cumulativeX, currentProcessed4+cumulativeY, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[0][0]+rads.getMatrix()[0][0]>maxX)maxX = cP.getMatrix()[0][0]+rads.getMatrix()[0][0];
                    cumulativeX += currentProcessed3;
                    cumulativeY += currentProcessed4;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){  
                        if(currentProcessed+cumulativeX>maxX)maxX = currentProcessed+cumulativeX;
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX += currentProcessed;
                            cumulativeY += currentProcessed2;
                        }
                    }
                    else{
                        if(currentProcessed>maxX)maxX = currentProcessed; 
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX = currentProcessed;
                            cumulativeY = currentProcessed2;
                        }
                    }
                    i+=2;
                }
            }
        }
        
        return maxX;
    }

    @Override
    public double getMinX() {
        String cmd[] = this.attributes.get("d").split("\\s+");
        int cmdLen = cmd.length;
        String curCommand = "";
        String curProcessed = "";
        double cumulativeX = 0;
        double cumulativeY = 0;
        int i = 0;
        double minX = Double.MAX_VALUE;
        
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX = currentProcessed;
                    if(cumulativeX<minX)minX = cumulativeX;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    if(cumulativeX<minX)minX = cumulativeX;
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[0][0]-rads.getMatrix()[0][0]<minX)minX = cP.getMatrix()[0][0]-rads.getMatrix()[0][0];
                    cumulativeX = currentProcessed3;
                    cumulativeY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3+cumulativeX, currentProcessed4+cumulativeY, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[0][0]-rads.getMatrix()[0][0]<minX)minX = cP.getMatrix()[0][0]-rads.getMatrix()[0][0];
                    cumulativeX += currentProcessed3;
                    cumulativeY += currentProcessed4;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){  
                        if(currentProcessed+cumulativeX<minX)minX = currentProcessed+cumulativeX;
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX += currentProcessed;
                            cumulativeY += currentProcessed2;
                        }
                    }
                    else{
                        if(currentProcessed<minX)minX = currentProcessed; 
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX = currentProcessed;
                            cumulativeY = currentProcessed2;
                        }
                    }
                    i+=2;
                }
            }
        }
        
        return minX;
    }

    @Override
    public double getMaxY() {
        String cmd[] = this.attributes.get("d").split("\\s+");
        int cmdLen = cmd.length;
        String curCommand = "";
        String curProcessed = "";
        double cumulativeX = 0;
        double cumulativeY = 0;
        int i = 0;
        double maxY = Double.MIN_VALUE;
        
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY = currentProcessed;
                    if(cumulativeY>maxY)maxY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    if(cumulativeY>maxY)maxY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[1][0]+rads.getMatrix()[1][0]>maxY)maxY = cP.getMatrix()[1][0]+rads.getMatrix()[1][0];
                    cumulativeX = currentProcessed3;
                    cumulativeY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3+cumulativeX, currentProcessed4+cumulativeY, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[1][0]+rads.getMatrix()[1][0]>maxY)maxY = cP.getMatrix()[1][0]+rads.getMatrix()[1][0];
                    cumulativeX += currentProcessed3;
                    cumulativeY += currentProcessed4;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){  
                        if(currentProcessed2+cumulativeY>maxY)maxY = currentProcessed2+cumulativeY;
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX += currentProcessed;
                            cumulativeY += currentProcessed2;
                        }
                    }
                    else{
                        if(currentProcessed2>maxY)maxY = currentProcessed2; 
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX = currentProcessed;
                            cumulativeY = currentProcessed2;
                        }
                    }
                    i+=2;
                }
            }
        }
        
        return maxY;
    }

    @Override
    public double getMinY() {
        String cmd[] = this.attributes.get("d").split("\\s+");
        int cmdLen = cmd.length;
        String curCommand = "";
        String curProcessed = "";
        double cumulativeX = 0;
        double cumulativeY = 0;
        int i = 0;
        double minY = Double.MAX_VALUE;
        
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY = currentProcessed;
                    if(cumulativeY<minY)minY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    if(cumulativeY<minY)minY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[1][0]-rads.getMatrix()[1][0]<minY)minY = cP.getMatrix()[1][0]-rads.getMatrix()[1][0];
                    cumulativeX = currentProcessed3;
                    cumulativeY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    Matrix rads = ensureRadii(cumulativeX, cumulativeY, currentProcessed3+cumulativeX, currentProcessed4+cumulativeY, currentProcessed, currentProcessed2, Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    Matrix cP = findArcCenterPoint(cumulativeX, cumulativeY, currentProcessed3, currentProcessed4, rads.getMatrix()[0][0], rads.getMatrix()[1][0], Math.toRadians(Double.parseDouble(cmd[i+2])), Integer.parseInt(cmd[i+3]), Integer.parseInt(cmd[i+4]));
                    if(cP.getMatrix()[1][0]-rads.getMatrix()[1][0]<minY)minY = cP.getMatrix()[1][0]-rads.getMatrix()[1][0];
                    cumulativeX += currentProcessed3;
                    cumulativeY += currentProcessed4;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){  
                        if(currentProcessed2+cumulativeY<minY)minY = currentProcessed2+cumulativeY;
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX += currentProcessed;
                            cumulativeY += currentProcessed2;
                        }
                    }
                    else{
                        if(currentProcessed2<minY)minY = currentProcessed2; 
                        if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                            cumulativeX = currentProcessed;
                            cumulativeY = currentProcessed2;
                        }
                    }
                    i+=2;
                }
            }
        }
        
        return minY;
    }
    
    private Matrix findArcCenterPoint(double x1, double y1, double x2, double y2, double rx, double ry, double varphi, int fA, int fS){
        //find x1' and y1'
        double matrix[][] = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi) * -1;
        matrix[1][1] = Math.cos(varphi);
        Matrix m1 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1-x2)/2.0;
        matrix[1][0] = (y1-y2)/2.0;
        Matrix m2 = new Matrix(matrix);
        Matrix coordinateA = MatrixMath.multiply(m1, m2);
        double x1A = coordinateA.getMatrix()[0][0];
        double y1A = coordinateA.getMatrix()[1][0];
        
        //find cx' and cy'
        double val = Math.sqrt( ((rx*rx*ry*ry) - (rx*rx*y1A*y1A) - (ry*ry*x1A*x1A)) / ((rx*rx*y1A*y1A) + (ry*ry*x1A*x1A)) );
        if(fA==fS){
            val *= -1;
        }
        matrix = new double[2][1];
        matrix[0][0] = rx*y1A/ry;
        matrix[1][0] = -1*(ry*x1A/rx);
        Matrix m3 = new Matrix(matrix);
        Matrix centerPointA = MatrixMath.multiply(m3, val);
        
        //find center points
        matrix = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = -1 * Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi);
        matrix[1][1] = Math.cos(varphi);
        Matrix m4 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1+x2)/2.0;
        matrix[1][0] = (y1+y2)/2.0;
        Matrix m5 = new Matrix(matrix);
        Matrix centerPoint = MatrixMath.multiply(m4, centerPointA);
        centerPoint = MatrixMath.add(centerPoint, m5);
        return centerPoint;
    }
    
    private Matrix ensureRadii(double x1, double y1, double x2, double y2, double rx, double ry, double varphi, int fA, int fS){
        double matrix[][] = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi) * -1;
        matrix[1][1] = Math.cos(varphi);
        Matrix m1 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1-x2)/2.0;
        matrix[1][0] = (y1-y2)/2.0;
        Matrix m2 = new Matrix(matrix);
        Matrix coordinateA = MatrixMath.multiply(m1, m2);
        double x1A = coordinateA.getMatrix()[0][0];
        double y1A = coordinateA.getMatrix()[1][0];

        rx = Math.abs(rx);
        ry = Math.abs(ry);
        double radii = Math.pow(x1A, 2)/Math.pow(rx, 2) + Math.pow(y1A, 2)/Math.pow(ry, 2);
        if(radii>1){
            rx = Math.sqrt(radii) * rx;
            ry = Math.sqrt(radii) * ry;
        }
        double r[][] = new double[2][1];
        r[0][0] = rx;
        r[1][0] = ry;
        return new Matrix(r);
    }
}
