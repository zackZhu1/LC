/*
find kth largest node in BST
	traverse the BST following this order: right -> root -> left
*/

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
	
	public static int kthLargestNodeInBST(TreeNode root, int k) {
		List<TreeNode> list = new ArrayList<>();
		dfs(root, list, k);
		
		return list.get(list.size() - 1).val;
	}
	
	private static void dfs(TreeNode root, List<TreeNode> list, int k) {	
		if (root == null) return;
		
		dfs(root.right, list, k);
		if (list.size() == k) return; // if right subtree has enough nodes, don't add current node in the list
		list.add(root);
		if (list.size() == k) return;
		dfs(root.left, list, k);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(5);
		System.out.println(kthLargestNodeInBST(root, 1));
	}
}








