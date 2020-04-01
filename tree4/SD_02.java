/* 
sum of all paths from leaf to root
*/

// 从上传下传值
private static int total = 0;

sum(root, 1, 0);

public void sum(TreeNode cur, int factor, int curPathValue) {
	curPathValue += cur.val * factor;
	if (cur.left == null && cur.right == null) {
		total += curPathValue;
		return;
	}
	if (cur.left != null) sum(cur.left, factor * 10, curPathValue);
	if (cur.right != null) sum(cur.right, factor * 10, curPathValue);
}

// pure recursion
class ReturnType {
	int sum;
	int numPaths;
}

public ReturnType sum(TreeNode root) {
	if (root.left == null && root.right == null) {
		return ReturnType(root.val, 1);
	}

	int numPaths = 0;
	int sum = 0;
	if (root.left != null) {
		ReturnType left = sum(root.left);
		numPaths += left.numPaths;
		sum += left.sum;
	}
	if (root.right != null) {
		ReturnType right = sum(root.right);
		numPaths += right.numPaths;
		sum += right.sum;
	}
	return ReturnType(sum * 10 + root.val * numPaths, numPaths);
}