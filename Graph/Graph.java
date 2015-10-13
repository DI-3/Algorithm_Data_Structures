import java.util.*;
class Graph{
	
  int V ; // no of vertices
  ArrayList<LinkedList<Integer>> adj ; // ArrayList of adjacency lists

  public Graph(int v){
    
    V = v;
    adj = new ArrayList<LinkedList<Integer>>();

    for(int i = 0 ; i < V ; i++){
    	LinkedList<Integer> lsEdges = new LinkedList<Integer>();
    	adj.add(lsEdges);
    }
  }
 
  void addEdge(int v,int w){
      adj.get(v).add(w);
  } 

  void  DFS(boolean[] visited,int v){

       visited[v] = true ;
       System.out.print(" " + v);

       LinkedList<Integer> adjList = adj.get(v);
       for(int i = 0 ; i  < adjList.size() ; i++){
            int u = adjList.get(i);
            if(!visited[u]) DFS(visited,u);

       }
  }

  void depthFirst(int v){

  	boolean[] visited = new boolean[V];
  	Arrays.fill(visited,false);
    
    DFS(visited,2);

  } 

  public static void main(String[] args){

   Graph g = new Graph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    g.depthFirst(2);
    System.out.println();

  } 

}