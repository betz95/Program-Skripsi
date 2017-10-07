package engine;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ermengarde
 */
public class SVGParser {
    public static final double AREA_LOWER_BOUND = 2500;
    public static final double LENGTH_LOWER_BOUND = 100;
    
    private File svgFile;
    private BufferedReader br;
    private ArrayList<Element> elements;
    private ArrayList<Element> unprocessedElements;

    public SVGParser(File svgFile) {
        this.elements = new ArrayList<Element>();
        this.unprocessedElements = new ArrayList<Element>();
        this.svgFile = svgFile;
        try{
            this.br = new BufferedReader(new FileReader(svgFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void parseFile(){
        //memindahkan isi file SVG ke String fileContent
        String fileContent = "";
        String buffer;
        try{
            while((buffer = br.readLine()) != null){
                fileContent += buffer;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        //adding elements to arraylist
        String tags[] = fileContent.split("<");
        for(int i=0;i<tags.length;i++){
            String elementName = "";
            String elementAttributes = "";
            boolean flag = true;    //flag = true adalah fase mengambil elementName
            boolean inQuotes = false;
            char cur;
            for(int j=0;j<tags[i].length();j++){
                if(j!=0 && tags[i].charAt(j-1)=='/' && tags[i].charAt(j)=='>'){
                    break;
                }
                cur = tags[i].charAt(j);
                if(flag){
                    if(cur != ' ' && cur != '\t'){
                        elementName += cur;
                    }
                    else{
                       flag = false; 
                    }
                }
                else{
                    if(inQuotes){
                        if(cur == ','){
                            elementAttributes += ' ';
                        }
                        else{
                            elementAttributes += cur;
                        }
                        if(cur == '\"'){
                            inQuotes = !inQuotes;
                        }
                    }
                    else{
                        if(cur != ' ' && cur != '\t'){
                            elementAttributes += cur;
                            if(cur == '\"'){
                                inQuotes = !inQuotes;
                            }
                        }
                    }
                }
            }
            if(isTargetElement(elementName)){
                if(elementName.equals("rect")){
                    Rectangle rect = new Rectangle(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(rect.getHorizontalLength()==0 || rect.getVerticalLength()==0){
                        if(rect.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(rect);
                        }
                        else{
                            elements.add(rect);
                        }
                    }
                    else{
                        if(rect.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(rect);
                        }
                        else{
                            elements.add(rect);
                        }
                    }
                }
                else if(elementName.equals("circle")){
                    Circle circle = new Circle(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(circle.getHorizontalLength()==0 || circle.getVerticalLength()==0){
                        if(circle.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(circle);
                        }
                        else{
                            elements.add(circle);
                        }
                    }
                    else{
                        if(circle.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(circle);
                        }
                        else{
                            elements.add(circle);
                        }
                    }
                }
                else if(elementName.equals("ellipse")){
                    Ellipse ellipse = new Ellipse(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(ellipse.getHorizontalLength()==0 || ellipse.getVerticalLength()==0){
                        if(ellipse.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(ellipse);
                        }
                        else{
                            elements.add(ellipse);
                        }
                    }
                    else{
                        if(ellipse.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(ellipse);
                        }
                        else{
                            elements.add(ellipse);
                        }
                    }
                }
                else if(elementName.equals("line")){
                    Line line = new Line(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(line.getHorizontalLength()==0 || line.getVerticalLength()==0){
                        if(line.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(line);
                        }
                        else{
                            elements.add(line);
                        }
                    }
                    else{
                        if(line.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(line);
                        }
                        else{
                            elements.add(line);
                        }
                    }
                }
                else if(elementName.equals("polygon")){
                    Polygon polygon = new Polygon(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(polygon.getHorizontalLength()==0 || polygon.getVerticalLength()==0){
                        if(polygon.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(polygon);
                        }
                        else{
                            elements.add(polygon);
                        }
                    }
                    else{
                        if(polygon.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(polygon);
                        }
                        else{
                            elements.add(polygon);
                        }
                    }
                }
                else if(elementName.equals("polyline")){
                    Polyline polyline = new Polyline(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                    if(polyline.getHorizontalLength()==0 || polyline.getVerticalLength()==0){
                        if(polyline.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(polyline);
                        }
                        else{
                            elements.add(polyline);
                        }
                    }
                    else{
                        if(polyline.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(polyline);
                        }
                        else{
                            elements.add(polyline);
                        }
                    }
                }
                else if(elementName.equals("path")){
                    Path path = new Path(elementName, elementAttributes.substring(0, elementAttributes.length()-2));
                        System.out.println(path.getHorizontalLength()+" "+path.getVerticalLength());
                    if(path.getHorizontalLength()==0 || path.getVerticalLength()==0){
                        if(path.getBoundingRectArea()<LENGTH_LOWER_BOUND){
                            unprocessedElements.add(path);
                        }
                        else{
                            elements.add(path);
                        }
                    }
                    else{
                        if(path.getBoundingRectArea()<AREA_LOWER_BOUND){
                            unprocessedElements.add(path);
                        }
                        else{
                            elements.add(path);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public ArrayList<Element> getUnprocessedElements() {
        return unprocessedElements;
    }
    
    private boolean isTargetElement(String elementName) {
        if(elementName.equals("rect") || elementName.equals("circle")
                || elementName.equals("ellipse")|| elementName.equals("line")
                || elementName.equals("polygon") || elementName.equals("polyline")
                || elementName.equals("path")){
            return true;
        }
        else return false;
    }
}
