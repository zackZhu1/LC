// solution1:
public int getLongestDepth(int[] array) {
	// step1: find the root, and create the reverse mapping
	List<Integer> rootList = new ArrayList<>();
	Map<Integer, List<Integer>> map = new HashMap<>();

	for (int i = 0; i < array.length; i++) {
		if (array[i] == -1) {
			rootList.add(i);
		} else {
			if (!map.containsKey(array[i])) {
				map.put(array[i], new ArrayList<>());
			}
			map.get(array[i]).add(i);
		}
	}

	// step2: DFS to find the height of each tree from each root
	int max = 0;
	for (Integer root : rootList) {
		max = Math.max(dfs(root, map), max);
	} 
	return max;
}

private int dfs(Integer root, Map<Integer, List<Integer>> map) {
	// base case
	if (!map.containsKey(root)) {
		return 1;
	}

	int maxDepth = 0;
	for (Integer child : map.get(root)) {
		maxDepth = Math.max(dfs(child, map) + 1, maxDepth);
	}
	return maxDepth;
}

// Time = O(2n) = O(n)
// Space = O(n)


// solution2: 从下往上找
public int getLongestDepth2(int[] array) {
	int maxDepth = 0;
	for (int i = 0; i < array.length; i++) { // i: current node, array[i]: its parent
		int cur = i;
		int depth = 1;
		while (array[cur] != -1) {
			cur = array[cur];
			depth++;
		}
		maxDepth = Math.max(depth, maxDepth);
	}
	return maxDepth;
}

// Time = O(n^2)
// Space = O(1)

// solution3: recursion + memorization
// induction rule: pathLength(n) = pathLength(n's parent) + 1
public int getLongestDepth3(int[] nodes) {
	int[] depths = new int[nodes.length]; // mark visited 的作用
	int globalMax = 0;
	for (int i = 0; i < nodes.length; i++) {
		globalMax = Math.max(globalMax, dfs(i, depths, nodes));
	}
	return globalMax;
}

// mark visited 2
private int dfs(int i, int[] depths, int[] nodes) {
	if (i == -1) return 0;
	// unvisited: 0; visiting: -1; visited: > 0
	if (depths[i] > 0) return depths[i];
	if (depths[i] == -1) throw Exception("detect cycle");
	int parent = nodes[i];
	depths[i] = -1; // visiting
	depths[i] = 1 + dfs(parent, depths, nodes); // mark visited
	return depths[i]; 
}







