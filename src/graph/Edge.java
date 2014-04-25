package graph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class Edge {
    
    private Vertex v1, v2;

    public boolean hasVertex(Vertex vertex){
        if(vertex == v1)
            return true;
        else if(vertex == v2)
            return true;
        else 
            return false;
    }
    
    public Vertex getVertex1() {
        return v1;
    }

    public Vertex getVertex2() {
        return v2;
    }

    public void setVertex1(Vertex v1) {
        this.v1 = v1;
    }

    public void setVertex2(Vertex v2) {
        this.v2 = v2;
    }
    
    
}
