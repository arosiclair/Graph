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
    
    public Graph(){
        vertices = new ArrayList();
    }
    
    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }
    
    public Vertex getVertex(int index){
        return vertices.get(index);
    }
    
    public boolean hasVertex(Vertex vertex){
        return vertices.contains(vertex);
    }
    
    public boolean areNeighbors(Vertex v1, Vertex v2){
        return v1.isNeighbor(v2);
    }
    
    //Returns the number of vertices in the Graph.
    public int size(){
        return vertices.size();
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
        int minIndex = 0;
        
        
        //ArrayList<Vertex> clone = new ArrayList();
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;
        
        Vertex u = null;
        int numVisited = 0;
        //The Algorithm
        while (numVisited < vertices.size()){
            
            //reset the minIndex to the first index that hasn't been visited.
            for (int i = 0; i < visited.length; i++){
                if (!visited[i]){
                    minIndex = i;
                    break;
                }
            }
            
            //Find and update minIndex
            for (int i = 0; i < dist.length; i++){
                //Check if we have already visited this node.
                if(visited[i] == true) continue;
                
                //Check if this distance is less than our current minimum
                if (dist[i] < dist[minIndex])
                    minIndex = i;
            }
            
            //The vertex with the smallest distance that we haven't visited yet.
            u = vertices.get(minIndex);
            visited[minIndex] = true;
            numVisited++;
            
            if(dist[minIndex] == Integer.MAX_VALUE)
                break;
            
            //Examine "U's" neighbors and update their distances.
            for (Vertex neighbor : u.getNeighbors()){
                int neighborIndex = vertices.indexOf(neighbor);
                int neighborDist = dist[minIndex] + 1;
                if (neighborDist < dist[neighborIndex]){
                    dist[neighborIndex] = neighborDist;
                    prev[neighborIndex] = u;
                    //visited[neighborIndex] = true;
                    //numVisited++;
                }
            }
        }

        //The ArrayList we will return;
        ArrayList<Vertex> path = new ArrayList();
        
        //We start with a reference to our destination node and then use the
        //prev array to backtrack until there is no previous node.
        Vertex stepBack = to;
        while (stepBack != null){
            path.add(0, stepBack);
            stepBack = prev[vertices.indexOf(stepBack)];
        }
        
        return path;       
    }
    
    
    /**
     * In contrast to findPath, findLongestPath uses a slightly modified version
     * of Dijkstra's algorithm to find the Vertex with the maximum distance from
     * the passed "from" Vertex.
     * @param from
     * The Vertex from which we will find another Vertex that is the farthest away.
     * @return
     * The path to the farthest Vertex in the form of an ArrayList of Vertices
     * @throws VertexNotFoundException 
     * If the from Vertex is not part of this Graph.
     */
    public ArrayList<Vertex> findLongestPath(Vertex from) throws VertexNotFoundException{
        //Check if the Vertex is within the graph.
        if (!hasVertex(from)) throw new VertexNotFoundException();
        
        //An integer array keeping track of the distance from the origin vertex
        //The indices in this will correspond to those in the vertices ArrayList
        int[] dist = new int[vertices.size()];
        
        //A vertex Array that keeps a reference to the Vertex visited before the one at
        //the corresponding index.
        Vertex[] prev = new Vertex[vertices.size()];
        
        //initializes the two tracking arrays.
        for (int i = 0; i < vertices.size(); i++){
            dist[i] = Integer.MIN_VALUE;
            prev[i] = null;
        }
        
        //The dist from the "from" vertex to itself is 0.
        dist[0] = 0;
        int maxIndex = 0;
        
        
        //ArrayList<Vertex> clone = new ArrayList();
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;
        
        Vertex u = null;
        int numVisited = 0;

        //The Algorithm
        while (numVisited < vertices.size()){
            
            //reset the maxIndex to the first index that hasn't been visited.
            for (int i = 0; i < visited.length; i++){
                if (!visited[i]){
                    maxIndex = i;
                    break;
                }
            }
            
            //Find and update maxIndex
            for (int i = 0; i < dist.length; i++){
                //Check if we have already visited this node.
                if(visited[i] == true) continue;
                
                //Check if this distance is greater than our current maximum
                if (dist[i] > dist[maxIndex])
                    maxIndex = i;
            }
            
            //The vertex with the maximum distance that we haven't visited yet.
            u = vertices.get(maxIndex);
            visited[maxIndex] = true;
            
            if(dist[maxIndex] == Integer.MIN_VALUE)
                break;
            
            //Examine "U's" neighbors and update their distances.
            for (Vertex neighbor : u.getNeighbors()){
                int neighborIndex = vertices.indexOf(neighbor);
                int neighborDist = dist[maxIndex] + 1;
                if (neighborDist > dist[neighborIndex]){
                    dist[neighborIndex] = neighborDist;
                    prev[neighborIndex] = u;
                    visited[neighborIndex] = true;
                }
            }
        }

        //The ArrayList we will return;
        ArrayList<Vertex> path = new ArrayList();
        
        //We start with a reference to our destination node and then use the
        //prev array to backtrack until there is no previous node.
        Vertex stepBack = vertices.get(maxIndex);
        
        while (stepBack != null){
            path.add(0, stepBack);
            stepBack = prev[vertices.indexOf(stepBack)];
        }
        
        return path;
    }
}
