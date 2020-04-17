// Reconstruct a binary tree using level-order + special signal
import java.util.*;

class TreeNode {
	String val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(String value) {
		this.val = new String(value);
		this.left = null;
		this.right = null;
	}
}

public class test {
	public static TreeNode reconstruct(String[] level) {
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(level[0]);
		queue.offer(root);
		int index = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.val.equals("#")) continue;
				else {
					index++;
					TreeNode left = new TreeNode(level[index]);
					cur.left = left;
					index++;
					TreeNode right = new TreeNode(level[index]);
					cur.right = right;
					
					queue.offer(left);
					queue.offer(right);
				}
			}
		}
		
		return root;
	}
	
	private static void levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				System.out.print(cur.val + " ");
				if (cur.left != null) queue.offer(cur.left);
				if (cur.right != null) queue.offer(cur.right);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] level = {"5", "33", "#", "#", "2", "6", "7", "#", "#", "#", "#"};
		TreeNode root = reconstruct(level);
		levelOrder(root);
		
		Stack<Character> stack = new Stack<>();
		stack.
	}
}







