/*
分析：
least time -> shortest path
1. state: <i, j>
2. initial state: <0, 0>   goal state: <N-1, N-1>
3. cost: cost(cur) = max(cost(prev), matrix[cur])
4. expand/generation rule: expand(i, j), generate (i+1, j) (i-1, j) (i, j+1) (i, j-1)
5. dedupe at generation
*/

class Solution {
    class Node {
        int x;
        int y;
        int val;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    class MyComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.val - y.val;
        }
    }
    
    private static int[][] DIRS = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} }; 
    
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(N * N, new MyComparator());
        boolean[][] visited = new boolean[N][N];
        minHeap.offer(new Node(0, 0, grid[0][0]));
        visited[0][0] = true;
        
        Node node = null;
        while (!visited[N - 1][N - 1]) {
            // expand
            Node cur = minHeap.poll();
            int x = cur.x;
            int y = cur.y;
            
            // dedupe at generation
            for (int[] DIR : DIRS) {
                int nei_x = cur.x + DIR[0];
                int nei_y = cur.y + DIR[1];
                if (nei_x < 0 || nei_x >= N || nei_y < 0 || nei_y >= N) continue;
                if (visited[nei_x][nei_y]) continue;
                
                int val = Math.max(grid[nei_x][nei_y], cur.val);
                Node nei = new Node(nei_x, nei_y, val);
                minHeap.offer(nei);
                visited[nei_x][nei_y] = true;
                
                if (nei_x == N - 1 && nei_y == N - 1)
                    node = nei;
            }
        }
        return node.val;
    }
}











