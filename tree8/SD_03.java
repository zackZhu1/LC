import java.util.*;

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	TreeNode(int val) {
		this.val = val;
	}
}

public class test {
	// solution1: use global variable
	private static int prefixSum = 0;
	
	public static void changeBST(TreeNode root) {
		if (root == null) return;
		changeBST(root.left);
		
		prefixSum += root.val;
		root.val = prefixSum;
		
		changeBST(root.right);
	}
	
	// solution2: return value + passing parameters
	// return value: sum of the subtree of the tree
	// prevSum: prevSum before inorder traversing current node
	public int changeBST2(TreeNode root, int prevSum) {
		if (root == null) return 0;
		int leftSum = changeBST2(root.left, prevSum);
		int rootVal = root.val;
		root.val += leftSum + prevSum;
		int rightSum = changeBST2(root.right, root.val);
		return leftSum + rightSum + rootVal;
	}
	
	// solution3: return value + passing parameters
	// return value: prefixSum of all the nodes already traversed
	// prevSum: same as before
	public int changeBST3(TreeNode root, int prevSum) {
		if (root == null) return prevSum;
		int leftSum = changeBST3(root.left, prevSum);
		root.val += leftSum;
		int rightSum = changeBST3(root.right, root.val);
		return rightSum;
	}

}








