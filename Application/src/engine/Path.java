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
        int numOfGroups = 0;
        int i = 0;
        double maxX = Double.MIN_VALUE;
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                numOfGroups++;
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX += currentProcessed;
                    if(currentProcessed>maxX)maxX = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    if(cumulativeX>maxX)maxX = cumulativeX;
                    i++;
                }
                else if(curCommand.equalsIgnoreCase("V")){
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    cumulativeX += currentProcessed;
                    if(currentProcessed>maxX)maxX = currentProcessed;
                    cumulativeX += currentProcessed3;
                    if(currentProcessed3>maxX)maxX = currentProcessed3;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    cumulativeX += currentProcessed;
                    if(cumulativeX>maxX)maxX = cumulativeX;
                    cumulativeX += currentProcessed3;
                    if(cumulativeX>maxX)maxX = cumulativeX;
                    i+=7;
                }
                else{
                    if(Character.isLowerCase(curCommand.charAt(0))){
                        if(currentProcessed+cumulativeX>maxX)maxX = currentProcessed+cumulativeX;  
                    }
                    else{
                        if(currentProcessed>maxX)maxX = currentProcessed; 
                    }
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeX += currentProcessed;
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
        int numOfGroups = 0;
        int i = 0;
        double minX = Double.MAX_VALUE;
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                numOfGroups++;
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equals("H")){
                    cumulativeX += currentProcessed;
                    if(currentProcessed<minX)minX = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("h")){
                    cumulativeX += currentProcessed;
                    if(cumulativeX<minX)minX = cumulativeX;
                    i++;
                }
                else if(curCommand.equalsIgnoreCase("V")){
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    cumulativeX += currentProcessed;
                    if(currentProcessed<minX)minX = currentProcessed;
                    cumulativeX += currentProcessed3;
                    if(currentProcessed3<minX)minX = currentProcessed3;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    cumulativeX += currentProcessed;
                    if(cumulativeX<minX)minX = cumulativeX;
                    cumulativeX += currentProcessed3;
                    if(cumulativeX<minX)minX = cumulativeX;
                    i+=7;
                }
                else{
                    if(Character.isLowerCase(curCommand.charAt(0))){
                        if(currentProcessed+cumulativeX<minX)minX = currentProcessed+cumulativeX;  
                    }
                    else{
                        if(currentProcessed<minX)minX = currentProcessed; 
                    }
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeX += currentProcessed;
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
        double cumulativeY = 0;
        int numOfGroups = 0;
        int i = 0;
        double maxY = Double.MIN_VALUE;
        while(i<cmdLen){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                numOfGroups++;
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equalsIgnoreCase("H")){
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY += currentProcessed;
                    if(currentProcessed>maxY)maxY = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    if(cumulativeY>maxY)maxY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    cumulativeY += currentProcessed2;
                    if(currentProcessed2>maxY)maxY = currentProcessed2;
                    cumulativeY += currentProcessed4;
                    if(currentProcessed4>maxY)maxY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    cumulativeY += currentProcessed2;
                    if(cumulativeY>maxY)maxY = cumulativeY;
                    cumulativeY += currentProcessed4;
                    if(cumulativeY>maxY)maxY = cumulativeY;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){
                        if(currentProcessed2+cumulativeY>maxY)maxY = currentProcessed2+cumulativeY;  
                    }
                    else{
                        if(currentProcessed2>maxY)maxY = currentProcessed2; 
                    }
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeY += currentProcessed2;
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
        double cumulativeY = 0;
        int numOfGroups = 0;
        int i = 0;
        double minY = Double.MAX_VALUE;
        while(i<cmdLen-1){
            curProcessed = cmd[i];
            if(Character.isAlphabetic(curProcessed.charAt(0))){
                curCommand = cmd[i];
                numOfGroups++;
                i++;
            }
            else{
                double currentProcessed = Double.parseDouble(curProcessed);
                if(curCommand.equalsIgnoreCase("H")){
                    i++;
                }
                else if(curCommand.equals("V")){
                    cumulativeY += currentProcessed;
                    if(currentProcessed<minY)minY = currentProcessed;
                    i++;
                }
                else if(curCommand.equals("v")){
                    cumulativeY += currentProcessed;
                    if(cumulativeY<minY)minY = cumulativeY;
                    i++;
                }
                else if(curCommand.equals("A")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    cumulativeY += currentProcessed2;
                    if(currentProcessed2<minY)minY = currentProcessed2;
                    cumulativeY += currentProcessed4;
                    if(currentProcessed4<minY)minY = currentProcessed4;
                    i+=7;
                }
                else if(curCommand.equals("a")){
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                    double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                    cumulativeY += currentProcessed2;
                    if(cumulativeY<minY)minY = cumulativeY;
                    cumulativeY += currentProcessed4;
                    if(cumulativeY<minY)minY = cumulativeY;
                    i+=7;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(Character.isLowerCase(curCommand.charAt(0))){
                        if(currentProcessed2+cumulativeY<minY)minY = currentProcessed2+cumulativeY;  
                    }
                    else{
                        if(currentProcessed2<minY)minY = currentProcessed2; 
                    }
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeY += currentProcessed2;
                    }
                    i+=2;
                }
            }
        }
        return minY;
    }
    
}
