# Challenge 35 - Topological Sort
## Description
>
This is a key advanced topic that is generally associated with graphs.

### Problem Discussion
- is a way of ordering the list of nodes such that if (a,b) is an edge in the graph then a will appear before b in the list.
- if a graph has cycles or is not directed, then there is no topological sort.
- offers a valid ordering.
- identify all nodes with no incoming edges, add to the topological sort
  - these nodes are safe to add first since they have nothing that needs to come before them
  - such a node exists if there is no cycle
- remove the node's outbound edges from the graph
  - the nodes have already been added, their edges cant impact.
- repeat above, adding nodes with no incoming edges and removing their outbound edges.  
  - once all nodes are added then the topological sort is complete.

### Problem Labels
- Topological Sort

### Problem Considerations
#### Consideration 1
- create an empty queue that is responsible for storing the valid topological sort.
- create a queue processNext.  This is responsible for storing the next nodes to process.
- Count the number of incoming edges of each node and set a variable node.inbound.  Nodes typically only store their outgoing edges.
  - can count the inbound edges by walking thorugh each node n, and for each of its outgoing edges (n, x), increment x.inbound.
- walk through the nodes again and add to the processNext any node where x.inbound == 0.
- while processNext is not empty do the following
  - for each edge (n, x) decrement x.inbound.  if x.inbound == 0, append x to processNext
  - append n to order
- if order contains all the nodes then success, otherwise sort has failed due to a cycle.