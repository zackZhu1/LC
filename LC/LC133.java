class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>(); // 1. mark visited 2. mapping
        dfs(node, map);
        return map.get(node);
    }
    
    private void dfs(Node node, Map<Node, Node> map) {
        // base case
        if (map.get(node) != null) return; // already visited
        
        // mark visited
        Node copy = new Node(node.val);
        map.put(node, copy);
        
        for (Node nei : node.neighbors) {
            dfs(nei, map);
            copy.neighbors.add(map.get(nei));
        }
    }
}















