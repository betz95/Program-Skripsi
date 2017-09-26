package engine;


import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ermengarde
 */
public class Element {
    private String name;
    private HashMap<String, String> attributes;

    public Element(String name, String attributes) {
        this.name = name;
        this.attributes = new HashMap<String, String>();
        String att[] = attributes.split("\"");
        String attributeName = "";
        String attributeValue = "";
        if(!attributes.isEmpty()){
            for(int i=0;i<att.length;i+=2){
                attributeName = att[i].substring(0, att[i].length()-1);
                if(this.name.equals("path") && attributeName.equals("d")){
                    int attLen = att[i+1].length();
                    String newAtt = "";
                    Character cur = null;
                    for(int j=0;j<attLen;j++){
                        cur = att[i+1].charAt(j);
                        if(Character.isAlphabetic(cur)){
                            newAtt += " " + cur + " ";
                        }
                        else{
                            if(cur == '-'){
                                newAtt += " " + cur;
                            }
                            else{
                                newAtt += cur;
                            }
                        }
                    }
                    newAtt = newAtt.trim();
                    attributeValue = newAtt;
                }
                else{
                    attributeValue = att[i+1];
                }
                this.attributes.put(attributeName, attributeValue);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
