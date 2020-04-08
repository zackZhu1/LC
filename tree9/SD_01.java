// solution1:
TreeNode prev = null;
public void preOrder(TreeNode root) {
	if (root == null) return;
	if (prev != null) prev.c3 = root;
	prev = root;
	TreeNode c3 = root.c3;

	preOrder(root.c1);
	preOrder(root.c2);
	preOrder(c3);

	root.c1 = null;
	root.c2 = null;
}

// solution2:
public TreeNode preOrder(TreeNode root) {
	if (root == null) return null;
	TreeNode tail1 = preOrder(root.c1);
	TreeNode tail2 = preOrder(root.c2);
	TreeNode tail3 = preOrder(root.c3);

	TreeNode tail = root;
	TreeNode c3 = root.c3;
	tail = connect(tail, root.c1, tail1);
	tail = connect(tail, root.c2, tail2);
	tail = connect(tail, c3, tail3);

	root.c1 = null;
	root.c2 = null;
	return tail;
}

private TreeNode connect(TreeNode tail, TreeNode subHead, TreeNode subTail) {
	if (subHead == null) return tail;
	tail.c3 = subHead;
	return subTail;
}