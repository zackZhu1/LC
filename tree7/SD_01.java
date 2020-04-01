// pure recursion
// return value: isPerfect, height
public int isPerfect(TreeNode root) {
	if (root == null) return 0;
	int left = isPerfect(root.left);
	if (left < 0) return -1;	
	int right = isPerfect(root.right);
	if (right < 0) return -1;
	return left == right ? left + 1 : -1;
}
