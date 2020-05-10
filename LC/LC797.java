// All Paths From Source to Target
// solution: DFS

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(graph, cur, res, 0);
        return res;
    }
    
    private void dfs(int[][] graph, List<Integer> cur, List<List<Integer>> res, int node) {
        if (node == graph.length - 1) {
            cur.add(node);
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        
        cur.add(node);
        for (int nei : graph[node]) {
            dfs(graph, cur, res, nei);
        }
        cur.remove(cur.size() - 1);
    }
}