/*
分析：
1. state: <i, j>
2. init state: all the borders
3. cost: 当前水位线的高度 = max(cost[prev], matrix[cur])
4. expand/generate rule: 4个方向
5. dedupe at generation
*/

class Solution {
    class Node {
        int x;
        int y;
        int height; // 水位线的高度
        Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    
    class MyComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.height - y.height;
        }
    }
    
    private static int[][] DIRS = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} }; 
    
    public int trapRainWater(int[][] heightMap) {
        int sum = 0;
        int M = heightMap.length;
        int N = heightMap[0].length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(M * N, new MyComparator());
        boolean[][] visited = new boolean[M][N];
        
        // put all boarders into the queue
        for (int i = 0; i < M; i++) {
            visited[i][0] = true;
            visited[i][N - 1] = true;
            minHeap.offer(new Node(i, 0, heightMap[i][0]));
            minHeap.offer(new Node(i, N - 1, heightMap[i][N - 1]));
        }
        
        for (int j = 1; j < N - 1; j++) {
            visited[0][j] = true;
            visited[M - 1][j] = true;
            minHeap.offer(new Node(0, j, heightMap[0][j]));
            minHeap.offer(new Node(M - 1, j, heightMap[M - 1][j]));
        }
        
        while (!minHeap.isEmpty()) {
            // expand
            Node cur = minHeap.poll();
            
            for (int[] DIR : DIRS) {
                int nei_x = cur.x + DIR[0];
                int nei_y = cur.y + DIR[1];
                if (nei_x < 0 || nei_x >= M || nei_y < 0 || nei_y >= N) continue;
                if (visited[nei_x][nei_y]) continue;
                
                int height = Math.max(heightMap[nei_x][nei_y], cur.height);
                sum += height - heightMap[nei_x][nei_y];
                Node nei = new Node(nei_x, nei_y, height);
                minHeap.offer(nei);
                visited[nei_x][nei_y] = true;
            }
        }
        
        return sum;
    }
}





