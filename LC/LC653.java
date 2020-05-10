class Solution {
    
    private TreeNode first = null;
    private TreeNode last = null;
    
    private TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        last.right = first;
        first.left = last;
        return first;
    }
    
    private void dfs(TreeNode node) {
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
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        
        // 1. convert BST to DLL
        treeToDoublyList(root);
        
        // 2. binary search on DLL
        TreeNode left = first;
        TreeNode right = last;
        while (left != right) {
            int sum = left.val + right.val;
            if (sum == k) return true;
            else if (sum < k) left = left.right;
            else right = right.left;
        }
        return false;
    }
}