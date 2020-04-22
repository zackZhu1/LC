// pure recursion
class Solution {
    
    private int getTotalSum(TreeNode root) {
        if (root == null) return 0;
        int leftSum = getTotalSum(root.left);
        int rightSum = getTotalSum(root.right);
        return leftSum + rightSum + root.val;
    }
    
    private int helper(TreeNode root, int totalSum, long[] max) {
        if (root == null) return 0;
        int leftSum = helper(root.left, totalSum, max);
        int rightSum = helper(root.right, totalSum, max);
        int curSum = leftSum + rightSum + root.val;
        long totalProduct = (long) curSum * (totalSum - curSum);
        max[0] = Math.max(max[0], totalProduct);
        return curSum;
    }
    
    public int maxProduct(TreeNode root) {
        int totalSum = getTotalSum(root);
        
        long[] max = new long[] {0};
        helper(root, totalSum, max);
        return (int)(max[0] % 1000000007);
    }
}