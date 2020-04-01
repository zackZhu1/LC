/*
Find the second largest node in BST without parent pointer and stack
	一路走到最右边找到最大节点 
	if largest.left != null: findLargest(largst.left)
	else: largest.parent
*/
class Pair {
	TreeNode node;
	TreeNode parent;
}

public TreeNode secondLargest(TreeNode root) {
	if (root == null) return null;
	if (root.left == null && root.right == null) return null;
	Pair pair = largest(root);
	if (pair.node.left == null) {
		return pair.parent;
	} else {
		pair = largest(pair.node.left);
		return pair.node;
	}
}

private Pair largest(TreeNode node) {
	TreeNode parent = null;
	while (node != null) {
		parent = node;
		node = node.right;
	}
	return new Pair(node, parent);
}