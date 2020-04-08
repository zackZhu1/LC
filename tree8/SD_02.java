// post-order
// passing parameters: parent node
public void dfs(TreeNode node, TreeNode parent) {
	if (node == null) return;
	dfs(node.left, node);
	dfs(node.right, node);

	// 1. add node into res
	if (shouldDelete(parent) && !shouldDelete(node)) result.add(node);
	// 2. remove edges
	if (shouldDelete(parent) || shouldDelete(node)) remove(parent, node);
}

private void remove(TreeNode parent, TreeNode node) {
	if (parent == null) return;
	if (node == parent.left) parent.left = null;
	else parent.right = null;
}