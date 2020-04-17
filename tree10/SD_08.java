// Reconstruct a binary tree with special signal and pre-order 
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
	private static final String NN = "#";
	private static int preIndex = 0;
	
	public static TreeNode construct(String[] preOrder) {
		// base case
		if (preOrder[preIndex].equals(NN))  {
			preIndex++;
			return null;
		}
		
		// induction rule
		TreeNode root = new TreeNode(Integer.valueOf(preOrder[preIndex]));
		preIndex++;
		root.left = construct(preOrder);
		root.right = construct(preOrder);
		return root;
	}
	
	private static void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		String[] pre = {"4", "2", "1", "#", "#", "3", "#", "#", "#"};
		TreeNode root = construct(pre);
		inorder(root);
	}
}







