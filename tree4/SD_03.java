/*
length of shortest path from root to leaf
*/
public int shortest(TreeNode root) {
	if (root.left == null && root.right == null) return 1;

	int shortest = Integer.MAX_VALUE;
	if (root.left != null) {
		shortest = Math.min(shortest, shortest(root.left));
	}
	if (root.right != null) {
		shortest = Math.min(shortest, shortest(root.right));
	}
	return shortest + 1;
}


public int shortest(TreeNode root) {
	if (root.left == null && root.right == null) return 1;

	if (root.left == null) return 1 + shortest(root.right);
	if (root.right == null) return 1 + shortest(root.left);
	return Math.min(shortest(root.left), shortest(root.right)) + 1;
}