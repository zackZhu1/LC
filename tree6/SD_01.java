/*
Remove all the all-zero subtrees
*/
public TreeNode deleteAllZeros(TreeNode root) {
	if (root == null) return null;

	root.left = deleteAllZeros(root.left);
	root.right = deleteAllZeros(root.right);
	if (root.left == null && root.right == null && root.val == 0) {
		return null;
	}
	return root;
}