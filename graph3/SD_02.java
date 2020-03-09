// Determine if a graph is a tree

// directed graph
// step1: find the root node
// step2: DFS/BFS to check 
//        1. if a node visited more than once
// 		  2. if a node is not reachable
import java.util.*;

public class test {
	
	enum State {
		UNVISITED, VISITED;
	}

	static class Vertex {
		State state = State.UNVISITED;
		int value;
		List<Vertex> neighbors = new ArrayList<>();
	}

	static Map<Vertex, Integer> getIndegree(List<Vertex> graph) {
		Map<Vertex, Integer> indegree = new HashMap<>();
		for (Vertex v : graph) {
			if (!indegree.containsKey(v)) {
				indegree.put(v, 0);
			}
			List<Vertex> neighbors = v.neighbors;
			for (Vertex nei : neighbors) {
				if (!indegree.containsKey(nei)) {
					indegree.put(nei, 1);
				} else {
					indegree.put(nei, indegree.get(nei) + 1);
				}
			}
		}
		return indegree;
	}

	static Vertex findRoot(Map<Vertex, Integer> indegree) {
		int count = 0;
		Vertex root = null;
		for (Vertex vertex : indegree.keySet()) {
	    	if (indegree.get(vertex) == 0) {
	    		root = vertex;
	    		count++;
	    	}
		}
		if (count > 1 || count == 0) {
			return null;
		} 
		return root;
	}
	
	static boolean isTree(List<Vertex> graph) {
		// step1: find the root
		Map<Vertex, Integer> indegree = getIndegree(graph);
		Vertex root = findRoot(indegree);
		if (root == null) return false;
		// step2: DFS/BFS
		Set<Vertex> visited = new HashSet<>();
		boolean flag = dfs(root, visited);
		if (visited.size() != indegree.size()) return false; // check if there's any node not reachable
		return flag;
	}
	
	static boolean dfs(Vertex node, Set<Vertex> visited) {
		if (visited.contains(node)) {
			return false; // detect a node visited more than once
		}
		visited.add(node);
		for (Vertex nei : node.neighbors) {
			if (!dfs(nei, visited)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		List<Vertex> graph = new ArrayList<>();
		Vertex v1 = new Vertex(); v1.value = 1;
		Vertex v2 = new Vertex(); v2.value = 2;
		Vertex v3 = new Vertex(); v3.value = 3;
		Vertex v4 = new Vertex(); v4.value = 4;
		Vertex v5 = new Vertex(); v5.value = 5;
		v1.neighbors.add(v2); v1.neighbors.add(v3);
		v2.neighbors.add(v4); v2.neighbors.add(v5);
		graph.add(v1); graph.add(v2); graph.add(v3); graph.add(v4); graph.add(v5);
		
		System.out.println(isTree(graph));
	}
}

// undirected graph  (LC261)
// 1. no cycle
// 2. |E| = |V| - 1
// 3. connected
// pick any 2 of these 

class Solution {
    public boolean validTree(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // first check: no cycle
        Set<Integer> visited = new HashSet<>();
        if (hasCycle(graph, 0, -1, visited)) return false;
        
        // second check: connected
        if (visited.size() != graph.size()) {
            return false;
        }
        
        return true;
    }
    
    boolean hasCycle(List<List<Integer>> graph, int node, int parent, Set<Integer> visited) {
        if (visited.contains(node)) {
            return true;
        }
        
        visited.add(node);
        for (int i = 0; i < graph.get(node).size(); i++) {
            int nei = graph.get(node).get(i);
            if (nei != parent && hasCycle(graph, nei, node, visited)) {
                return true;
            }
        }
        return false;
    }
}


