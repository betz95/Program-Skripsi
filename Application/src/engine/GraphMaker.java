package engine;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Albert - 2014730007
 */
public class GraphMaker {
    private Graph result;
    private ArrayList<Element> elements;
    
    public GraphMaker(ArrayList<Element> elements){
        this.elements = elements;
    }

    public Graph getResult() {
        this.result = new Graph();
        int elementsSize = this.elements.size();
        for(int i=0;i<elementsSize;i++){
            Element cur = elements.get(i);
            if(cur.getName().equals("rect")){
                makeRect(cur);
            }
            else if(cur.getName().equals("circle")){
                makeCircle(cur);
            }
            else if(cur.getName().equals("ellipse")){
                makeEllipse(cur);
            }
            else if(cur.getName().equals("line")){
                makeLine(cur);
            }
            else if(cur.getName().equals("polygon")){
                makePolygon(cur);
            }
            else if(cur.getName().equals("polyline")){
                makePolyline(cur);
            }
            else if(cur.getName().equals("path")){
                makePath(cur);
            }
        }
        handleIntersections();
        return result;
    }

    private void handleIntersections() {
        ArrayList<Point2D.Double> pointIntersection = new ArrayList<>();
        ArrayList<Edge> edge1Intersection = new ArrayList<>();
        ArrayList<Edge> edge2Intersection = new ArrayList<>();
        for(int i=0;i<result.getEdges().size();i++){
            Edge e1 = result.getEdges().get(i);
            Line2D.Double l1 = new Line2D.Double(e1.getFrom().getLocation(), e1.getTo().getLocation()); //vertical line has undefined slope
            double x1E1 = e1.getFrom().getLocation().x;
            double y1E1 = e1.getFrom().getLocation().y;
            double x2E1 = e1.getTo().getLocation().x;
            double y2E1 = e1.getTo().getLocation().y;
            double m1 = (y2E1 - y1E1)/(x2E1 - x1E1);
            double equationE1[] = {m1, (m1 * x1E1 - y1E1)*-1}; //[0] = x, [1] = constant
            boolean isVerticalLineE1 = false;
            if(x1E1==x2E1)isVerticalLineE1 = true;
            for(int j=i+1;j<result.getEdges().size();j++){
                Edge e2 = result.getEdges().get(j);
                Line2D.Double l2 = new Line2D.Double(e2.getFrom().getLocation(), e2.getTo().getLocation()); //vertical line has undefined slope
                if(l1.intersectsLine(l2)){
                    double x1E2 = e2.getFrom().getLocation().x;
                    double y1E2 = e2.getFrom().getLocation().y;
                    double x2E2 = e2.getTo().getLocation().x;
                    double y2E2 = e2.getTo().getLocation().y;
                    double m2 = (y2E2 - y1E2)/(x2E2 - x1E2);
                    double equationE2[] = {m2, (m2 * x1E2 - y1E2)*-1}; //[0] = x, [1] = constant
                    boolean isVerticalLineE2 = false;
                    if(x1E2==x2E2)isVerticalLineE2 = true;
                    double x = 0;
                    double y = 0;
                    if(isVerticalLineE1){
                        x = x1E1;
                        y = equationE2[0] * x + (equationE2[1]);
                    }
                    else if(isVerticalLineE2){
                        x = x1E2;
                        y = equationE1[0] * x + (equationE1[1]);
                    }
                    else{
                        x = (equationE1[1]-equationE2[1])/(equationE2[0]-equationE1[0]);
                        y = equationE1[0] * x + (equationE1[1]);
                    }
                    if(!Double.isNaN(x) && !Double.isNaN(y)){
                        pointIntersection.add(new Point2D.Double(x, y));
                        edge1Intersection.add(e1);
                        edge2Intersection.add(e2);
                    }
                }
            }
        }
        ArrayList<Edge> removed = new ArrayList<>();
        for(int i=0;i<pointIntersection.size();i++){    
            makeIntersection(pointIntersection.get(i), edge1Intersection.get(i), edge2Intersection.get(i), removed);
        }
        for(Edge e: removed){
            result.removeEdge(e);
        }
    }
    
    private void makeIntersection(Point2D.Double p, Edge e1, Edge e2, ArrayList<Edge> removed){
        double x = p.x;
        double y = p.y;
        Vertex newVertex = new Vertex(new Point2D.Double(x, y));
        if(result.getVertices().contains(newVertex)){
            newVertex = result.getVertices().get(result.getVertices().indexOf(newVertex));
        }
        else{
            result.addVertex(newVertex);
        }
        if(result.addEdge(new Edge(e1.getFrom(), newVertex)) && result.addEdge(new Edge(newVertex, e1.getTo()))){
            removed.add(e1);
        }
        if(result.addEdge(new Edge(e2.getFrom(), newVertex)) && result.addEdge(new Edge(newVertex, e2.getTo()))){
            removed.add(e2);
        }
    }
    
    private void makeRect(Element cur) {
        double x = 0;
        double y = 0;
        double width = 0;
        double height = 0;
        String found = cur.getAttributes().get("x");
        if(found != null){
            x = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("y");
        if(found != null){
            y = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("width");
        if(found != null){
            width = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("height");
        if(found != null){
            height = Double.parseDouble(found);
        }
        
        Vertex v1, v2, v3, v4;
        v1 = new Vertex(new Point2D.Double(x, y));
        v2 = new Vertex(new Point2D.Double(x+width, y));
        v3 = new Vertex(new Point2D.Double(x+width, y+height));
        v4 = new Vertex(new Point2D.Double(x, y+height));
        
        this.result.addVertex(v1);
        this.result.addVertex(v2);
        this.result.addVertex(v3);
        this.result.addVertex(v4);
        this.result.addEdge(new Edge(v1, v2));
        this.result.addEdge(new Edge(v2, v3));
        this.result.addEdge(new Edge(v3, v4));
        this.result.addEdge(new Edge(v1, v4));
    }

    private void makeEllipse(Element cur) {
        double cx = 0;
        double cy = 0;
        double rx = 0;
        double ry = 0;
        String found = cur.getAttributes().get("cx");
        if(found != null){
            cx = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("cy");
        if(found != null){
            cy = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("rx");
        if(found != null){
            rx = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("ry");
        if(found != null){
            ry = Double.parseDouble(found);
        }
        
        Vertex v1, v2, v3, v4, v5, v6, v7, v8;
        v1 = new Vertex(new Point2D.Double(cx, cy-ry));
        v2 = new Vertex(new Point2D.Double(cx+(rx*Math.cos(Math.toRadians(45))), cy-(ry*Math.sin(Math.toRadians(45)))));
        v3 = new Vertex(new Point2D.Double(cx+rx, cy));
        v4 = new Vertex(new Point2D.Double(cx+(rx*Math.cos(Math.toRadians(45))), cy+(ry*Math.sin(Math.toRadians(45)))));
        v5 = new Vertex(new Point2D.Double(cx, cy+ry));
        v6 = new Vertex(new Point2D.Double(cx-(rx*Math.cos(Math.toRadians(45))), cy+(ry*Math.sin(Math.toRadians(45)))));
        v7 = new Vertex(new Point2D.Double(cx-rx, cy));
        v8 = new Vertex(new Point2D.Double(cx-(rx*Math.cos(Math.toRadians(45))), cy-(ry*Math.sin(Math.toRadians(45)))));
        this.result.addVertex(v1);
        this.result.addVertex(v2);
        this.result.addVertex(v3);
        this.result.addVertex(v4);
        this.result.addVertex(v5);
        this.result.addVertex(v6);
        this.result.addVertex(v7);
        this.result.addVertex(v8);
        Edge e1, e2, e3, e4, e5, e6, e7, e8;
        e1 = new Edge(v1, v2);
        e2 = new Edge(v2, v3);
        e3 = new Edge(v3, v4);
        e4 = new Edge(v4, v5);
        e5 = new Edge(v5, v6);
        e6 = new Edge(v6, v7);
        e7 = new Edge(v7, v8);
        e8 = new Edge(v8, v1);
        this.result.addEdge(e1);
        this.result.addEdge(e2);
        this.result.addEdge(e3);
        this.result.addEdge(e4);
        this.result.addEdge(e5);
        this.result.addEdge(e6);
        this.result.addEdge(e7);
        this.result.addEdge(e8);
    }

    private void makePolygon(Element cur) {
        ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
        String found = cur.getAttributes().get("points");
        if(found!= null){
            String inputs[] = found.split("\\s+");
            int inputsLen = inputs.length;
            for(int i=0;i<inputsLen;i+=2){
                points.add(new Point2D.Double(Double.parseDouble(inputs[i]), Double.parseDouble(inputs[i+1])));
            }

            int numOfPoints = points.size();
            Vertex current = null;
            Vertex previous = null;
            Vertex first = null;
            for(int i=0;i<numOfPoints;i++){
                current = new Vertex(points.get(i));
                this.result.addVertex(current);
                if(i>0){
                    Edge e = new Edge(previous, current);
                    this.result.addEdge(e);
                }
                else{
                    first = current;
                }
                previous = current;
            }
            this.result.addEdge(new Edge(first, current));
        }
    }

    private void makeLine(Element cur) {
        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;
        String found = cur.getAttributes().get("x1");
        if(found != null){
            x1 = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("y1");
        if(found != null){
            y1 = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("x2");
        if(found != null){
            x2 = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("y2");
        if(found != null){
            y2 = Double.parseDouble(found);
        }
        
        Vertex v1, v2;
        v1 = new Vertex(new Point2D.Double(x1, y1));
        v2 = new Vertex(new Point2D.Double(x2, y2));
        this.result.addVertex(v1);
        this.result.addVertex(v2);
        this.result.addEdge(new Edge(v1, v2));
    }

    private void makePolyline(Element cur) {
        ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
        String found = cur.getAttributes().get("points");
        if(found!= null){
            String inputs[] = found.split("\\s+");
            int inputsLen = inputs.length;
            for(int i=0;i<inputsLen;i+=2){
                points.add(new Point2D.Double(Double.parseDouble(inputs[i]), Double.parseDouble(inputs[i+1])));
            }

            int numOfPoints = points.size();
            Vertex current = null;
            Vertex previous = null;
            for(int i=0;i<numOfPoints;i++){
                current = new Vertex(points.get(i));
                this.result.addVertex(current);
                if(i>0){
                    Edge e = new Edge(previous, current);
                    this.result.addEdge(e);
                }
                previous = current;
            }
        }
    }

    private void makeCircle(Element cur) {
        double cx = 0;
        double cy = 0;
        double r = 0;
        String found = cur.getAttributes().get("cx");
        if(found != null){
            cx = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("cy");
        if(found != null){
            cy = Double.parseDouble(found);
        }
        found = cur.getAttributes().get("r");
        if(found != null){
            r = Double.parseDouble(found);
        }
        
        Vertex v1, v2, v3, v4, v5, v6, v7, v8;
        v1 = new Vertex(new Point2D.Double(cx, cy-r));
        v2 = new Vertex(new Point2D.Double(cx+(r*Math.cos(Math.toRadians(45))), cy-(r*Math.sin(Math.toRadians(45)))));
        v3 = new Vertex(new Point2D.Double(cx+r, cy));
        v4 = new Vertex(new Point2D.Double(cx+(r*Math.cos(Math.toRadians(45))), cy+(r*Math.sin(Math.toRadians(45)))));
        v5 = new Vertex(new Point2D.Double(cx, cy+r));
        v6 = new Vertex(new Point2D.Double(cx-(r*Math.cos(Math.toRadians(45))), cy+(r*Math.sin(Math.toRadians(45)))));
        v7 = new Vertex(new Point2D.Double(cx-r, cy));
        v8 = new Vertex(new Point2D.Double(cx-(r*Math.cos(Math.toRadians(45))), cy-(r*Math.sin(Math.toRadians(45)))));
        this.result.addVertex(v1);
        this.result.addVertex(v2);
        this.result.addVertex(v3);
        this.result.addVertex(v4);
        this.result.addVertex(v5);
        this.result.addVertex(v6);
        this.result.addVertex(v7);
        this.result.addVertex(v8);
        Edge e1, e2, e3, e4, e5, e6, e7, e8;
        e1 = new Edge(v1, v2);
        e2 = new Edge(v2, v3);
        e3 = new Edge(v3, v4);
        e4 = new Edge(v4, v5);
        e5 = new Edge(v5, v6);
        e6 = new Edge(v6, v7);
        e7 = new Edge(v7, v8);
        e8 = new Edge(v8, v1);
        this.result.addEdge(e1);
        this.result.addEdge(e2);
        this.result.addEdge(e3);
        this.result.addEdge(e4);
        this.result.addEdge(e5);
        this.result.addEdge(e6);
        this.result.addEdge(e7);
        this.result.addEdge(e8);
    }
    
    private void makePath(Element cur) {
        String cmd[];
        String found = cur.getAttributes().get("d");
        if(found != null){
            cmd = found.split("\\s+");
            int cmdLen = cmd.length;
            ArrayList<PathCommandGroup> groups = new ArrayList<PathCommandGroup>();
            double cumulativeX = 0;
            double cumulativeY = 0;
            String curCommand = "";
            String curProcessed = "";
            int numOfGroups = 0;
            int i = 0;
            while(i<cmdLen){
                curProcessed = cmd[i];
                if(Character.isAlphabetic(curProcessed.charAt(0))){
                    groups.add(new PathCommandGroup(cmd[i].toUpperCase()));
                    curCommand = cmd[i];
                    numOfGroups++;
                    i++;
                }
                else{
                    double currentProcessed = Double.parseDouble(curProcessed);
                    if(curCommand.equals("H")){
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed, groups.get(numOfGroups-2).getLastYCoordinate()));
                        cumulativeX = currentProcessed;
                        i++;
                    }
                    else if(curCommand.equals("h")){
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed + cumulativeX, cumulativeY));
                        cumulativeX += currentProcessed;
                        i++;
                    }
                    else if(curCommand.equals("V")){
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(groups.get(numOfGroups-2).getLastXCoordinate(), currentProcessed));
                        cumulativeY = currentProcessed;
                        i++;
                    }
                    else if(curCommand.equals("v")){
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(cumulativeX, currentProcessed + cumulativeY));
                        cumulativeY += currentProcessed;
                        i++;
                    }
                    else if(curCommand.equals("A")){
                        double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                        double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                        double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                        groups.get(numOfGroups-1).setRx(currentProcessed);
                        groups.get(numOfGroups-1).setRy(currentProcessed2);
                        groups.get(numOfGroups-1).setDegree(Double.parseDouble(cmd[i+2]));
                        groups.get(numOfGroups-1).setLarArcFlag(Integer.parseInt(cmd[i+3]));
                        groups.get(numOfGroups-1).setSweepFlag(Integer.parseInt(cmd[i+4]));
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed3, currentProcessed4));
                        cumulativeX = currentProcessed3;
                        cumulativeY = currentProcessed4;
                        i+=7;
                    }
                    else if(curCommand.equals("a")){
                        double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                        double currentProcessed3 = Double.parseDouble(cmd[i+5]);
                        double currentProcessed4 = Double.parseDouble(cmd[i+6]);
                        groups.get(numOfGroups-1).setRx(currentProcessed);
                        groups.get(numOfGroups-1).setRy(currentProcessed2);
                        groups.get(numOfGroups-1).setDegree(Double.parseDouble(cmd[i+2]));
                        groups.get(numOfGroups-1).setLarArcFlag(Integer.parseInt(cmd[i+3]));
                        groups.get(numOfGroups-1).setSweepFlag(Integer.parseInt(cmd[i+4]));
                        groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed3 + cumulativeX, currentProcessed4 + cumulativeY));
                        cumulativeX += currentProcessed3;
                        cumulativeY += currentProcessed4;
                        i+=7;
                    }
                    else{
                        double currentProcessed2 = Double.parseDouble(cmd[i+1]);
                        if(Character.isLowerCase(curCommand.charAt(0))){
                            groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed + cumulativeX, currentProcessed2 + cumulativeY));
                            if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                                cumulativeX += currentProcessed;
                                cumulativeY += currentProcessed2;
                            }
                        }
                        else{
                            groups.get(numOfGroups-1).addCoordinate(new Point2D.Double(currentProcessed, currentProcessed2));
                            if(i >= cmdLen-2 || Character.isAlphabetic(cmd[i+2].charAt(0))){
                                cumulativeX = currentProcessed;
                                cumulativeY = currentProcessed2;
                            }
                        }
                        i+=2;
                    }
                }
            }

            Vertex last = null;
            Vertex first = null;
            for(i=0;i<numOfGroups;i++){
                PathCommandGroup cmdGroup = groups.get(i);
                if(i==0 && !cmdGroup.getCommand().equals("M")){
                    break;
                }
                if(cmdGroup.getCommand().equals("M")){
                    first = makePathMoveTo(cmdGroup);
                    last = first;
                }
                else if(cmdGroup.getCommand().equals("L") || cmdGroup.getCommand().equals("H") || cmdGroup.getCommand().equals("V")){
                    last = makePathLineTo(last, cmdGroup);
                }
                else if(cmdGroup.getCommand().equals("C")){
                    last = makePathCubicCurveTo(last, cmdGroup);
                }
                else if(cmdGroup.getCommand().equals("S")){
                    last = makePathSmoothCurveTo(last, cmdGroup, groups.get(i-1));
                }
                else if(cmdGroup.getCommand().equals("Q")){
                    last = makePathQuadraticCurveTo(last, cmdGroup);
                }
                else if(cmdGroup.getCommand().equals("T")){
                    last = makePathCurveTo(last, cmdGroup, groups.get(i-1));
                }
                else if(cmdGroup.getCommand().equals("A")){
                    last = makePathEllipticalArc(last, cmdGroup);
                }
                else if(cmdGroup.getCommand().equals("Z")){
                    this.result.addEdge(new Edge(last, first));
                }
            }
        }
    }

    private Vertex makePathMoveTo(PathCommandGroup cmdGroup) {
        Vertex beginPath = new Vertex(cmdGroup.coordinates.get(0));
        this.result.addVertex(beginPath);
        if(cmdGroup.coordinates.size()==2){
            Vertex endBeginPath = new Vertex(cmdGroup.coordinates.get(1));
            this.result.addVertex(endBeginPath);
            this.result.addEdge(new Edge(beginPath, endBeginPath));
            return endBeginPath;
        }
        else{
            return beginPath;
        }
    }

    private Vertex makePathLineTo(Vertex last, PathCommandGroup cmdGroup) {
        Vertex dest = new Vertex(cmdGroup.coordinates.get(0));
        this.result.addVertex(dest);
        this.result.addEdge(new Edge(last, dest));
        return dest;
    }

    private Vertex makePathCubicCurveTo(Vertex last, PathCommandGroup cmdGroup) {
        Point2D.Double controlPoints[] = {last.getLocation(), cmdGroup.coordinates.get(0), cmdGroup.coordinates.get(1), cmdGroup.coordinates.get(2)};
        Vertex beginCurve, endCurve, middle, leftMiddle, rightMiddle;
        beginCurve = last;
        leftMiddle = new Vertex(getCubicBezierCurvesPoint(controlPoints, 0.25));
        middle = new Vertex(getCubicBezierCurvesPoint(controlPoints, 0.5));
        rightMiddle = new Vertex(getCubicBezierCurvesPoint(controlPoints, 0.75));
        endCurve = new Vertex(cmdGroup.getLastCoordinate());
        this.result.addVertex(leftMiddle);
        this.result.addVertex(middle);
        this.result.addVertex(rightMiddle);
        this.result.addVertex(endCurve);
        this.result.addEdge(new Edge(beginCurve, leftMiddle));
        this.result.addEdge(new Edge(leftMiddle, middle));
        this.result.addEdge(new Edge(middle, rightMiddle));
        this.result.addEdge(new Edge(rightMiddle, endCurve));
        return endCurve;
    }

    private Vertex makePathSmoothCurveTo(Vertex last, PathCommandGroup cmdGroup, PathCommandGroup prevCmdGroup) {
        if(prevCmdGroup.getCommand().equals("C") || prevCmdGroup.getCommand().equals("S")){
            Point2D.Double prevCtrlPoint = prevCmdGroup.getSecondLastCoordinate();
            Point2D.Double curStart = prevCmdGroup.getLastCoordinate();
            Point2D.Double curCtrlPoint = mirrorControlPoint(prevCtrlPoint, curStart);
            cmdGroup.addFirstCoordinate(curCtrlPoint);
            return makePathCubicCurveTo(last, cmdGroup);
        }
        else{
            return makePathQuadraticCurveTo(last, cmdGroup);
        }
    }

    private Vertex makePathQuadraticCurveTo(Vertex last, PathCommandGroup cmdGroup) {
        Point2D.Double controlPoints[] = {last.getLocation(), cmdGroup.coordinates.get(0), cmdGroup.coordinates.get(1)};
        Vertex beginCurve, endCurve, middle, leftMiddle, rightMiddle;
        beginCurve = last;
        leftMiddle = new Vertex(getQuadraticBezierCurvesPoint(controlPoints, 0.25));
        middle = new Vertex(getQuadraticBezierCurvesPoint(controlPoints, 0.5));
        rightMiddle = new Vertex(getQuadraticBezierCurvesPoint(controlPoints, 0.75));
        endCurve = new Vertex(cmdGroup.getLastCoordinate());
        this.result.addVertex(leftMiddle);
        this.result.addVertex(middle);
        this.result.addVertex(rightMiddle);
        this.result.addVertex(endCurve);
        this.result.addEdge(new Edge(beginCurve, leftMiddle));
        this.result.addEdge(new Edge(leftMiddle, middle));
        this.result.addEdge(new Edge(middle, rightMiddle));
        this.result.addEdge(new Edge(rightMiddle, endCurve));
        return endCurve;
    }

    private Vertex makePathCurveTo(Vertex last, PathCommandGroup cmdGroup, PathCommandGroup prevCmdGroup) {
        if(prevCmdGroup.getCommand().equals("Q") || prevCmdGroup.getCommand().equals("T")){
            Point2D.Double prevCtrlPoint = prevCmdGroup.getSecondLastCoordinate();
            Point2D.Double curStart = prevCmdGroup.getLastCoordinate();
            Point2D.Double curCtrlPoint = mirrorControlPoint(prevCtrlPoint, curStart);
            cmdGroup.addFirstCoordinate(curCtrlPoint);
            return makePathQuadraticCurveTo(last, cmdGroup);
        }
        else{
            return makePathLineTo(last, cmdGroup);
        }
    }

    private void ensureRadii(Vertex last, PathCommandGroup cmdGroup){
        if(cmdGroup.getCommand().equals("A")){
            double x1 = last.getLocation().x;
            double y1 = last.getLocation().y;
            double x2 = cmdGroup.getLastCoordinate().x;
            double y2 = cmdGroup.getLastCoordinate().y;
            double rx = cmdGroup.getRx();
            double ry = cmdGroup.getRy();
            double varphi = cmdGroup.getDegree();
            int fA = cmdGroup.getLarArcFlag();
            int fS = cmdGroup.getSweepFlag();

            double matrix[][] = new double[2][2];
            matrix[0][0] = Math.cos(varphi);
            matrix[0][1] = Math.sin(varphi);
            matrix[1][0] = Math.sin(varphi) * -1;
            matrix[1][1] = Math.cos(varphi);
            Matrix m1 = new Matrix(matrix);
            matrix = new double[2][1];
            matrix[0][0] = (x1-x2)/2.0;
            matrix[1][0] = (y1-y2)/2.0;
            Matrix m2 = new Matrix(matrix);
            Matrix coordinateA = MatrixMath.multiply(m1, m2);
            double x1A = coordinateA.getMatrix()[0][0];
            double y1A = coordinateA.getMatrix()[1][0];
            
            rx = Math.abs(rx);
            ry = Math.abs(ry);
            double radii = Math.pow(x1A, 2)/Math.pow(rx, 2) + Math.pow(y1A, 2)/Math.pow(ry, 2);
            if(radii>1){
                rx = Math.sqrt(radii) * rx;
                ry = Math.sqrt(radii) * ry;
            }
            cmdGroup.setRx(rx);
            cmdGroup.setRy(ry);
        }
    }
    
    private Vertex makePathEllipticalArc(Vertex last, PathCommandGroup cmdGroup) {
        ensureRadii(last, cmdGroup);
        Matrix centerPoint = findArcCenterPoint(last, cmdGroup);
        double rx = cmdGroup.getRx();
        double ry = cmdGroup.getRy();
        double varphi = cmdGroup.getDegree();
        int fS = cmdGroup.getSweepFlag();
        int fA = cmdGroup.getLarArcFlag();
        double theta1 = findArcStartAngle(last, cmdGroup);
        double deltaTheta = findArcDeltaTheta(last, cmdGroup);
        int parts = (int)Math.abs((Math.toDegrees(deltaTheta)/45));
        double val = deltaTheta/parts;
        double r[][] = new double[2][2];
        r[0][0] = Math.cos(varphi);
        r[0][1] = -1*Math.sin(varphi);
        r[1][0] = Math.sin(varphi);
        r[1][1] = Math.cos(varphi);
        Matrix mR = new Matrix(r);

        Vertex u = last;
        Vertex v = last;
        double c[][];
        for(int i=1;i<=parts-1;i++){
            c = new double[2][1];
            c[0][0] = rx*Math.cos(theta1+(i*val));
            c[1][0] = ry*Math.sin(theta1+(i*val));
            Matrix mC = new Matrix(c);
            Matrix res = MatrixMath.multiply(mR, mC);
            res = MatrixMath.add(res, centerPoint);
            v = new Vertex(new Point2D.Double(res.getMatrix()[0][0], res.getMatrix()[1][0]));
            this.result.addVertex(v);
            this.result.addEdge(new Edge(u, v));
            u = v;
        }
        Vertex endArc = new Vertex(cmdGroup.getLastCoordinate());
        this.result.addVertex(endArc);
        this.result.addEdge(new Edge(v, endArc));
        return endArc;
    }
    
    private double findArcDeltaTheta(Vertex last, PathCommandGroup cmdGroup){
        double x1 = last.getLocation().x;
        double y1 = last.getLocation().y;
        double x2 = cmdGroup.getLastCoordinate().x;
        double y2 = cmdGroup.getLastCoordinate().y;
        double rx = cmdGroup.getRx();
        double ry = cmdGroup.getRy();
        double varphi = cmdGroup.getDegree();
        int fA = cmdGroup.getLarArcFlag();
        int fS = cmdGroup.getSweepFlag();
        
        double matrix[][] = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi) * -1;
        matrix[1][1] = Math.cos(varphi);
        Matrix m1 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1-x2)/2.0;
        matrix[1][0] = (y1-y2)/2.0;
        Matrix m2 = new Matrix(matrix);
        Matrix coordinateA = MatrixMath.multiply(m1, m2);
        double x1A = coordinateA.getMatrix()[0][0];
        double y1A = coordinateA.getMatrix()[1][0];
        
        double val = Math.sqrt( ((rx*rx*ry*ry) - (rx*rx*y1A*y1A) - (ry*ry*x1A*x1A)) / ((rx*rx*y1A*y1A) + (ry*ry*x1A*x1A)) );
        if(fA==fS){
            val *= -1;
        }
        matrix = new double[2][1];
        matrix[0][0] = rx*y1A/ry;
        matrix[1][0] = -1*(ry*x1A/rx);
        Matrix m3 = new Matrix(matrix);
        Matrix centerPointA = MatrixMath.multiply(m3, val);
        double cxA = centerPointA.getMatrix()[0][0];
        double cyA = centerPointA.getMatrix()[1][0];
        
        
        
        
        matrix = new double[2][1];
        matrix[0][0] = (x1A - cxA)/rx;
        matrix[1][0] = (y1A-cyA)/ry;
        Matrix vU = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = ((-1*x1A)-cxA)/rx;
        matrix[1][0] = ((-1*y1A)-cyA)/ry;
        Matrix vV = new Matrix(matrix);
        
        double dotProduct = MatrixMath.dotProduct(vU, vV);
        double vULength = MatrixMath.vectorLength(vU);
        double vVLength = MatrixMath.vectorLength(vV);
        double result = Math.acos(dotProduct / vULength * vVLength);
        
        if((vU.getMatrix()[0][0] * vV.getMatrix()[1][0] - vU.getMatrix()[1][0] * vV.getMatrix()[0][0])<0){
            result *= -1;
        }
        
        //mod 360
        if(fS==0 && result>0){
            result -= 2*Math.PI;
        }
        else if(fS==1 && result<0){
            result += 2*Math.PI;
        }
        result %= 2*Math.PI;
        
        return result;
    }
    
    private double findArcStartAngle(Vertex last, PathCommandGroup cmdGroup){
        double x1 = last.getLocation().x;
        double y1 = last.getLocation().y;
        double x2 = cmdGroup.getLastCoordinate().x;
        double y2 = cmdGroup.getLastCoordinate().y;
        double rx = cmdGroup.getRx();
        double ry = cmdGroup.getRy();
        double varphi = cmdGroup.getDegree();
        int fA = cmdGroup.getLarArcFlag();
        int fS = cmdGroup.getSweepFlag();
        
        double matrix[][] = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi) * -1;
        matrix[1][1] = Math.cos(varphi);
        Matrix m1 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1-x2)/2.0;
        matrix[1][0] = (y1-y2)/2.0;
        Matrix m2 = new Matrix(matrix);
        Matrix coordinateA = MatrixMath.multiply(m1, m2);
        double x1A = coordinateA.getMatrix()[0][0];
        double y1A = coordinateA.getMatrix()[1][0];
        
        double val = Math.sqrt( ((rx*rx*ry*ry) - (rx*rx*y1A*y1A) - (ry*ry*x1A*x1A)) / ((rx*rx*y1A*y1A) + (ry*ry*x1A*x1A)) );
        if(fA==fS){
            val *= -1;
        }
        matrix = new double[2][1];
        matrix[0][0] = rx*y1A/ry;
        matrix[1][0] = -1*(ry*x1A/rx);
        Matrix m3 = new Matrix(matrix);
        Matrix centerPointA = MatrixMath.multiply(m3, val);
        double cxA = centerPointA.getMatrix()[0][0];
        double cyA = centerPointA.getMatrix()[1][0];
        
        matrix = new double[2][1];
        matrix[0][0] = 1;
        matrix[1][0] = 0;
        Matrix vU = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1A-cxA)/rx;
        matrix[1][0] = (y1A-cyA)/ry;
        Matrix vV = new Matrix(matrix);
        
        double dotProduct = MatrixMath.dotProduct(vU, vV);
        double vULength = MatrixMath.vectorLength(vU);
        double vVLength = MatrixMath.vectorLength(vV);
        double result = Math.acos(dotProduct / vULength * vVLength);
        
        if((vU.getMatrix()[0][0] * vV.getMatrix()[1][0] - vU.getMatrix()[1][0] * vV.getMatrix()[0][0])<0){
            result *= -1;
        }
        return result;
    }
    
    private Matrix findArcCenterPoint(Vertex last, PathCommandGroup cmdGroup){
        double x1 = last.getLocation().x;
        double y1 = last.getLocation().y;
        double x2 = cmdGroup.getLastCoordinate().x;
        double y2 = cmdGroup.getLastCoordinate().y;
        double rx = cmdGroup.getRx();
        double ry = cmdGroup.getRy();
        double varphi = cmdGroup.getDegree();
        int fA = cmdGroup.getLarArcFlag();
        int fS = cmdGroup.getSweepFlag();
        
        //find x1' and y1'
        double matrix[][] = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi) * -1;
        matrix[1][1] = Math.cos(varphi);
        Matrix m1 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1-x2)/2.0;
        matrix[1][0] = (y1-y2)/2.0;
        Matrix m2 = new Matrix(matrix);
        Matrix coordinateA = MatrixMath.multiply(m1, m2);
        double x1A = coordinateA.getMatrix()[0][0];
        double y1A = coordinateA.getMatrix()[1][0];
        
        //find cx' and cy'
        double val = Math.sqrt( ((rx*rx*ry*ry) - (rx*rx*y1A*y1A) - (ry*ry*x1A*x1A)) / ((rx*rx*y1A*y1A) + (ry*ry*x1A*x1A)) );
        if(fA==fS){
            val *= -1;
        }
        matrix = new double[2][1];
        matrix[0][0] = rx*y1A/ry;
        matrix[1][0] = -1*(ry*x1A/rx);
        Matrix m3 = new Matrix(matrix);
        Matrix centerPointA = MatrixMath.multiply(m3, val);
        
        //find center points
        matrix = new double[2][2];
        matrix[0][0] = Math.cos(varphi);
        matrix[0][1] = -1 * Math.sin(varphi);
        matrix[1][0] = Math.sin(varphi);
        matrix[1][1] = Math.cos(varphi);
        Matrix m4 = new Matrix(matrix);
        matrix = new double[2][1];
        matrix[0][0] = (x1+x2)/2.0;
        matrix[1][0] = (y1+y2)/2.0;
        Matrix m5 = new Matrix(matrix);
        Matrix centerPoint = MatrixMath.multiply(m4, centerPointA);
        centerPoint = MatrixMath.add(centerPoint, m5);
        return centerPoint;
    }
    
    private Point2D.Double getQuadraticBezierCurvesPoint(Point2D.Double controlPoints[], double t){
        double resX = 0;
        double resY = 0;
        resX = Math.pow((1-t), 2) * controlPoints[0].x + 2 * t * (1-t) * controlPoints[1].x + Math.pow(t, 2) * controlPoints[2].x;
        resY = Math.pow((1-t), 2) * controlPoints[0].y + 2 * t * (1-t) * controlPoints[1].y + Math.pow(t, 2) * controlPoints[2].y;
        return new Point2D.Double(resX, resY);
    }
    
    private Point2D.Double getCubicBezierCurvesPoint(Point2D.Double controlPoints[], double t){
        double resX = 0;
        double resY = 0;
        resX = Math.pow((1-t), 3) * controlPoints[0].x + 3 * t * Math.pow((1-t), 2) * controlPoints[1].x + 3 * Math.pow(t, 2) * (1-t) * controlPoints[2].x + Math.pow(t, 3) * controlPoints[3].x; 
        resY = Math.pow((1-t), 3) * controlPoints[0].y + 3 * t * Math.pow((1-t), 2) * controlPoints[1].y + 3 * Math.pow(t, 2) * (1-t) * controlPoints[2].y + Math.pow(t, 3) * controlPoints[3].y;
        return new Point2D.Double(resX, resY);
    }

    private Point2D.Double mirrorControlPoint(Point2D.Double prevCtrlPoint, Point2D.Double curStart) {
        Point2D.Double res = new Point2D.Double();
        res.x = curStart.x - (prevCtrlPoint.x - curStart.x);
        res.y = curStart.y - (prevCtrlPoint.y - curStart.y);
        return res;
    }
    
    class PathCommandGroup{
        private String command;
        private ArrayList<Point2D.Double> coordinates;
        private double degree;
        private int larArcFlag;
        private int sweepFlag;
        private double rx;
        private double ry;
        
        
        public PathCommandGroup(String command){
            this.command = command;
            this.coordinates = new ArrayList<Point2D.Double>();
        }

        public double getRx() {
            return rx;
        }

        public void setRx(double rx) {
            this.rx = rx;
        }

        public double getRy() {
            return ry;
        }

        public void setRy(double ry) {
            this.ry = ry;
        }
        
        public void addCoordinate(Point2D.Double newPoint){
            this.coordinates.add(newPoint);
        }
        
        public void addFirstCoordinate(Point2D.Double newPoint){
            this.coordinates.add(0, newPoint);
        }
        
        public Point2D.Double getSecondLastCoordinate(){
            return this.coordinates.get(this.coordinates.size()-2);
        }
        
        public Point2D.Double getLastCoordinate(){
            return this.coordinates.get(this.coordinates.size()-1);
        }
        
        public double getLastXCoordinate(){
            return this.coordinates.get(this.coordinates.size()-1).x;
        }
        
        public double getLastYCoordinate(){
            return this.coordinates.get(this.coordinates.size()-1).y;
        }

        public String getCommand() {
            return command;
        }

        public ArrayList<Point2D.Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(ArrayList<Point2D.Double> coordinates) {
            this.coordinates = coordinates;
        }

        public double getDegree() {
            return degree;
        }

        public void setDegree(double degree) {
            this.degree = Math.toRadians(degree);
        }

        public int getLarArcFlag() {
            return larArcFlag;
        }

        public void setLarArcFlag(int larArcFlag) {
            this.larArcFlag = larArcFlag;
        }

        public int getSweepFlag() {
            return sweepFlag;
        }

        public void setSweepFlag(int sweepFlag) {
            this.sweepFlag = sweepFlag;
        }
    }
}
