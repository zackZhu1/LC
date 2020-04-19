import java.util.*;

class TreeNode {
	double val;
	int numLeft;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(double val, int numLeft) {
		this.val = val;
		this.numLeft = numLeft;
		this.left = null;
		this.right = null;
	}
}

public class test {
	public static int numSmaller(TreeNode root, int target) {
		int result = 0;
		while (root != null) {
			if (root.val == target) {
				return result += root.numLeft;
			} else if (root.val < target) {
				result += root.numLeft + 1;
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return result;
	}
	
	public static TreeNode createTestCase() {
		TreeNode root = new TreeNode(8, 4);
		root.left = new TreeNode(4, 1);
		root.left.left = new TreeNode(1, 0);
		root.left.right = new TreeNode(7, 0);
		root.left.right.right = new TreeNode(7.5, 0);
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
		System.out.println(numSmaller(root, 10));
	}
}







