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

public class test {
	// solution1: maintain a parent node pointer
	public static TreeNode findSecondLargestNode(TreeNode root) {
		// 1. find the largest node
		TreeNode parent = null;
		TreeNode largest = root;
		while (largest.right != null) {
			parent = largest;
			largest = largest.right;
		}

		// 2. find the second largest
		TreeNode secondLargest = null;
		if (largest.left != null) {
			secondLargest = largest.left;
			while (secondLargest.right != null) {
				secondLargest = secondLargest.right;
			}
			return secondLargest;
		} else {
			return parent;
		}
	}
	
	// solution2: reverse inorder traversal
	// https://www.geeksforgeeks.org/second-largest-element-in-binary-search-tree-bst/
	private static int count = 0;
	private static TreeNode secondLargest = null;
	public static TreeNode findSecondLargestNode2(TreeNode root) {
		inorder(root);
		return secondLargest;
	}
	
	private static void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.right);
		count++;
		if (count == 2) {
			secondLargest = root;
			return;
		}
		inorder(root.left);
	}
	
	private static TreeNode createTestCase1() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.right.right = new TreeNode(7);
		return root;
	}
	
	private static TreeNode createTestCase2() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.right.right = new TreeNode(7);
		root.right.right.left = new TreeNode(5);
		root.right.right.left.right = new TreeNode(6);
		return root;
	}
	
	private static void levelOrder(TreeNode root) {
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			System.out.print(cur.val + " ");
			if (cur.left != null) {
				queue.offer(cur.left);
			}
			if (cur.right != null) {
				queue.offer(cur.right);
			}
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = createTestCase2();
		// levelOrder(root);
		System.out.println(findSecondLargestNode2(root).val);
	}
}







