// Given a given BST and a target, divide it into two separate BST 
import java.util.*;

class TreeNode {
	double val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(double val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}

class GlobalState {
	TreeNode smaller = null;
	TreeNode larger = null;
	
	GlobalState (TreeNode smaller, TreeNode larger) {
		this.smaller = smaller;
		this.larger = larger;
	}
}

public class test {
	
	// solution1: pure recursion
	public static GlobalState partitionBST(TreeNode root, int pivot) {
		if (root == null) return new GlobalState(null, null);
		
		if (root.val <= pivot) {
			GlobalState rightState = partitionBST(root.right, pivot);
			root.right = rightState.smaller;
			return new GlobalState(root, rightState.larger);
		}
		
		GlobalState leftState = partitionBST(root.left, pivot);
		GlobalState rightState = partitionBST(root.right, pivot);
		root.left = leftState.larger;
		root.right = rightState.larger;
		return new GlobalState(leftState.smaller, root);
	}
	
	// solution2: use derived property of search path
	// 小于等于pivot的是递增的  大于pivot的是递减的
	public static GlobalState partitionBST2(TreeNode root, int pivot) {
		GlobalState state = new GlobalState(null, null);
		TreeNode largestT1 = null;
		TreeNode smallestT2 = null;
		
		while (root != null) {
			if (root.val <= pivot) {
				if (state.smaller == null) state.smaller = root;
				else largestT1.right = root;
				
				largestT1 = root;
				root = root.right;
			} else {
				if (state.larger == null) state.larger = root;
				else smallestT2.left = root;
				
				smallestT2 = root;
				root = root.left;
			}
		}
		// delink !!!
		if (largestT1 != null) largestT1.right = null;
		if (smallestT2 != null) smallestT2.left = null;
		return state;
	}
	
	// verification
	public static void levelOrder(TreeNode root) {
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			System.out.print(cur.val + " ");
			if (cur.left != null) {
				TreeNode left = cur.left;
				queue.offer(left);
			}
			if (cur.right != null) {
				TreeNode right = cur.right;
				queue.offer(right);
			}
		}
		System.out.println();
	}
	
	public static TreeNode createTestCase() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(7);
		root.left.right.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(9);
		root.right.left.left = new TreeNode(8.5);
		root.right.left.right = new TreeNode(11);
		root.right.left.right.left = new TreeNode(9.5);
		root.right.left.right.left.right = new TreeNode(10.2);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(17);
		root.right.right.right = new TreeNode(25);
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = createTestCase();
		GlobalState state = partitionBST2(root, 10);
		levelOrder(state.smaller);
		levelOrder(state.larger);
	}
}







