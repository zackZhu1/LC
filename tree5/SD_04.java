/*
longest increasing path
*/

// solution1: backtracking
public int longest(TreeNode cur, int prevValue, int prevLongest) {
	if (cur == null) return 0;
	prevLongest = (cur.val > prevValue) ? prevLongest + 1 : 1;
	int left = longest(cur.left, cur.val, prevLongest);
	int right = longest(cur.right, cur.val, prevLongest);
	return Math.max(Math.max(left, right), prevLongest);
}

longest(root, Integer.MAX_VALUE, 0);

// solution2: pure recursion
int globalLongest = 0;
public int longest(TreeNode root) {
	if (root == null) return 0;
	int left = longest(root.left);
	int right = longest(root.right);

	int longest = 1;
	if (root.left != null && root.val < root.left.val) longest += left;
	if (root.right != null && root.val < root.right.val) longest = Math.max(longest, right);
	globalLongest = Math.max(globalLongest, longest);
	return longest;
}