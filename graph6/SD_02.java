import java.util.*;

import org.w3c.dom.Node;

public class test {
	static Map<Integer, List<Integer>> graph = new HashMap<>();
	
	static void createTestGraph() {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		List<Integer> list4 = new ArrayList<>();
		List<Integer> list5 = new ArrayList<>();
		list1.add(2); list1.add(4);
		list2.add(3); list2.add(5);
		list3.add(1); 
		list4.add(3);
		list5.add(3);
		graph.put(1, list1); 
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(4, list4);
		graph.put(5, list5);
	}
	
	// solution1: BFS + DFS
	public static Map<Integer, List<Integer>> getParentMap(Integer init, Integer goal) {
		Map<Integer, List<Integer>> parentMap = new HashMap<>(); // record all parents for each node
		Map<Integer, Integer> levels = new HashMap<>();  // record the shortest path for each node 
		Queue<Integer> queue = new ArrayDeque<>();
		
		parentMap.put(init, new ArrayList<>());
		queue.offer(init);
		levels.put(init, 0);
		
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer cur = queue.poll();
				for (Integer nei : graph.get(cur)) {
					// mark visited at generation
					if (!levels.containsKey(nei)) {
						levels.put(nei, level + 1);
						parentMap.put(nei, new ArrayList<>());
						parentMap.get(nei).add(cur);
						queue.offer(nei);
					} else if (level + 1 == levels.get(nei)) {
						parentMap.get(nei).add(cur);
					}
				}
			}
			// when to terminate: the first level we have goal node
			if (levels.containsKey(goal)) break;
			
			level++;
		}
		
		return parentMap;
	}
	
	public static List<List<Integer>> allShortestPaths(Map<Integer, List<Integer>> parentMap, Integer goal) {
		List<List<Integer>> allShortestPaths = new ArrayList<>();
		List<Integer> curPath = new ArrayList<>();
		dfs(goal, parentMap, curPath, allShortestPaths);
		return allShortestPaths;
	}
	
	private static void dfs(Integer curNode, Map<Integer, List<Integer>> parentMap, List<Integer> curPath, List<List<Integer>> allPaths) {
		curPath.add(curNode);
		if (parentMap.get(curNode).isEmpty()) {
			List<Integer> path = new ArrayList<>(curPath); // deep copy
			Collections.reverse(path);
			allPaths.add(path);
		} else {
			for (Integer nei : parentMap.get(curNode)) {
				dfs(nei, parentMap, curPath, allPaths);
			}
		}
		curPath.remove(curPath.size() - 1);
	}
	
	private static void dfs2(Integer curNode, Map<Integer, List<Integer>> parentMap, List<Integer> curPath, List<List<Integer>> allPaths) {
		// base case 
		if (parentMap.get(curNode).isEmpty()) {
			curPath.add(curNode);
			List<Integer> path = new ArrayList<>(curPath); // deep copy
			Collections.reverse(path);
			allPaths.add(path);
			
			curPath.remove(curPath.size() - 1);
			return;
		}
		
		// induction rule
		curPath.add(curNode);
		for (Integer nei : parentMap.get(curNode)) {
			dfs(nei, parentMap, curPath, allPaths);
		}
		curPath.remove(curPath.size() - 1);
	}
	
	// solution2: level limited DFS
	public static List<List<Integer>> levelLimitedDFS(int init, int goal) {
		List<List<Integer>> allShortestPaths = new ArrayList<>();
		for (int i = 0; i < graph.size(); i++) {
			Set<Integer> visited = new HashSet<>();
			List<List<Integer>> allPaths = new ArrayList<>();
			List<Integer> curPath = new ArrayList<>();
			dfs3(curPath, allPaths, init, goal, visited, i, 0);
			if (!allPaths.isEmpty()) {
				allShortestPaths = allPaths;
				break;
			}
		}
		return allShortestPaths;
	}
	
	private static void dfs3(List<Integer> curPath, List<List<Integer>> allPaths, int curNode, int goal, Set<Integer> visited, int maximumLevel, int curLevel) {
		if (curLevel > maximumLevel) return;
		if (curNode == goal) {
			visited.add(curNode);
			curPath.add(curNode);
			List<Integer> path = new ArrayList<>(curPath); // deep copy
			allPaths.add(path);
			
			curPath.remove(curPath.size() - 1);
			visited.remove(curNode);
			return;
		}
		
		visited.add(curNode);
		curPath.add(curNode);
		for (Integer nei : graph.get(curNode)) {  // generate
			if (visited.contains(nei)) continue;
			dfs3(curPath, allPaths, nei, goal, visited, maximumLevel, curLevel + 1);
		}
		curPath.remove(curPath.size() - 1);
		visited.remove(curNode);
	}
	
	public static void main(String[] args) {
		createTestGraph();
//		Map<Integer, List<Integer>> parentMap = getParentMap(1, 3);
//		List<List<Integer>> allShortestPaths = allShortestPaths(parentMap, 3);
		
		List<List<Integer>> allShortestPaths = levelLimitedDFS(1, 3);
		for (List<Integer> path : allShortestPaths) {
			for (Integer node : path) {
				System.out.print(node.toString() + " ");
			}
			System.out.println();
		}
	}
}




