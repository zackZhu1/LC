int bfs(Node root, Node target) {
	Queue<Node> queue = new ArrayDeque<>();
	Set<Node> visited = new HashSet<>();
	queue.offer(root);
	visited.offer(root);

	int level = 0;
	while (!queue.isEmpty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node cur = queue.poll(); // expansion
			// option1: terminate at expansion
			// if (cur == target) return level;

			for (Node nei : cur.neighbors) {
				if (nei == target) {
					return level + 1; // option2: terminate at generation
				}
				if (visited.add(nei)) {
					queue.offer(nei); // generation
				}
			}
		}
		level++;
	}
	return -1;
}