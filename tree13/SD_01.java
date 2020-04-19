import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class GlobalState {
    TreeNode maxValue = null;
    int maxCount = 0;
    TreeNode prevValue = null;
    int prevCount = 0;
}

public class test {
    public static int findMostFrequentValue(TreeNode root) {
        GlobalState state = new GlobalState();
        inorder(root, state);
        return state.maxValue.val;
    }
    
    private static void inorder(TreeNode root, GlobalState state) {
        if (root == null) return;
        
        inorder(root.left, state);
        if (state.prevValue == null || root.val != state.prevValue.val) {
            state.prevValue = root;
            state.prevCount = 1;
        } else {
            state.prevCount++;
        }
        // update global max if possible
        if (state.prevCount > state.maxCount) {
            state.maxValue = root;
            state.maxCount = state.prevCount;
        }
        inorder(root.right, state);
    }
    
    public static TreeNode createTestCase() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(1);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(8);
        root.right.right = new TreeNode(17);
        root.right.right.left = new TreeNode(16);
        root.right.right.right = new TreeNode(17);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTestCase();
        System.out.println(findMostFrequentValue(root));
    }
}







