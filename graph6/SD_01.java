void findAnyPath(Node init, Node goal) {
	// step1: BFS
	// 1. record the previous node
	// 2. mark visited
	Map<Node, Node> prevMap = new HashMap<>(); 
	Queue<Node> queue = new ArrayDeque<>();
	queue.offer(init);
	prevMap.put(init, null);

	while (!queue.isEmpty()) {
		Node cur = queue.poll();
		for (Node nei : cur.neighbors) {
			if (prevMap.containsKey(nei)) continue;
			prevMap.put(nei, cur); // mark visited
			if (nei == goal) return;
			queue.offer(nei);
		}
	}

	// step2: reversely find the path
	List<Node> path = new ArrayList<>();
	path.add(goal);
	Node cur = goal;
	while (cur != init) {
		cur = prevMap.get(cur); // get its parent
		path.add(cur);
	}
	Collections.reverse(path);
}

