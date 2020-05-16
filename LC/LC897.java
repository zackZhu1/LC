
class Solution {
    private TreeNode prev = null;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode res = new TreeNode(0);
        prev = res;
        inorder(root);
        return res.right;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        prev.right = node;
        prev = node;
        inorder(node.right);
    }
}