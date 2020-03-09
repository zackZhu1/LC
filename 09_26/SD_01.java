// determine if a graph has a cycle

// 1. directed graph
// solution1: DFS mark visited 2
enum State {
	UNVISITED, VISITING, VISITED;
}

class Vertex {
	State state = State.UNVISITED;
	List<Vertex> neighbors;
}

boolean hasCycle(List<Vertex> graph) {
	for (Vertex v : graph) {
		if (hasCycle(v)) {
			return true;
		}
	}
	return false;
}

boolean hasCycle(Vertex v) {
	if (v.state == State.VISITED) {
		return false;
	}
	if (v.state == State.VISITING) {
		return true; // detect cycle
	}
	v.state = State.VISITING;
	for (Vertex nei : v.neighbors) {
		if (hasCycle(nei)) {
			return true;
		}
	}
	v.state = State.VISITED;
	return false;
}
// Time = O(|V| + |E|)

// solution2: DFS mark visited 3 
boolean hasCycle(Vertex v) {
	if (v.state == State.VISITED) {
		return true;
	}
	v.state = State.VISITED;
	for (Vertex nei : v.neighbors) {
		if (hasCycle(nei)) {
			return true;
		}
	}
	v.state = State.UNVISITED; // backtracking
	return false;
}
// Time = O(b^|N|)

// solution3: topological order

// -----------------------------------------------------

// 2. undirected graph
// solution1: DFS mark visited 1
boolean hasCycle(Vertex v, Vertex prev) {
	if (v.state == State.VISITED) {
		return true;
	}
	v.state = State.VISITED;
	for (Vertex nei : v.neighbors) {
		if (nei != prev && hasCycle(nei)) {
			return true;
		}
	}
	return false;
}

hasCycle(seed, null);

// solution2: concepts when undirected graph is a tree
// 1. no cycle
// 2. |E| = |V| - 1
// 3. connected

// 2, 3 --> 1
List<Vertex> graph; 
int number_of_nodes = graph.size();

int number_of_edges = 0;
for (Vertex v : graph) {
	number_of_edges += v.neighbors.size();
}
number_of_edges = number_of_edges / 2; 
















