import java.util.*;

import org.w3c.dom.Node;

public class test {
	static class Cell {
		int x;
		int y;
		Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] dirs = new int[][] {
		{0, 1}, {0, -1}, {1, 0}, {-1, 0}
	};

	
	private static boolean checkValid(int[][] matrix, int newX, int newY) {
		if(newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[0].length) {
			return false;
		}
		return true;
	}
	
	public static int[][] shortestDistance(int[][] matrix) {
		int[][] res = new int[matrix.length][matrix[0].length];
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		Queue<Cell> queue = new ArrayDeque<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					Cell cell = new Cell(i, j);
					queue.offer(cell);
					visited[i][j] = true;
				}
			}
		}
		
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Cell cur = queue.poll(); // expand
				// generate
				for (int[] dir : dirs) {
					int newX = cur.x + dir[0];
					int newY = cur.y + dir[1];
					if (checkValid(matrix, newX, newY)) {
						Cell nei = new Cell(newX, newY);
						if (!visited[newX][newY] && matrix[newX][newY] == 1) {
							res[newX][newY] = level + 1; // next level
							queue.offer(nei);
							visited[newX][newY] = true; // mark visited at generation
						}
					}
				}
			}
			level++; // update the level to next level
		}
		
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
		return res;		
	}
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0, 1, 0},
				{1, 1, 1},
				{0, 1, 0}
		};
		shortestDistance(matrix);
	}
}








