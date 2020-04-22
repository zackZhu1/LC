// smallest string with swaps
// https://www.youtube.com/watch?v=qosJ4632QAg
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // step1: create the graph (element is index in s)
        Map<Integer, List<Integer>> map = createGraph(s, pairs);
        
        // step2: find connected component
        //        sort each component lexicographically
        boolean[] visited = new boolean[s.length()];
        char[] res = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                List<Integer> indexList = new ArrayList<>();
                List<Character> valueList = new ArrayList<>();
                dfs(s, map, visited, indexList, valueList, i);
                Collections.sort(indexList);
                Collections.sort(valueList);
                
                for (int j = 0; j < indexList.size(); j++) 
                    res[indexList.get(j)] = valueList.get(j);
            }
        }
        return new String(res);
    }
    
    private void dfs(String s, Map<Integer, List<Integer>> map, boolean[] visited, List<Integer> indexList, List<Character> valueList, int i) {
        if (visited[i]) return;
        
        // mark visited
        visited[i] = true;
        indexList.add(i);
        valueList.add(s.charAt(i));
        if (!map.containsKey(i)) return; // key !
        
        List<Integer> neighbors = map.get(i);
        for (int k = 0; k < neighbors.size(); k++) 
            dfs(s, map, visited, indexList, valueList, neighbors.get(k));
    }
    
    private Map<Integer, List<Integer>> createGraph(String s, List<List<Integer>> pairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            int index1 = pairs.get(i).get(0);
            int index2 = pairs.get(i).get(1);
            if (!graph.containsKey(index1)) graph.put(index1, new ArrayList<>());
            if (!graph.containsKey(index2)) graph.put(index2, new ArrayList<>());
            graph.get(index1).add(index2);
            graph.get(index2).add(index1);
        }
        return graph;
    }
}