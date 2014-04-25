package graph;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class Vertex {
    
    //Connected edges
    //public ArrayList<Edge> edges;
    
    //Neighboring vertices
    private ArrayList<Vertex> neighbors;
    
    //Number of connected edges.
    private int degree = neighbors.size();
    
//    public void addEdge(Edge edge){
//        edges.add(edge);
//        
//        //Check if this edge is already connected to another Vertex.
//        
//    }
    

    public void addNeighbor(Vertex neighbor){
        neighbors.add(neighbor);
    }
    
    public boolean isNeighbor (Vertex vertex){
        return getNeighbors().contains(vertex);
    }

    public ArrayList<Vertex> getNeighbors() {
        return neighbors;
    }

    public int getDegree() {
        return degree;
    }
    
    
    
    public Vertex clone(){
        Vertex clone = new Vertex();
        for (Vertex neighbor : neighbors)
            clone.addNeighbor(neighbor);
        
        return clone;
    }

}