// Sum of all paths from root to leaf 
private static int total = 0;

public void sum(TreeNode cur, int curPathValue) {
	if (cur == null) return;
	curPathValue = curPathValue * 10 + cur.val;
	if (cur.left == null && cur.right == null) {
		total += curPathValue;
	}

	sum(cur.left, curPathValue);
	sum(cur.right, curPathValue);
}

public int sum(TreeNode cur, int curPathValue) {
	if (cur == null) return 0;
	curPathValue = curPathValue * 10 + cur.val;
	if (cur.left == null && cur.right == null) {
		return curPathValue;
	}
	return sum(cur.left, curPathValue) + sum(cur.right, curPathValue);
}