/*
longest path from any to any (diameter of a binary tree)
*/
private static int globalLongest = 0;

public int longestPath(TreeNode root) {
	if (root == null) return 0;

	int left = longestPath(root.left);
	int right = longestPath(root.right);
	globalLongest = Math.max(globalLongest, 1 + left + right);
	return 1 + Math.max(left, right);
}