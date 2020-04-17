// construct BST from preorder traversal
// Time = O(nlogn)
// Space = O(height)
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return reconstruct(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode reconstruct(int[] pre, int start, int end) {
        // base case
        if (start > end) return null;
        
        TreeNode root = new TreeNode(pre[start]);
        int rightStart = start + 1;
        while (rightStart <= end && pre[rightStart] < pre[start]) {
            rightStart++; 
        }
        
        root.left = reconstruct(pre, start + 1, rightStart - 1);
        root.right = reconstruct(pre, rightStart, end);
        return root;
    }
}

// Time = O(N)
// space = O(height)
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return reconstruct(preorder, Integer.MAX_VALUE);
    }
    
    private int preIndex = 0;
    private TreeNode reconstruct(int[] pre, int inOrderSuccessor) {
        if (preIndex == pre.length || pre[preIndex] > inOrderSuccessor) return null;
        
        TreeNode root = new TreeNode(pre[preIndex]);
        preIndex++;
        root.left = reconstruct(pre, root.val);
        root.right = reconstruct(pre, inOrderSuccessor);
        return root;
    }
}