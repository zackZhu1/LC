// word ladder
// BFS

public class test {
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return 0;
        }
        
        Set<String> wordSet = new HashSet<>(wordList);
        return bfs(beginWord, endWord, wordSet);
    }
    
    private static int bfs(String beginWord, String endWord, Set<String> wordSet) {
        int level = 0;
        Map<String, String> visited = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        
        visited.put(beginWord, null);
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            if (matched(curWord, endWord)) {
            	// print the path
            	String cur = endWord;
                System.out.print(cur + " ");
                while (!cur.equals(beginWord) && cur != null) {
                	cur = visited.get(cur);
                	System.out.print(cur + " ");
                }
                
                return level;
            }
            for (String nei : wordSet) {
                if (isNeighbor(curWord, nei) && !visited.containsKey(nei)) {
                    visited.put(nei, curWord);
                    queue.offer(nei);
                }
            }   
        }
        level++;
        return -1;
    }
	
    private static boolean matched(String curWord, String endWord) {
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != endWord.charAt(i)) return false;
        }
        return true;
    }
    
    private static boolean isNeighbor(String curWord, String wordinSet) {
    	// System.out.println(curWord + " vs " + wordinSet);
    	int count = 0;
    	for (int i = 0; i < curWord.length(); i++) {
    		if (curWord.charAt(i) != wordinSet.charAt(i)) {
    			count += 1;
    		}
    	}
    	boolean flag = count == 1;
    	// System.out.println(flag);
    	return flag;
    }
    
	public static void main(String[] args) {
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(ladderLength("hit", "cog", wordList));
	}
}








