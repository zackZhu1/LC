class Solution {
    private static int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word, 0, visited)) return true;
                }
            }
        }        
        return false;
    }
    
    private boolean dfs(char[][] matrix, int x, int y, String word, int index, boolean[][] visited) {
        if (index == word.length()) return true;
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) return false;
        if (visited[x][y]) return false;
        if (matrix[x][y] != word.charAt(index)) return false;
        
        visited[x][y] = true;
        for (int[] DIR : DIRS) {
            if (dfs(matrix, x + DIR[0], y + DIR[1], word, index + 1, visited)) return true;
        }
        visited[x][y] = false;
        return false;
    }
}