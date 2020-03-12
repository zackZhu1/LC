class Cell {
	int x; 
	int y;
}

static int[][] dirs = new int[][] {
	{0, 1}, {0, -1}, {1, 0}, {-1, 0}
};

public void fillColor(int[][] matrix, int x, int y, int color) {
	int oldColor = matrix[x][y];
	if (oldColor == color) return;

	Queue<Cell> queue = new LinkedList<>();
	Cell start = new Cell(x, y);
	queue.offer(start);
	matrix[x][y] = color; // mark visited

	while (!queue.isEmpty()) {
		Cell cur = queue.poll();
		for (int[] dir : dirs) {
			int newX = cur.x + dir[0];
			int newY = cur.y + dir[1];
			Cell nei = new Cell(newX, newY);
			if (checkValid(matrix, newX, newY) && matrix[x][y] == oldColor) {
				matrix[newX][newY] = color; // mark visited at generation
				queue.offer(nei);
			}
		}
	}
}

// BFS: Time = O(|V| + |E|) Space = O(n)
// DFS: Time = O(|V| + |E|) Space = O(n^2)