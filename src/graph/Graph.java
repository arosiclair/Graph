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
public class Graph {
    
    /*This holds all of the vertices within this graph. 
     *We can use the ArrayList's indices to keep track of which vertex is which
     */
    private ArrayList<Vertex> vertices;
    
    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }
    
    public boolean hasVertex(Vertex vertex){
        return vertices.contains(vertex);
    }
    
    public boolean areNeighbors(Vertex v1, Vertex v2){
        return v1.isNeighbor(v2);
    }
    
    public Vertex getVertex(int index){
        return vertices.get(index);
    }
    /*Uses Dijkstra's algorithm to construct a path in the form of an ArrayList
     *of Vertices between the from Vertex and the to Vertex.
     *
     *Ensures that both vertices are within the graph.
     */
    public ArrayList<Vertex> findPath(Vertex from, Vertex to) throws VertexNotFoundException{
        //Check if both vertices are in this graph.
        if (!hasVertex(from) || !hasVertex(to))
            throw new VertexNotFoundException();
        
        //An integer array keeping track of the distance from the origin vertex
        //The indices in this will correspond to those in the vertices ArrayList
        int[] dist = new int[vertices.size()];
        
        //A vertex Array that keeps a reference to the Vertex visited before the one at
        //the corresponding index.
        Vertex[] prev = new Vertex[vertices.size()];
        
        //initializes the two tracking arrays.
        for (int i = 0; i < vertices.size(); i++){
            dist[i] = Integer.MAX_VALUE;
            prev[i] = null;
        }
        
        //The dist from the "from" vertex to itself is 0.
        dist[0] = 0;
        int minDistIndex = 0;
        
        
        ArrayList<Vertex> clone = new ArrayList();
        for (Vertex vertex : vertices)
            clone.add(vertex.clone());
        
        Vertex u = null;
        //The Algorithm
        while (!clone.isEmpty()){
            u = clone.get(minDistIndex);
            clone.remove(u);
            if(dist[minDistIndex] == Integer.MAX_VALUE)
                break;
            
            for (Vertex neighbor : u.getNeighbors()){
                int alt = dist[minDistIndex] + 1;
                if (alt < dist[clone.indexOf(neighbor)]){
                    dist[clone.indexOf(neighbor)] = alt;
                    prev[clone.indexOf(neighbor)] = u;
                    clone.remove(neighbor);
                }
            }
        }

        //The ArrayList we will return;
        ArrayList<Vertex> path = new ArrayList();
        
        while (prev[vertices.indexOf(u)] != null){
            path.add(0, u);
            u = prev[vertices.indexOf(u)];
        }
        
        return path;       
    }
}
