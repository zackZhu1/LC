class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        buildGraph(numCourses, prerequisites, graph, indegree);
        return topologicalOrder(graph, indegree);
    }
    
    private void buildGraph(int numCourses, int[][] prerequisites, Map<Integer, List<Integer>> graph, int[] indegree) {
        for (int i = 0; i < numCourses; i++) {
            List<Integer> list = new ArrayList<>();
            graph.put(i, list);
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }
    }
    
    private boolean topologicalOrder(Map<Integer, List<Integer>> graph, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            List<Integer> children = graph.get(course);
            for (int child : children) {
                indegree[child]--;
                if (indegree[child] == 0) queue.offer(child);
            }
        }
        return count == indegree.length;
    }
}