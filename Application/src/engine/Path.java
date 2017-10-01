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
                else if(curCommand.equals("V")){
                    i++;
                }
                else if(curCommand.equals("v")){
                    i++;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeX += currentProcessed;
                        if(Character.isLowerCase(curCommand.charAt(0))){
                            if(cumulativeX>maxX)maxX = cumulativeX;  
                        }
                        else{
                            if(currentProcessed>maxX)maxX = currentProcessed; 
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
                else if(curCommand.equals("V")){
                    i++;
                }
                else if(curCommand.equals("v")){
                    i++;
                }
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeX += currentProcessed;
                        if(Character.isLowerCase(curCommand.charAt(0))){
                            if(cumulativeX<minX)minX = cumulativeX;  
                        }
                        else{
                            if(currentProcessed<minX)minX = currentProcessed; 
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
                if(curCommand.equals("H")){
                    i++;
                }
                else if(curCommand.equals("h")){
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
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeY += currentProcessed2;
                        if(Character.isLowerCase(curCommand.charAt(0))){
                            if(cumulativeY>maxY)maxY = cumulativeY;  
                        }
                        else{
                            if(currentProcessed2>maxY)maxY = currentProcessed2; 
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
        double cumulativeY = 0;
        int numOfGroups = 0;
        int i = 0;
        double minY = Double.MAX_VALUE;
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
                    i++;
                }
                else if(curCommand.equals("h")){
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
                else{
                    double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                    if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                        cumulativeY += currentProcessed2;
                        if(Character.isLowerCase(curCommand.charAt(0))){
                            if(cumulativeY<minY)minY = cumulativeY;  
                        }
                        else{
                            if(currentProcessed2<minY)minY = currentProcessed2; 
                        }
                    }
                    i+=2;
                }
            }
        }
        return minY;
    }
    
}
