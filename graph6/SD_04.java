class BFSImpl {
	private Queue<Node> queue;
	private Map<Node, Integer> visited; // 1. mark visited. 2. its shortest path

	public BFSImpl(Node init) {
		queue = new ArrayDeque<>();
		visited = new HashMap<>();
		queue.offer(init);
		visited.put(init, 0);
	}

	public boolean hasNodesToExplore() {
		return !queue.isEmpty();
	}

	// generate 
	public int nextRound(BFSImpl reverse) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node cur = queue.poll();
			for (Node nei : cur.neighbors) {
				// check if it is overlapped with the reverse BFS explored area
				if (reverse.visited.contains(nei)) {
					return visited.get(cur) + 1 + reverse.visited.get(nei);
				}

				// generate from A
				if (!visited.containsKey(nei)) {
					visited.put(nei, visited.get(cur) + 1);
					queue.offer(nei);
				}
			}
		}
		return -1;
	}
}

int biDirectionalBFS(Node a, Node b) {
	BFSImpl bfsA = new BFSImpl(a);
	BFSImpl bfsB = new BFSImpl(b);
	while (bfsA.hasNodesToExplore() && bfsB.hasNodesToExplore()) {
		int shortest = bfsA.nextRound(bfsB);
		if (shortest != -1) {
			return shortest;
		}
		int shortest = bfsB.nextRound(bfsA);
		if (shortest != -1) {
			return shortest;
		}
	}
	return -1;
}

// uni-directional, branching factor: n, level: k
// Time = O(n^k)
// Space = O(n^k)

// bi-directional, branching factor: n, level: k
// Time = O(2 * n^(k/2))




