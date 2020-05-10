import java.util.*;

class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class test {
	private static int[][] DIRS = new int[][] {
		{0, 1}, {0, -1}, {1, 0}, {-1, 0}
	};
	
	private static boolean checkValid(int[][] matrix, int newX, int newY) {
		if(newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[0].length) {
			return false;
		}
		return true;
	}
	
	public static int[][] shortestDistance(int[][] matrix, int k) {
		int[][] res = new int[matrix.length][matrix[0].length];
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		
		// 1. insert all 1 nodes into the queue 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					Node node = new Node(i, j);
					queue.offer(node);
					visited[i][j] = true;
					res[i][j] = 1;
				}
			}
		}
		
		// 2. do k level BFS with mark visited
		int level = 0;
		while (!queue.isEmpty() && level < k) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				for (int[] DIR : DIRS) {
					int new_x = cur.x + DIR[0];
					int new_y = cur.y + DIR[1];
					if (checkValid(matrix, new_x, new_y)) {
						Node nei = new Node(new_x, new_y);
						if (!visited[new_x][new_y] && matrix[new_x][new_y] == 0) {
							res[new_x][new_y] = 1;
							queue.offer(nei);
							visited[new_x][new_y] = true;			
						}
					}
				}
			}
			level++;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1},
				{0, 0, 0, 0, 0}
		};
		int[][] res = shortestDistance(matrix,2);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}
}







