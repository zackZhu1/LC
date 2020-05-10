// side topic: DFS mark visited 2种方法
// 先走到点 再check
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        dfs(node, map);
        return map.get(node);
    }
    
    private void dfs(Node node, Map<Node, Node> map) {
        if (map.get(node) != null) return; // already visited
        
        // mark visited
        Node copy = new Node(node.val);
        map.put(node, copy); 
        
        for (Node nei : node.neighbors) {
            dfs(nei, map);
            copy.neighbors.add(map.get(nei)); // node.neighbors.add(nei)
        }
    }
}

// 先check再走过去
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        dfs(node, map);
        return map.get(node);
    }
    
    private void dfs(Node node, Map<Node, Node> map) {
        for (Node nei : node.neighbors) {
            if (!map.containsKey(nei)) {
                map.put(nei, new Node(nei.val));
                dfs(nei, map);
            }
            map.get(node).neighbors.add(map.get(nei)); // 此时node必须在map里 所以map.put(node,..)
        }
    }
}
