int count = 0;

public boolean isUni(TreeNode root) {
	if (root == null) return true;
	boolean left = isUni(root.left);
	boolean right = isUni(root.right);

	if ((root.left == null || root.left.val == root.val)
		&& (root.right == null || root.right.val == root.val)
		&& left && right) {
		count++;
		return true;
	}
	return false;
}