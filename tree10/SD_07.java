// Reconstruct a BST with level-order 
// solution1: 
// Time = O(n^2)
// Space = O(n)
// https://www.geeksforgeeks.org/construct-bst-given-level-order-traversal/

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	TreeNode(int val) {
		this.val = val;
	}
}

public class test {
	
	public static TreeNode buildTree(int[] level) {
		if (level.length == 0) return null;
		TreeNode root = null;
		for (int i = 0; i < level.length; i++) {
			root = helper(root, level[i]);
		}
		return root;
	}
	
	public static TreeNode helper(TreeNode root, int val) {
		if (root == null) {
			root = new TreeNode(val);
			return root;
		}
		
		if (val <= root.val) {
			root.left = helper(root.left, val);
		} else {
			root.right = helper(root.right, val);
		}
		return root;
	}
	
	public static TreeNode buildTree2(int[] level) {
		if (level.length == 0) return null;
		TreeNode root = new TreeNode(level[0]);
		for (int i = 1; i < level.length; i++) {
			helper2(root, level[i]);
		}
		return root;
	}
	
	private static void helper2(TreeNode root, int val) {
		TreeNode prev = null;
		TreeNode cur = root;
		int direction = 0;
		while (cur != null) {
			if (val < root.val) {
				direction = -1;
				prev = cur;
				cur = cur.left;
			} else {
				direction = 1;
				prev = cur;
				cur = cur.right;
			}
		}
		if (direction == -1) prev.left = cur;
		else if (direction == 1) prev.right = cur;
	}
	
	
	private static void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		int[] level = {7, 4, 12, 3, 6, 8, 1, 5, 10};
		TreeNode root = buildTree(level);
		inorder(root);
	}
}







