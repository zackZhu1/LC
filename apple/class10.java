import java.util.*;

class ListNode {
	int val;
	ListNode next;
	ListNode(int val) {
		this.val = val;
	}
}

public class test {
	
	public List<List<Integer>> nqueens(int n) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		helper(n, cur, res);
		return res;
	}
	
	private void helper(int n, List<Integer> cur, List<List<Integer>> res) {
		// base case
		if (cur.size() == n) {
			res.add(new ArrayList<>(cur));
			return;
		}
		// induction rule
		for (int i = 0; i < n; i++) { // try each column for current row
			if (isValid(cur, i)) {
				cur.add(i);
				helper(n, cur, res);
				cur.remove(cur.size() - 1);
			}
		}
	}
	
	private boolean isValid(List<Integer> cur, int column) {
		int row = cur.size();
		for (int i = 0; i < row; i++) {
			if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) return false;
		}
		return true;
	}
	
	/*
	 * spiral order traverse
	 */
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		recursiveTraverse(matrix, 0, matrix.length, res);
		return res;
	}
	
	private void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> res) {
		if (size == 0) return;
		if (size == 1) {
			res.add(matrix[offset][offset]);
			return;
		}
		
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[offset][offset + i]);
		}
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[offset + i][offset + size - 1]);
		}
		for (int i = size - 1; i >= 1; i--) {
			res.add(matrix[offset + size - 1][offset + i]);
		}
		for (int i = size - 1; i >= 1; i--) {
			res.add(matrix[offset + i][offset]);
		}
		recursiveTraverse(matrix, offset + 1, size - 2, res);
	}
	
	/*
	 * reverse linked list in pairs
	 */
	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = reverseInPairs(head.next.next);
		newHead.next = head;
		return newHead;
	}
	
	/*
	 * abbreviation matching
	 */
	public boolean match(String input, String pattern) {
		return match(input, pattern, 0, 0);
	}
	
	// t is pattern
	private boolean match(String s, String t, int si, int ti) {
		if (si == s.length() && ti == t.length()) return true;
		if (si >= s.length() || ti >= t.length()) return false;
		// case1: current character in t is not a digit
		if (t.charAt(ti) < '0' || t.charAt(ti) > '9') {
			if (s.charAt(si) == t.charAt(ti)) {
				return match(s, t, si + 1, ti + 1);
			}
			return false;
		}
		// case 2: current character in t is a digit
		int count = 0;
		while (ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {
			count = count * 10 + (t.charAt(ti) - '0');
			ti++;
		}
		return match(s, t, si + count, ti);
	}
	
	/*
	 * store number of nodes in left subtree
	 */
	static class TreeNode {
		int key;
		TreeNode left;
		TreeNode right;
		int numNodesLeft;
		TreeNode(int key) {
			this.key = key;
		}
	}
	
	public void numNodesLeft(TreeNode root) {
		numNodes(root);
	}
	
	private int numNodes(TreeNode root) {
		if (root == null) return 0;
		int leftNum = numNodes(root.left);
		int rightNum = numNodes(root.right);
		root.numNodesLeft = leftNum;
		return leftNum + rightNum + 1;
	}
	
	/*
	 * find the node in binary tree with the max difference in the total number of descendents in its left subtree and right subtree
	 */
	public TreeNode maxDiffNode(TreeNode root) {
		if (root == null) return null;
		
		TreeNode[] node = new TreeNode[1];
		int[] diff = new int[1];
		diff[0] = -1;
		dfs(root, node, diff);
		return node[0];
	}
	
	private int dfs(TreeNode root, TreeNode[] node, int[] diff) {
		if (root == null) return 0;
		int leftNum = dfs(root.left, node, diff);
		int rightNum = dfs(root.right, node, diff);
		if (Math.abs(leftNum - rightNum) > diff[0]) {
			node[0] = root;
			diff[0] = Math.abs(leftNum - rightNum);
		}
		return leftNum + rightNum + 1;
	}
	
	/*
	 * lowest common ancestor
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null) return null;
		if (root == one || root == two) return root; // ignore the later recursion
		TreeNode left = lowestCommonAncestor(root.left, one, two);
		TreeNode right = lowestCommonAncestor(root.right, one, two);
		if (left != null && right != null) return root;
		return left == null ? right : left;
	}
	
	public static void main(String[] args) {
		
	}
}








