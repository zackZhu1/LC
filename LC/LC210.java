class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        buildGraph(numCourses, prerequisites, graph, indegree);
        
        int[] res = new int[numCourses];
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[index++] = course;
            List<Integer> children = graph.get(course);
            for (int child : children) {
                indegree[child]--;
                if (indegree[child] == 0) queue.offer(child);
            }
        }
        return index == numCourses ? res : new int[0];
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
}