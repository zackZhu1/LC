/*
largest path sum any to any 
*/

int globalMax = Integer.MIN_VALUE;

// return value: maximum path sum starting from root
public int largest(TreeNode root) {
	if (root == null) return 0;

	int left = largest(root.left);
	int right = largest(root.right);
	int singleMax = Math.max(root, root + left, root + right);
	int doubleMax = Math.max(singleMax, root + left + right);
	globalMax = Math.max(doubleMax, globalMax);
	return singleMax;
}