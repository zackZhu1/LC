public static void insert(TreeNode root, int target) {
	while (root != null) {
		if (root.val < target) {
			if (root.right == null) {
				root.right = new TreeNode(target, 0);
				return;
			} else {
				root = root.right;
			}
		} else {
			root.numLeft++;
			if (root.left == null) {
				root.left = new TreeNode(target, 0);
				return;
			} else {
				root = root.left;
			}
		}
	}
}