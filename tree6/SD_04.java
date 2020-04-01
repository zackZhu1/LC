/*
retain nodes in range [min, max] for a given BST
*/

// brute force
public TreeNode deleteNodes(TreeNode root, int min, int max) {
	if (root == null) return null;
	root.left = deleteNodes(root.left, min, max);
	root.right = deleteNodes(root.right, min, max);

	// case1: root in range
	if (root.val >= min && root.val <= max) return root;
	// case2: root out of range
	if (root.val < min) return root.right;
	else return root.left;
}


// optimization
public TreeNode deleteNodes(TreeNode root, int min, int max) {
	if (root == null) return null;
	// add pruning
	if (root.val < min) {
		return deleteNodes(root.right, min, max);
	} 
	if (root.val > max) {
		return deleteNodes(root.left, min, max);
	}

	// root in range
	root.left = deleteNodes(root.left, min, max);
	root.right = deleteNodes(root.right, min, max);
	return root;
}