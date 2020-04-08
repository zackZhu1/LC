// solution1: use prev node to connect
TreeNode head = null; 
TreeNode prev = null;

public void inOrder(TreeNode root) {
	if (root == null) return;
	inOrder(root.left);

	if (head == null) head = root;
	if (prev != null) prev.right = root;
	root.left = prev;
	prev = root;

	inOrder(root.right);
}

// solution2: pure recursion
public TreeNode flatten(TreeNode root) {
	if (root == null) return null;
	TreeNode left = flatten(root.left);
	TreeNode right = flatten(root.right);

	root.left = root;
	root.right = root;

	left = connect(left, root);
	left = connect(left, right);
	return left;
}

public TreeNode connect(TreeNode h1, TreeNode h2) {
	if (h1 == null) return h2;
	if (h2 == null) return h1;
	TreeNode t1 = h1.prev;
	TreeNode t2 = h2.prev;
	h1.prev = t2;
	t2.next = h1;
	h2.prev = t1;
	t1.next = h2;
	return h1;
}