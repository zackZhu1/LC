import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val) {
		this.val = val;
	}
}

public class test {
	/*
	 * check if binary tree is balanced
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		return height(root) != -1;
	}
	
	private int height(TreeNode root) {
		if (root == null) return 0;
		
		int leftHeight = height(root.left);
		if (leftHeight == -1) return -1;
		
		int rightHeight = height(root.right);
		if (rightHeight == -1) return -1;
		
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/*
	 * check if binary tree is symmetric
	 * 	pure recursion
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		return isSymmetric(root.left, root.right);
	}
	
	private boolean isSymmetric(TreeNode one, TreeNode two) {
		if (one == null && two == null) return true;
		else if (one == null || two == null) return false;
		else if (one.val != two.val) return false;
		return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
	}
	
	/*
	 * tweaked identical binary tree
	 */
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		if (one == null && two == null) return true;
		else if (one == null || two == null) return false;
		else if (one.val != two.val)return false;
		return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
			|| isTweakedIdentical(one.left, two.right)&& isTweakedIdentical(one.right, two.left); 
	}
	
	/*
	 * is BST or not
	 * 	pass down 从上往下传值
	 */
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBST(TreeNode root, int min, int max) {
		if (root == null) return true;
		if (root.val < min || root.val > max) return false;
		return isBST(root.left, min, root.val - 1) && isBST(root.right, root.val + 1, max);
	}
	
	/*
	 * get keys in BST in given range
	 */
	public List<Integer> getRange(TreeNode root, int min, int max) {
		List<Integer> res = new ArrayList<>();
		getRange(root, min, max, res);
		return res;
	}
	
	private void getRange(TreeNode root, int min, int max, List<Integer> res) {
		if (root == null) return;
		if (root.val > min) getRange(root.left, min, max, res);
		if (root.val >= min && root.val <= max) res.add(root.val);
		if (root.val < max) getRange(root.right, min, max, res);
	}
	
	
	public static void main(String[] args) {
		
	}
}








