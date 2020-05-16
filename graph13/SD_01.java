class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length]; // 每个点的最短路径 / cost
        for (int[] row : distance) 
            Arrays.fill(row, Integer.MAX_VALUE);
        
        distance[start[0]][start[1]] = 0;
        bfs2(maze, start, distance);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
    
    private static int[][] DIRS = {{0,1},{0,-1},{-1,0},{1,0}};
    private void bfs2(int[][] maze, int[] start, int[][] distance) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[] {start[0], start[1], 0});
        
        while (!queue.isEmpty()) {
            // expand
            int[] s = queue.poll();
            // dedupe at expansion
            if (distance[s[0]][s[1]] < s[2])
                continue;
            
            // generation
            for (int[] DIR : DIRS) {
                int x = s[0] + DIR[0];
                int y = s[1] + DIR[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += DIR[0];
                    y += DIR[1];
                    count++;
                }
                
                // 可能多次generate
                if (distance[s[0]][s[1]] + count < distance[x - DIR[0]][y - DIR[1]]) {
                    // update cost 
                    distance[x - DIR[0]][y - DIR[1]] = distance[s[0]][s[1]] + count; // + count - 1 ???
                    // offer new element to the queue
                    queue.offer(new int[] {x - DIR[0], y - DIR[1], distance[x - DIR[0]][y - DIR[1]]});
                }
            }
        }
    }
}