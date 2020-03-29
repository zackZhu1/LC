class Solution {
    private static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 
            || word == null || word.length() == 0) return false;
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0, visited)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (visited[i][j]) return false;
        if (word.charAt(index) != board[i][j]) return false;
        
        visited[i][j] = true;
        for (int[] DIR : DIRS) {
            int x = i + DIR[0];
            int y = j + DIR[1];
            if (dfs(board, word, x, y, index + 1, visited)) return true;
        }
        visited[i][j] = false; // backtracking
        return false;
    }
}