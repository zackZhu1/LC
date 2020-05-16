// Sum of Root To Leaf Binary Numbers

// solution1: 从上往下传值
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        int[] sum = new int[] {0};
        dfs(root, sum, 0);
        return sum[0];
    }
    
    private void dfs(TreeNode root, int[] sum, int prevSum) {
        if (root == null) return;
        int curSum = prevSum * 2 + root.val;
        if (root.left == null && root.right == null) {
            sum[0] += curSum;
            return;
        }
        
        dfs(root.left, sum, curSum);
        dfs(root.right, sum, curSum);
    }
}

// solution2: 利用return value
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int prevSum) {
        if (root == null) return 0;
        int curSum = prevSum * 2 + root.val;
        if (root.left == null && root.right == null) {
            return curSum;
        }
        
        return dfs(root.left, curSum) + dfs(root.right, curSum);
    }
}