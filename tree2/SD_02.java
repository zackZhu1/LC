/*
Top View:
分析过程：
	对每个节点 需要知道level和column的信息
	当BFS遍历的时候 两边边界都在扩展 --> Deque<TreeNode> 
	只有第一次扩展的时候 需要加入到结果中 --> maintain 边界 --> [leftMost, rightMost]
*/

public class test {
	static class NodeWithCol {
		int col;
		TreeNode node; 

		NodeWithCol(TreeNode node, int col) {
			this.col = col;
			this.node = node;
		}
	}
	
	// top view
	public static List<Integer> topView(TreeNode root) {
		Queue<NodeWithCol> q = new ArrayDeque<>();
		q.offer(new NodeWithCol(root, 0));

		Deque<Integer> res = new ArrayDeque<>();
		res.offer(root.val);
		int leftMost = 0;
		int rightMost = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				NodeWithCol cur = q.poll();
				
				if (cur.node.left != null) {
					if (cur.col == leftMost) {
						res.offerFirst(cur.node.left.val);
						leftMost--;
					}
					q.offer(new NodeWithCol(cur.node.left, cur.col - 1));
				}
				if (cur.node.right != null) {
					if (cur.col == rightMost) {
						res.offerLast(cur.node.right.val);
						rightMost++;
					}
					q.offer(new NodeWithCol(cur.node.right, cur.col + 1));
				}
			}
		}
		return new ArrayList<>(res);
	}


	// bottom view
	private static TreeMap<Integer, Integer> map = new TreeMap<>(); // key: col, value: node.val
	public static void bottomView(TreeNode root) {
		if (root == null) return;
		Queue<NodeWithCol> queue = new LinkedList<>();
		queue.offer(new NodeWithCol(0, root));

		while (!queue.isEmpty()) {
			NodeWithCol cur = queue.poll();
			map.put(cur.col, cur.node.val);

			if (cur.node.left != null) 
				queue.offer(new NodeWithCol(cur.col - 1, cur.node.left));
			if (cur.node.right != null)
				queue.offer(new NodeWithCol(cur.col + 1, cur.node.right));
		}
	}

	// vertical view
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		
		Map<Integer, List<Integer>> map = new HashMap<>(); // key: col, value: [node.val]
		int min = 0, max = 0; // easy for constructing the return value
		
		Queue<NodeWithCol> queue = new LinkedList<>();
		queue.offer(new NodeWithCol(root, 0));
		
		while (!queue.isEmpty()) {
			NodeWithCol cur = queue.poll();
			if (!map.containsKey(cur.col)) {
				map.put(cur.col, new ArrayList<>());
			}
			map.get(cur.col).add(cur.node.val);
			
			if (cur.node.left != null) {
				queue.offer(new NodeWithCol(cur.node.left, cur.col - 1));
				min = Math.min(min, cur.col - 1);
			}
			if (cur.node.right != null) {
				queue.offer(new NodeWithCol(cur.node.right, cur.col + 1));
				max = Math.max(max, cur.col + 1);
			}
		}
		
		for (int i = min; i <= max; i++) {
			res.add(map.get(i));
		}
		return res;
	}

	// border view
	// leftMost path: DFS, preorder, left -> right
	// rightmost path: DFS, postorder, left -> right
	// leaf nodes: DFS, pre/in/post, left -> right
	public void borderView(TreeNode root) {
		if (root == null) return;
		dfs(root, true, true);
	}

	private void dfs(TreeNode root, boolean leftMost, boolean rightMost) {
		if (root == null) return;

		if (leftMost || root.left == null && root.right == null) {
			System.out.println(root.val + " ");
		}
		dfs(root.left, leftMost, false);
		dfs(root.right, false, rightMost);
		if (rightMost && (root.left != null || root.right != null)) {
			System.out.println(root.val + " ");
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(9);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(11);
		List<Integer> res = topView(root);
		for (Integer ele : res) {
			System.out.println(ele);
		}
	}
}








