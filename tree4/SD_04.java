/*
largest path sum 直上直下 any to any
*/

// solution1: pure recursion
// return value: largest path sum starting from root
private static int globalMax = Integer.MIN_VALUE;

public int largest(TreeNode root) {
	if (root == null) return 0;
	int left = largest(root.left);
	int right = largest(root.right);
	// induction rule: 
	int largest = root.val + Math.max(Math.max(left, right), 0);
	globalMax = Math.max(globalMax, largest);
	return largest;
}

// solution2: backtracking
// return value: largest path sum ending at current node
private static int globalMax = Integer.MIN_VALUE;
public void backtracking(TreeNode cur, int prevLargest) {
	int largest = root.val + (prevLargest < 0 ? 0 : prevLargest);
	globalMax = Math.max(globalMax, largest);

	if (root.left != null) backtracking(cur.left, largest);
	if (root.right != null) backtracking(cur.right, largest);
}

backtracking(root, 0);