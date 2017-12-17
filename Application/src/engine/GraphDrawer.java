package engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Albert - 2014730007
 */
public class GraphDrawer {
    private Graph graph;
    private File destFile;
    private BufferedWriter bw;
    private ArrayList<Element> unprocessedElements;
    
    public GraphDrawer(Graph graph, ArrayList<Element> unprocessedElements, File destFile){
        this.unprocessedElements = unprocessedElements;
        this.graph = graph;
        this.destFile = destFile;
        try{
            this.bw = new BufferedWriter(new FileWriter(this.destFile.getPath()));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw() throws IOException{
        if(this.graph != null){
            bw.write("<html>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<svg height=\"2000\" width=\"2000\" style=\"padding:5;\">");
            bw.newLine();
            int numVertices = graph.getVertices().size();
            HashSet<String> hs = new HashSet<String>(); 
            for(int i=0;i<numVertices;i++){
                Vertex cur = graph.getVertices().get(i);
                hs.add("<circle cx=\"" + cur.getLocation().x + "\" cy=\"" + cur.getLocation().y + "\" r=\"5\" fill=\"black\" />");
            }
            int numEdges = graph.getEdges().size();
            for(int i=0;i<numEdges;i++){
                Edge cur = graph.getEdges().get(i);
                hs.add("<line x1=\"" + cur.getFrom().getLocation().x + "\" y1=\"" + cur.getFrom().getLocation().y + "\" "
                        + "x2=\"" + cur.getTo().getLocation().x + "\" y2=\"" + cur.getTo().getLocation().y + "\" style=\"stroke:rgb(0,0,0);stroke-width:2\" />");
            }
            int numUnprocessed = unprocessedElements.size();
            for(int i=0;i<numUnprocessed;i++){
                Element cur = unprocessedElements.get(i);
                String str = "";
                str += ("<" + cur.getName() + " ");
                HashMap<String, String> attributes = cur.getAttributes();
                for(Map.Entry<String, String> entry: attributes.entrySet()){
                    str += (entry.getKey()+ "=" + "\"" + entry.getValue() +"\"");
                }
                str += " />";
                hs.add(str);
            }
            for(String s: hs){
                bw.write(s);
                bw.newLine();
            }
            bw.write("</svg>");
            bw.newLine();
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
            bw.newLine();
            bw.close();
        }
    }
}
