//https://www.youtube.com/watch?v=QXvbRrK1zjY
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        return dfs(root, root.val);
    }
    
    private int dfs(TreeNode root, int s1) {
        if (root == null) return -1;
        if (root.val > s1) return root.val;
        
        int left = dfs(root.left, s1);
        int right = dfs(root.right, s1);
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }
}