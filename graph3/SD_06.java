// DFS 解法
// note: 边的定义 两道题不同
// course schedule 
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // step1: build the graph
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        
        // step2: check if topological order exists
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();
        for (Integer course : graph.keySet()) {
            if (hasCycle(graph, visited, visiting, course)) {
                return false;
            }
        }
        
        return true;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.get(v).add(u); // v depends on u
        }
        return graph;
    }
    
    // mark visited 2
    private boolean hasCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> visiting, Integer node) {
        if (visiting.contains(node)) {
            return true; // detect cycle
        }
        
        if (visited.contains(node)) {
            return false; // already visited
        }
        
        visiting.add(node); 
        for (Integer nei : graph.get(node)) {
            if (hasCycle(graph, visited, visiting, nei)) {
                return true;
            }
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
}


// course schedule II
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> topoOrder = new ArrayList<>();
        
        // step1: build the graph
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        
        // step2: check if topological order exists
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();
        for (Integer course : graph.keySet()) {
            if (hasCycle(graph, visited, visiting, course, topoOrder)) {
                return new int[]{};
            }
        }
        
        int[] result = new int[numCourses];
        for (int i = 0; i < result.length; i++) {
            result[i] = topoOrder.get(i);
        }
        return result;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v); // u depends on v
        }
        return graph;
    }
    
    // mark visited 2
    private boolean hasCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> visiting, Integer node, List<Integer> topoOrder) {
        if (visiting.contains(node)) {
            return true; // detect cycle
        }
        
        if (visited.contains(node)) {
            return false; // already visited
        }
        
        visiting.add(node); 
        for (Integer nei : graph.get(node)) {
            if (hasCycle(graph, visited, visiting, nei, topoOrder)) {
                return true;
            }
        }
        visiting.remove(node);
        visited.add(node);
        topoOrder.add(node);
        return false;
    }
}
