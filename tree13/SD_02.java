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
	TreeNode prev = null;
	TreeNode node1 = null;
	TreeNode node2 = null;
}

public class test {
	public static void recoverBST(TreeNode root) {
		GlobalState state = new GlobalState();
		inorder(root, state);
		swap(state.node1, state.node2);
	}
	
	private static void inorder(TreeNode root, GlobalState state) {
		if (root == null) return;
		
		inorder(root.left, state);
		if (state.prev != null && state.prev.val > root.val) {
			if (state.node1 == null) {
				state.node1 = state.prev;
			}
			state.node2 = root;
		}
		state.prev = root;
		
		inorder(root.right, state);
	}
	
	private static void swap(TreeNode node1, TreeNode node2) {
		double tmp = node1.val;
		node1.val = node2.val;
		node2.val = tmp;
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
		root.left.right = new TreeNode(11);
		root.left.right.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(9);
		root.right.left.left = new TreeNode(8.5);
		root.right.left.right = new TreeNode(7);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(17);
		root.right.right.right = new TreeNode(25);
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = createTestCase();
		levelOrder(root);
		recoverBST(root);
		levelOrder(root);
	}
}







