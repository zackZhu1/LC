// level order -> preorder
// solution1: iteration using stack
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i>= 0; i--) {
                stack.add(root.children.get(i));
            }
        }
        return list;
    }
}

// solution2: recursion like DFS
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    
    private void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        
        res.add(root.val);
        for (Node child : root.children) {
            dfs(child, res);
        }
    }
}