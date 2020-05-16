/*
Kth smallest element in Young's Matrix

分析:
smallest element -> shortest path
1. state(i, j): <i, j>
2. initial state: <0, 0>
3. cost: cost of state(i, j) = matrix[i][j]
4. expand/generation rule: expand (i, j) generate (i + 1, j) (i, j + 1)
5. dedupe @ generation 
*/

class Node {
    int x;
    int y;
    int val;
    
    public Node(int x, int y, int val) {
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

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(k, new MyComparator());
        boolean[][] visited = new boolean[N][N];
        minHeap.offer(new Node(0, 0, matrix[0][0]));
        visited[0][0] = true;
        
        Node cur = null;
        while (k > 0) {
            cur = minHeap.poll(); // expand
            int x = cur.x; 
            int y = cur.y;
   
            // dedupe at generation
            if (y + 1 < N && !visited[x][y + 1]) {
                minHeap.offer(new Node(x, y + 1, matrix[x][y + 1]));
                visited[x][y + 1] = true; // mark visited;
            }
            if (x + 1 < N && !visited[x + 1][y]) {
                minHeap.offer(new Node(x + 1, y, matrix[x + 1][y]));
                visited[x + 1][y] = true;
            }
            k--;
        }
        return cur.val;
    }
}


// suppose n = matrix.length
// Time = O( (|V| + |E|) * logn ) = O((n^2 + 2n^2) * logn^2) = O(n^2 * logn^2)
// Space = O(n^2) includes heap and visited
