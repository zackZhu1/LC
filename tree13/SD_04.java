// Time = O(logn + k)
class Solution {
    private int count = 0;
    private TreeNode kth = null;
    
    // inorder traversal
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return kth.val;
    }
    
    private void dfs(TreeNode root, int k) {
        if (root == null) return;
        dfs(root.left, k);
        count++;
        if (count == k) {
            kth = root;
            return;
        }
        dfs(root.right, k);
    }
}

// optimizate the time complexity to O(logn)
import java.util.*;

class TreeNode {
    int val;
    int numLeft;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val, int numLeft) {
        this.val = val;
        this.numLeft = numLeft;
        this.left = null;
        this.right = null;
    }
}

public class test {
    // solution1: pure recursion
    public static TreeNode kthSmallest(TreeNode root, int k) {
        if (root == null) return null;
        
        if (root.numLeft == k - 1) {
            return root;
        } else if (root.numLeft >= k) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - root.numLeft - 1);
        }
    }
    
    // solution2: iteration
    public static TreeNode kthSmallest2(TreeNode root, int k) {
        while (root != null) {
            if (root.numLeft == k - 1) {
                return root;
            } else if (root.numLeft >= k) {
                root = root.left;
            } else {
                k = k - root.numLeft - 1;
                root = root.right;
            }
        }
        return null;
    }
    
    public static TreeNode createTestCase() {
        TreeNode root = new TreeNode(8, 3);
        root.left = new TreeNode(4, 1);
        root.left.left = new TreeNode(1, 0);
        root.left.right = new TreeNode(7, 0);
        root.right = new TreeNode(15, 2);
        root.right.left = new TreeNode(9, 0);
        root.right.left.right = new TreeNode(12, 0);
        root.right.right = new TreeNode(20, 1);
        root.right.right.left = new TreeNode(17, 0);
        root.right.right.right = new TreeNode(25, 0);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTestCase();
        System.out.println(kthSmallest2(root, 6).val);
    }
}







