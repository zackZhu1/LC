// iteration
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode result = root;
        while (result != null) {
            if (result.val == val) return result;
            else if (result.val < val) 
                result = result.right;
            else
                result = result.left;
        }
        return result;
    }
}
// Time = O(height)
// Space = O(1)

// recursion 
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        
        if (root.val == val) return root;
        if (root.val < val) 
            return searchBST(root.right, val);
        else 
            return searchBST(root.left, val);
    }
}
// Time = O(height)
// Space = O(height)