package engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Ermengarde
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    
    public Graph(){
        this.vertices = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();
    }
    
    public void addVertex(Vertex newVertex){
        this.vertices.add(newVertex);
    }
    
    public void addEdge(Edge newEdge){
        this.edges.add(newEdge);
    }
    
    //test
    public boolean removeVertex(Vertex remove){
        return this.vertices.remove(remove);
    }
    
    //test
    public boolean removeEdge(Edge remove){
        return this.edges.remove(remove);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
