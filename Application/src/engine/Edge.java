package engine;

/**
 * @author Albert - 2014730007
 */
public class Edge {
    private Vertex from;
    private Vertex to;
    private boolean helpLine;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
        this.helpLine = false;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public boolean isHelpLine() {
        return helpLine;
    }

    public void setHelpLine(boolean helpLine) {
        this.helpLine = helpLine;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Edge){
            Edge e = (Edge)o;
            if((this.from.equals(e.from) && this.to.equals(e.to)) || (this.from.equals(e.to) && this.to.equals(e.from))){
                return true;
            }
            else{
                return false;
            }
        }
        else return false;
    }
}
