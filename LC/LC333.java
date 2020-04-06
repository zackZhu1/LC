// pure recursion 
class Solution {
    class Result {
        int min;
        int max;
        boolean isBST;
        int size;
        Result() {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }
    
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        Result result = helper(root);
        return result.size;
    }
    
    private Result helper(TreeNode root) {
        if (root == null) return new Result();
        Result left = helper(root.left);
        Result right = helper(root.right);
        Result cur = new Result();
        if (!left.isBST || !right.isBST || root.val <= left.max || root.val >= right.min) {
            cur.isBST = false;
            cur.size = Math.max(left.size, right.size);
            return cur;
        }
        cur.isBST = true;
        cur.size = left.size + right.size + 1;
        cur.min = root.left == null ? root.val : left.min;
        cur.max = root.right == null ? root.val : right.max;
        return cur;
    }
}







