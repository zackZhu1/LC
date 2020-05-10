class Solution {
    private Node first = null;
    private Node last = null;
    
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        last.right = first;
        first.left = last;
        return first;
    }
    
    private void dfs(Node node) {
        if (node == null) return;
        
        dfs(node.left);
        
        if (last == null) {
            first = node;
        } else {
            last.right = node;
            node.left = last;
        }
        last = node;
        
        dfs(node.right);
    }
}