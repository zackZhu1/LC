// topological order
// DFS mark visited 2
enum State {
	UNVISITED, VISITED, EXECUTED;
}

class Vertex {
	State state = State.UNVISITED;
	List<Vertex> dependencies;
}

List<Vertex> topoOrder(List<Vertex> graph) {
	List<Vertex> topoOrder = new ArrayList<>();
	for (Vertex v : graph) {
		dfs(v, topoOrder);
	}
	return topoOrder;
}

void dfs(Vertex v, List<Vertex> topoOrder) {
	if (v.state == State.EXECUTED) {
		return;
	}
	if (v.state == State.VISITING) {
		throw Exception("Detect cycle !");
	}

	v.state = State.VISITING;
	for (Vertex nei : v.dependencies) {
		dfs(nei, topoOrder);
	}
	v.state = State.EXECUTED; 
	// run the task after all its dependencies finish
	topoOrder.add(v);
}

// Time: O(|V| + |E|)
// Space: the longest DFS path, |V| worst case 