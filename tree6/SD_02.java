public TreeNode or(TreeNode r1, TreeNode r2) {
	if (r1 == null) return r2;
	if (r2 == null) return r1;

	TreeNode root = new TreeNode(r1.val || r2.val);
	root.left = or(r1.left, r2.left);
	root.right = or(r1.right, r2.right);
	return root;
}