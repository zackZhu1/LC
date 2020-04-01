public int sum(TreeNode root) {
	if (root == null) return 0;
	int leftLen = len(root.left);
	int rightLen = len(root.right);
	if (leftLen == rightLen) {
		return (1 << leftLen) + sum(root.right);
	} else {
		return (1 << rightLen) + sum(root.left);
	}
}

private int len(TreeNode root) {
	int res = 0;
	while (root != null) {
		res++;
		root = root.left;
	}
	return res;
}