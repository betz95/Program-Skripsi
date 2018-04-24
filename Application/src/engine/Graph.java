package engine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Albert - 2014730007
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private ArrayList<ArrayList<Vertex>> adjList;
    
    public Graph(){
        this.vertices = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();
        this.adjList = new ArrayList<ArrayList<Vertex>>();
    }
    
    /** Represents visited edge **/
    private class Node{
        private int first;
        private boolean second;
    }
        
    public ArrayList<Node>[] copyGraph(){
        ArrayList<Node> graph[] = new ArrayList[this.vertices.size()];
        for(int i=0;i<adjList.size();i++){
            graph[i] = new ArrayList<>();
            for(int j=0;j<adjList.get(i).size();j++){
                Vertex v = adjList.get(i).get(j);
                Node n = new Node();
                n.first = v.getNumber();
                n.second = false;
                graph[i].add(n);
            }
        }
        return graph;
    }
    
    /** Hierholzer's Algorithm Implementation**/
    public void hierholzer(){
        int res = this.makeEuler(); //res = 0 able to make euler circuit, res = 2 able to make euler path
        ArrayList<Integer> eulerPath = new ArrayList<>();
        int start = -1;
        if(res==2){
            for(int i=0;i<vertices.size();i++){
                if(vertices.get(i).getDegree()%2==1){
                    start = vertices.get(i).getNumber();
                    break;
                }
            }
        }
        else{
            start = 0;
        }
        ArrayList<Node>[] graph = copyGraph();
        doHierholzer(graph, start, eulerPath);
        eulerPath.add(start);
        if(eulerPath.size()>1){
            for(int i=0;i<eulerPath.size();i++){
                this.vertices.get(eulerPath.get(i)).getDisplayNumbers().add(i+1);
            }
        }
    }
    
    private void doHierholzer(ArrayList<Node>[] graph, int u, ArrayList<Integer> eulerPath) {
        if(graph.length==0)return ;
        for(int i=0;i<graph[u].size();i++){
            Node neigh = graph[u].get(i);
            if(!neigh.second){
                neigh.second = true;
                for(int j=0;j<graph[neigh.first].size();j++){
                    Node neighCur = graph[neigh.first].get(j);
                    if(neighCur.first==u && !neighCur.second){
                        neighCur.second = true;
                        break;
                    }
                }
                doHierholzer(graph, neigh.first, eulerPath);
                eulerPath.add(neigh.first);
            }
        }
    }
    
    public void addVertex(Vertex newVertex){
        if(!this.vertices.contains(newVertex)){
            newVertex.setNumber(this.vertices.size());
            this.vertices.add(newVertex);
            this.adjList.add(new ArrayList<Vertex>());
        }
        else{
            newVertex.setNumber(this.vertices.get(this.vertices.indexOf(newVertex)).getNumber());
        }
    }
    
    public boolean addEdge(Edge newEdge){
        if(!this.edges.contains(newEdge) && !newEdge.getFrom().equals(newEdge.getTo())){
            this.edges.add(newEdge);
            Vertex firstVertex = newEdge.getFrom();
            Vertex secondVertex = newEdge.getTo();
            if(this.vertices.contains(firstVertex)){
                firstVertex = this.vertices.get(this.vertices.indexOf(firstVertex));
            }
            if(this.vertices.contains(secondVertex)){
                secondVertex = this.vertices.get(this.vertices.indexOf(secondVertex));
            }        
            firstVertex.setDegree(firstVertex.getDegree()+1);
            secondVertex.setDegree(secondVertex.getDegree()+1);
            this.adjList.get(firstVertex.getNumber()).add(secondVertex);
            this.adjList.get(secondVertex.getNumber()).add(firstVertex);
            return true;
        }
        else return false;
    }
    
    public void removeEdge(Edge e){
        if(this.edges.contains(e)){
            this.edges.remove(e);
            Vertex firstVertex = e.getFrom();
            Vertex secondVertex = e.getTo();
            if(this.vertices.contains(firstVertex)){
                firstVertex = this.vertices.get(this.vertices.indexOf(firstVertex));
            }
            if(this.vertices.contains(secondVertex)){
                secondVertex = this.vertices.get(this.vertices.indexOf(secondVertex));
            }        
            firstVertex.setDegree(firstVertex.getDegree()-1);
            secondVertex.setDegree(secondVertex.getDegree()-1);
            this.adjList.get(firstVertex.getNumber()).remove(secondVertex);
            this.adjList.get(secondVertex.getNumber()).remove(firstVertex);
        }
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
    
    public ArrayList<ArrayList<Vertex>> getAdjList(){
        return this.adjList;
    }
    
    private void dfsRemove(ArrayList<Node>[] adjList, Vertex u, ArrayList<Edge> smallerArea){
        int cur = u.getNumber();
        for(int i=0;i<adjList[cur].size();i++){
            Node v = adjList[cur].get(i);
            if(!v.second){
                v.second = true;
                Edge e = new Edge(u, this.vertices.get(v.first));
                e = edges.get(edges.indexOf(e));
                if(!smallerArea.contains(e)){
                    smallerArea.add(e);
                }
                dfsRemove(adjList, this.vertices.get(v.first), smallerArea);
            }
        }
    }
    
    private void chooseLargerArea(Edge e){
        boolean visited[] = new boolean[this.vertices.size()];
        int area1 = dfsCount(e.getFrom().getNumber(), visited);
        visited = new boolean[this.vertices.size()];
        int area2 = dfsCount(e.getTo().getNumber(), visited);
        ArrayList<Edge> smallerArea = new ArrayList<>();
        ArrayList<Node>[] graph = copyGraph();
        if(area1>=area2){
            dfsRemove(graph, e.getTo(), smallerArea);
        }
        else{
            dfsRemove(graph, e.getFrom(), smallerArea);
        }
        for(Edge edge: smallerArea){
            Vertex eFrom = this.vertices.get(this.vertices.indexOf(edge.getFrom()));
            Vertex eTo = this.vertices.get(this.vertices.indexOf(edge.getTo()));
            this.adjList.get(eFrom.getNumber()).remove(eTo);
            this.adjList.get(eTo.getNumber()).remove(eFrom);
            this.vertices.get(eFrom.getNumber()).setDegree(this.vertices.get(eFrom.getNumber()).getDegree()-1);
            this.vertices.get(eTo.getNumber()).setDegree(this.vertices.get(eTo.getNumber()).getDegree()-1);
            this.edges.get(this.edges.indexOf(edge)).setHelpLine(true);
        }
    }
    
    private void makeHelpingLine(Edge e) {
        boolean isBridge = false;
        if(checkBridge(e)!=-1){
            isBridge = true;
        }
        Vertex eFrom = e.getFrom();
        Vertex eTo = e.getTo();
        this.adjList.get(eFrom.getNumber()).remove(eTo);
        this.adjList.get(eTo.getNumber()).remove(eFrom);
        this.vertices.get(eFrom.getNumber()).setDegree(this.vertices.get(eFrom.getNumber()).getDegree()-1);
        this.vertices.get(eTo.getNumber()).setDegree(this.vertices.get(eTo.getNumber()).getDegree()-1);
        this.edges.get(this.edges.indexOf(e)).setHelpLine(true);
        if(isBridge){
            chooseLargerArea(e);
        }
    }
    
    public int makeEuler(){
        int numOfOddVertices = 0;
        for(int i=0;i<this.vertices.size();i++){
            if(this.vertices.get(i).getDegree()%2==1){
                numOfOddVertices += 1;
            }
        }
        while(numOfOddVertices!=0 && numOfOddVertices!=2){
            Edge best = null;
            boolean first = true;
            for(int i=0;i<this.edges.size();i++){
                Edge e = this.edges.get(i);
                if(!e.isHelpLine() && (this.vertices.get(e.getFrom().getNumber()).getDegree()%2==1 || this.vertices.get(e.getTo().getNumber()).getDegree()%2==1)){
                    if(first){
                        best = e;
                        first = false;
                    }
                    else{
                        best = compareEdge(best, e);
                    }
                }
            }
            makeHelpingLine(best);
            numOfOddVertices = 0;
            for(int i=0;i<this.vertices.size();i++){
                if(this.vertices.get(i).getDegree()%2==1){
                    numOfOddVertices += 1;
                }
            }
        }
        return numOfOddVertices;
    }
    
    private Edge compareEdge(Edge e1, Edge e2){
        int isBridgeE1 = checkBridge(e1);
        int isBridgeE2 = checkBridge(e2);
        int numConnectedOddVertexE1 = 0;
        int numConnectedOddVertexE2 = 0;
        
        if(this.vertices.get(e1.getFrom().getNumber()).getDegree()%2==1)numConnectedOddVertexE1++;
        if(this.vertices.get(e1.getTo().getNumber()).getDegree()%2==1)numConnectedOddVertexE1++;
        if(this.vertices.get(e2.getFrom().getNumber()).getDegree()%2==1)numConnectedOddVertexE2++;
        if(this.vertices.get(e2.getTo().getNumber()).getDegree()%2==1)numConnectedOddVertexE2++;
        
        if(numConnectedOddVertexE1==2 && numConnectedOddVertexE2==2){
            if(isBridgeE1==-1 && isBridgeE2==-1){
                return e1;
            }
            else if(isBridgeE1==-1 && isBridgeE2!=-1){
                return e1;
            }
            else if(isBridgeE1!=-1 && isBridgeE2==-1){
                return e2;
            }
            else{
                return (isBridgeE1>=isBridgeE2)? e1: e2;
            }
        }
        else if(numConnectedOddVertexE1==2 && numConnectedOddVertexE2!=2){
            return e1;
        }
        else if(numConnectedOddVertexE1!=2 && numConnectedOddVertexE2==2){
            return e2;
        }
        else{
            if(isBridgeE1==-1 && isBridgeE2==-1){
                return e1;
            }
            else if(isBridgeE1==-1 && isBridgeE2!=-1){
                return e1;
            }
            else if(isBridgeE1!=-1 && isBridgeE2==-1){
                return e2;
            }
            else{
                return (isBridgeE1>=isBridgeE2)? e1: e2;
            }
        }
    }

    private int dfsCount(int cur, boolean visited[]){
        visited[cur] = true;
        int count = 1;
        for(int i=0;i<adjList.get(cur).size();i++){
            Vertex v = adjList.get(cur).get(i);
            if(!visited[v.getNumber()]){
                count += dfsCount(v.getNumber(), visited);
            }
        }
        return count;
    }
    
    private int checkBridge(Edge e) {
        boolean visited[] = new boolean[this.vertices.size()];
        int before = dfsCount(e.getFrom().getNumber(), visited);
        this.adjList.get(e.getFrom().getNumber()).remove(e.getTo());
        this.adjList.get(e.getTo().getNumber()).remove(e.getFrom());
        visited = new boolean[this.vertices.size()];
        int after = dfsCount(e.getFrom().getNumber(), visited);
        this.adjList.get(e.getFrom().getNumber()).add(e.getTo());
        this.adjList.get(e.getTo().getNumber()).add(e.getFrom());
        if(before==after){
            return -1;
        }
        else{
            return (after>before)? after: before;
        }
    }

    /** Method for development only **/
    private void printAdjList() {
        for(int i=0;i<adjList.size();i++){
            Vertex vi = this.vertices.get(i);
            if(vi.getDegree()==0)continue;
            System.out.println("Vertex "+vi.getNumber()+" Has Degree "+vi.getDegree()+" Located At "+vi.getLocation());
            System.out.println("Neighbour of Vertex "+vi.getNumber());
            for(int j=0;j<adjList.get(i).size();j++){
                Vertex vj = adjList.get(i).get(j);
                System.out.println("\t\t"+vj.getNumber()+" "+vj.getLocation());
            }
        }
    }
}
