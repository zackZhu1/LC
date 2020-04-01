/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list, k);
        return list.get(list.size() - 1).val;
    }
    
    private void dfs(TreeNode root, List<TreeNode> list, int k) {
        if (root == null) return;
        
        dfs(root.left, list, k);
        if (list.size() == k) return;
        list.add(root);
        if (list.size() == k) return;
        dfs(root.right, list, k);
    }
}