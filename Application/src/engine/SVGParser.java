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
    private File svgFile;
    private BufferedReader br;

    public SVGParser(File svgFile) {
        this.svgFile = svgFile;
        try{
            this.br = new BufferedReader(new FileReader(svgFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Element> parseFile(){
        ArrayList<Element> elements = new ArrayList<Element>();
        
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
                    elements.add(new Rectangle(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("circle")){
                    elements.add(new Circle(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("ellipse")){
                    elements.add(new Ellipse(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("line")){
                    elements.add(new Line(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("polygon")){
                    elements.add(new Polygon(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("polyline")){
                    elements.add(new Polyline(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
                else if(elementName.equals("path")){
                    elements.add(new Path(elementName, elementAttributes.substring(0, elementAttributes.length()-2)));
                }
            }
        }
        return elements;
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
