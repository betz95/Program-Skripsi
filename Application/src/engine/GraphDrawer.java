package engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ermengarde
 */
public class GraphDrawer {
    private Graph graph;
    private File destFile;
    private BufferedWriter bw;
    
    public GraphDrawer(Graph graph, File destFile){
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
            bw.write("<svg height=\"800\" width=\"800\" style=\"padding:5;\">");
            bw.newLine();
            int numVertices = graph.getVertices().size();
            for(int i=0;i<numVertices;i++){
                Vertex cur = graph.getVertices().get(i);
                bw.write("<circle cx=\"" + cur.getLocation().x + "\" cy=\"" + cur.getLocation().y + "\" r=\"5\" fill=\"black\" />");
                bw.newLine();
            }
            int numEdges = graph.getEdges().size();
            for(int i=0;i<numEdges;i++){
                Edge cur = graph.getEdges().get(i);
                bw.write("<line x1=\"" + cur.getFrom().getLocation().x + "\" y1=\"" + cur.getFrom().getLocation().y + "\" "
                        + "x2=\"" + cur.getTo().getLocation().x + "\" y2=\"" + cur.getTo().getLocation().y + "\" style=\"stroke:rgb(0,0,0);stroke-width:2\" />");
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
