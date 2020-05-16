// pure recursion
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return dfs(t1, t2);
    }
    
    private TreeNode dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null || t2 == null) return t1 == null ? t2 : t1;
        TreeNode cur = new TreeNode(t1.val + t2.val);
        cur.left = dfs(t1.left, t2.left);
        cur.right = dfs(t1.right, t2.right);
        return cur;
    }
}