// solution1:
class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] passedThrough = new int[10][10];
        updatePassedThrough(passedThrough);
        
        int[] result = new int[] {0};
        
        Set<Integer> visited = new HashSet<>();
        // for (int key = 1; key < 10; key++) {
        //     visited.add(key);
        //     dfs(key, result, passedThrough, visited, m, n);
        //     visited.remove(key);
        // }
        int count = 0;
        
        visited.add(1);
        dfs(1, result, passedThrough, visited, m, n);
        visited.remove(1); 
        count += result[0] * 4;
        
        result[0] = 0;
        visited.add(2);
        dfs(2, result, passedThrough, visited, m, n);
        visited.remove(2);
        count += result[0] * 4;
        
        result[0] = 0;
        visited.add(5);
        dfs(5, result, passedThrough, visited, m, n);
        visited.remove(5);
        
        count += result[0];
        return count;
    }
    
    private void dfs(int key, int[] result, int[][] passedThrough, Set<Integer> visited, int m, int n) {
        if (visited.size() > n) return;
        
        if (visited.size() >= m && visited.size() <= n) {
            result[0]++;
        }
        
        for (int nextKey = 1; nextKey < 10; nextKey++) {
            if (visited.contains(nextKey)) continue;
            if (passedThrough[key][nextKey] > 0 && !visited.contains(passedThrough[key][nextKey])) continue;
            visited.add(nextKey);
            dfs(nextKey, result, passedThrough, visited, m, n);
            visited.remove(nextKey);
        }
    }
    
    private void updatePassedThrough(int[][] passedThrough) {
        passedThrough[1][3] = 2;
        passedThrough[3][1] = 2;
        passedThrough[1][9] = 5;
        passedThrough[9][1] = 5;
        passedThrough[1][7] = 4;
        passedThrough[7][1] = 4;
        passedThrough[2][8] = 5;
        passedThrough[8][2] = 5;
        passedThrough[3][7] = 5;
        passedThrough[7][3] = 5;
        passedThrough[3][9] = 6;
        passedThrough[9][3] = 6;
        passedThrough[4][6] = 5;
        passedThrough[6][4] = 5;
        passedThrough[7][9] = 8;
        passedThrough[9][7] = 8;
    }
}




// solution2: 利用return value
class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] passedThrough = new int[10][10];
        updatePassedThrough(passedThrough);
        
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int key = 1; key < 10; key++) {
            visited.add(key);
            count += dfs(key, passedThrough, visited, m, n);
            visited.remove(key);
        }
        return count;
    }
    
    private int dfs(int key, int[][] passedThrough, Set<Integer> visited, int m, int n) {
        if (visited.size() > n) return 0;
        
        int count = 0;
        if (visited.size() >= m && visited.size() <= n) {
            count += 1;
        }
        
        for (int nextKey = 1; nextKey < 10; nextKey++) {
            if (visited.contains(nextKey)) continue;
            if (passedThrough[key][nextKey] > 0 && !visited.contains(passedThrough[key][nextKey])) continue;
            visited.add(nextKey);
            count += dfs(nextKey, passedThrough, visited, m, n);
            visited.remove(nextKey);
        }
        
        return count;
    }
    
    private void updatePassedThrough(int[][] passedThrough) {
        passedThrough[1][3] = 2;
        passedThrough[3][1] = 2;
        passedThrough[1][9] = 5;
        passedThrough[9][1] = 5;
        passedThrough[1][7] = 4;
        passedThrough[7][1] = 4;
        passedThrough[2][8] = 5;
        passedThrough[8][2] = 5;
        passedThrough[3][7] = 5;
        passedThrough[7][3] = 5;
        passedThrough[3][9] = 6;
        passedThrough[9][3] = 6;
        passedThrough[4][6] = 5;
        passedThrough[6][4] = 5;
        passedThrough[7][9] = 8;
        passedThrough[9][7] = 8;
    }
}
