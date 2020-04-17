// solution1: 
// Time = O(n)
// Space = O(n)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>(); // O(n)
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    
    private TreeNode construct(int[] pre, int pStart, int pEnd, int[] in, int iStart, int iEnd, Map<Integer, Integer> map) {
        // base case
        if (pStart > pEnd) return null;
        
        // induction rule: using preOrder to construct tree
        TreeNode root = new TreeNode(pre[pStart]);
        
        // calculate size of two halves
        int inIndex = map.get(root.val);
        int leftSize = inIndex - 1 - iStart + 1;
        int rightSize = iEnd - (inIndex + 1) + 1;
        
        root.left = construct(pre, pStart + 1, pStart + 1 + leftSize - 1, in, iStart, inIndex - 1, map);
        root.right = construct(pre, pEnd - rightSize + 1, pEnd, in, inIndex + 1, iEnd, map);
        return root;
    }
}

// solution2
// Time = O(n)
// Space = O(height)
class Solution {
    private int preIndex = 0;
    private int inIndex = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] preorder, int[] inorder, int inOrderSuccessor) {
        if (inIndex == inorder.length || inorder[inIndex] == inOrderSuccessor) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preIndex]);
        preIndex++;
        
        root.left = helper(preorder, inorder, root.val);
        inIndex++;
        root.right = helper(preorder, inorder, inOrderSuccessor);
        return root;
    }
}