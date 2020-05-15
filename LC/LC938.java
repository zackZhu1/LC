/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] sum = new int[]{0};
        dfs(root, sum, L, R);
        return sum[0];
    }
    
    private void dfs(TreeNode node, int[] sum, int L, int R) {
        if (node == null) return;
        if (L <= node.val && node.val <= R) sum[0] += node.val;
        if (L < node.val) 
            dfs(node.left, sum, L, R);
        if (node.val < R)
            dfs(node.right, sum, L, R);
    }
}