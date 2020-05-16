// pure recursion
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        boolean left = isUnivalTree(root.left);
        boolean right = isUnivalTree(root.right);
        if (!left || !right) return false;
        if (root.left != null && root.val != root.left.val) return false;
        if (root.right != null && root.val != root.right.val) return false;
        return true;
    }
}





class Solution {
    class ReturnValue {
        Integer val;
        boolean isUni = false;
        ReturnValue(Integer val, boolean isUni) {
            this.val = val;
            this.isUni = isUni;
        }
    }
    
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root).isUni;
    }
    
    private ReturnValue dfs(TreeNode root) {
        if (root == null) {
            ReturnValue res = new ReturnValue(null, true);
            return res;
        }
        
        ReturnValue left = dfs(root.left);
        ReturnValue right = dfs(root.right);
        if (!left.isUni || !right.isUni) {
            return new ReturnValue(root.val, false);
        }
        if (left.val == null && right.val == null) {
            return new ReturnValue(root.val, true);
        }
        else if (left.val == null) {
            return new ReturnValue(root.val, root.val == right.val);
        }
        else if (right.val == null) {
            return new ReturnValue(root.val, root.val == left.val);
        }
        else if (left.val != right.val || left.val != root.val || right.val != root.val) {
            return new ReturnValue(root.val, false);
        }
        
        return new ReturnValue(root.val, true);
    }
}









