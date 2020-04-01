// BFS
public List<Integer> leftView(TreeNode root) {
	List<Integer> res = new ArrayList<>();
	if (root == null) return res;

	Queue<TreeNode> q = new LinkedList<>();
	q.offer(root);
	while (!q.isEmpty()) {  // for each level
		int size = q.size();
		res.add(q.peek());

		for (int i = 0; i < size; i++) {
			TreeNode cur = q.poll();
			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}
		}
	}
	return res;
}

// DFS
public void leftView(List<TreeNode> result, TreeNode root, int level) {
	if (root == null) return;

	if (result.size() == level) result.add(root);
	leftView(result, root.left, level + 1);
	leftView(result, root.right, level + 1);
}

public List<Integer> rightView(TreeNode root) {
	List<Integer> res = new ArrayList<>();
	if (root == null) return res;

	Queue<TreeNode> q = new LinkedList<>();
	q.offer(root);
	while (!q.isEmpty()) {
		int size = q.size();

		for (int i = 0; i < size; i++) {
			TreeNode cur = q.poll();
			if (i == size - 1) res.add(cur);

			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}
		}
	}
	return res;
}












