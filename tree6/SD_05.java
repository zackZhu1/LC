class ReturnType {
	boolean isPerfect;
	boolean isComplete;
	int height;
}

public boolean isCompleteTree(TreeNode root) {
	if (root == null) {
		return new ReturnType(true, true, 0);
	}
	ReturnType left = isCompleteTree(root.left);
	ReturnType right = isCompleteTree(root.right);

	boolean isPerfect = left.isPerfect && right.isPerfect && left.height == right.height;
	boolean isComplete = (left.isComplete && right.isPerfect && left.height == right.height + 1)
						|| (left.isPerfect && right.isComplete && left.height = right.height);
	int height = Math.max(left.height, right.height) + 1;
	return ReturnType(isPerfect, isComplete, height);
}