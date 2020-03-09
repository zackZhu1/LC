// mark visited 1
enum State {
	UNVISITED, VISITED;
}

class Vertex {
	State state = UNVISITED;
	List<Vertex> neighbors; 
}

void dfs(Vertex v) {
	if (v.state == VISITED) return;

	v.state = VISITED;
	// expansion
	for (Vertex n : v.neighbors) {
		dfs(n);
	}
}

// mark visited 2
enum State {
	UNVISITED, VISITED, VISITING;
}

class Vertex {
	State state = UNVISITED;
	List<Vertex> neighbors;
}

void dfs(Vertex v) {
	if (v.state == VISITED) return; // already visited not on current DFS path
	if (v.state == VISITING) return; // already visited but on current DFS path --> detect cycle

	v.state = VISITING;
	for (Vertex n : v.neighbors) {
		dfs(n);
	}
	v.state = VISITED;
}

// mark visited 3
enum State {
	UNVISITED, VISITED;
}

class Vertex {
	State state = UNVISITED;
	List<Vertex> neighbors;
}

void dfs(Vertex v) {
	if (v.state == VISITED) return; // detect cycle
	v.state = VISITED;
	for (Vertex n : v.neighbors) {
		dfs(n);
	}
	v.state = UNVISITED;
}















