Graph
=====

A very basic graph implementatio using ArrayLists and references between Vertex objects. Created for my PathX Mini-Game project.

Purpose
======
This Graph data structure was created to add path finding functionality to my PathX Mini-Game. The implementation is very 
simple, using mostly ArrayLists, and path finding is done using Dijkstra's algorithm. Overall, operations for this Graph are not very fast, but fast enough for my Mini-Game's purposes.

Functionality
=====
1. Graph Class
  - addVertex
  - removeVertex
  - getVertex
  - hasVertex
  - areNeighbors
  - findPath
  - findLongestPath
2. Vertex Class
  - addNeighbor
  - removeNeighbor
  - getNeighbors
  - getDegree
  - isNeighbor
3. Edge Class
  - getVertex1
  - getVertex2
  - setVertex1
  - setVertex2
  - hasVertex
