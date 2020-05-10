// Find the Town Judge
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] indegrees = new int[N + 1];
        int[] outdegrees = new int[N + 1];
        for (int[] relation : trust) {
            outdegrees[relation[0]]++;
            indegrees[relation[1]]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == N - 1 && outdegrees[i] == 0) return i;
        }
        return -1;
    }
}

// optimization: use only one array
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] degrees = new int[N + 1];
        for (int[] relation : trust) {
            degrees[relation[0]]--;
            degrees[relation[1]]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (degrees[i] == N - 1) return i;
        }
        return -1;
    }
}