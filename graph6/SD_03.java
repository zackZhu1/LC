// word ladder
// BFS

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) return 0;
        
        Set<String> wordSet = new HashSet<>(wordList);
        return bfs(beginWord, endWord, wordSet);
    }
    
    private int bfs(String beginWord, String endWord, Set<String> wordSet) {
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        
        visited.add(beginWord);
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll(); // expand
                if (matched(curWord, endWord)) {
                    level++;
                    return level;
                }
                // generate 
                for (String nei : wordSet) {
                    if (isNeighbor(curWord, nei) && !visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }    
            }
            level++;
        }
        return 0;
    }
    
    private boolean matched(String curWord, String endWord) {
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != endWord.charAt(i)) return false;
        }
        return true;
    } 
    
    private boolean isNeighbor(String curWord, String wordinSet) {
        int count = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != wordinSet.charAt(i)) {
                count += 1;
            }
        }
        return count == 1;
    }
}





