// Number of Connected Components in an Undirected graph
// step1: build the graph
// step2: traverse the graph and find out # of connected areas
// 			algorithms: mark visited 1 两种写法

class Solution {
    public int countComponents(int n, int[][] edges) {
        // step1: construct graph
        Map<Integer, List<Integer>> graph = constructGraph(n, edges);
        // step2: DFS/BFS find connected Components
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(graph, node, visited);
                count++;
            }
        }
        return count;
    }
    
    private void dfs(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
        if (visited.contains(node)) {   // 进入之后check
            return;
        }
        visited.add(node); // mark visited
        for (Integer nei : graph.get(node)) {
            dfs(graph, nei, visited);   
        }     
    }
    
    private Map<Integer, List<Integer>> constructGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        // step1: construct graph
        Map<Integer, List<Integer>> graph = constructGraph(n, edges);
        // step2: DFS/BFS find connected Components
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer node : graph.keySet()) {
            if (visited.add(node)) {
                count++;
                dfs(graph, node, visited);
            } 
        }
        return count;
    }
    
    private void dfs(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
        for (Integer nei : graph.get(node)) {
            if (visited.add(nei)) { // 进入之前check
                dfs(graph, nei, visited);    
            }
        }     
    }
    
    private Map<Integer, List<Integer>> constructGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}


// Find the connected area with largest size
// solution1: 
public int findMaximumConnectedArea(Map<Integer, List<Integer>> graph) {
	Set<Integer> visited = new HashSet<>();
	int max = 0;
	for (Integer v : graph.keySet()) {
		if (visited.add(v)) {
			max = Math.max(max, dfs(v, graph, visited));
		}
	}
	return max;
}

private int dfs(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
	int size = 0;
	for (Integer nei : graph.get(node)) {
		if (visited.add(nei)) {
			size += dfs(graph, nei, visited);
		}
	}
	return size + 1;
}

// solution2: 
public int findMaximumConnectedArea(Map<Integer, List<Integer>> graph) {
	Set<Integer> visited = new HashSet<>();
	int max = 0;
	for (Integer v : graph.keySet()) {
		int oldSize = visited.size();
		if (visited.add(v)) {
			dfs(v, graph, visited);
		}
		int newSize = visited.size();
		max = Math.max(max, newSize - oldSize);
	}
	return max;
}




















