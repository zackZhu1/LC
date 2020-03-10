// tree
void bfs(Node root) {
	Queue<Node> queue = new ArrayDeque<>();
	queue.offer(root);
	while (!queue.isEmpty()) {
		Node cur = queue.poll(); // expand
		for (Node nei : cur.children) { // generate
			queue.offer(nei);
		}
	}
}

void bfs(Node root) {
	Queue<Node> queue = new ArrayDeque<>();
	queue.offer(root);
	int level = 0;
	while (!queue.isEmpty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node cur = queue.poll();
			for (Node nei : cur.children) {
				queue.offer(nei);
			}
		}
		level++;
	}
}

// graph
// mark visited at expansion 
void bfs(Node root) {
	Queue<Node> queue = new ArrayDeque<>();
	Set<Node> visited = new HashSet<>();
	queue.offer(root);

	while (!queue.isEmpty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node cur = queue.poll(); // expansion
			if (!visited.add(cur)) {
				continue;
			}
			for (Node nei : cur.children) {
				queue.offer(nei);
			}
		}
	}
}
// Time = O(|V| + |E|)
// Space = O(|E|)

// mark visited at being generated
void bfs(Node root) {
	Queue<Node> queue = new ArrayDeque<>();
	Set<Node> visited = new HashSet<>();
	queue.offer(root);
	visited.offer(root);

	while (!queue.isEmpty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node cur = queue.poll(); // expansion
			for (Node nei : cur.children) {
				if (visited.add(nei)) {
					queue.offer(nei); // generation
				}
			}
		}
	}
}
// Time = O(|V| + |E|)
// Space = O(|V|)








