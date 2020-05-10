import java.util.*;

public class test {
	private static Map<Integer, List<Integer>> graph = new HashMap<>();
	
	private static void createTestCase() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(2); list1.add(4);
		List<Integer> list2 = new ArrayList<>();
		list2.add(4);
		List<Integer> list3 = new ArrayList<>();
		list3.add(2); list3.add(4);
		List<Integer> list4 = new ArrayList<>();
		list4.add(0);
		List<Integer> list0 = new ArrayList<>();
		graph.put(0, list0);
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(4, list4);
	}
	
	private static int[] getIndegree() {
		int[] indegree = new int[graph.size()];
		for (int i = 0; i < graph.size(); i++) {
			for (Integer child : graph.get(i)) {
				indegree[child]++;
			}
		}
		return indegree;
	}
	
	// solution1: BFS find topological order,  but execute all possible tasks at one time
	public static int findMinimumTime() {
		int[] indegree = getIndegree();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) queue.offer(i);
		}
		
		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer cur = queue.poll();
				for (int nei : graph.get(cur)) {
					indegree[nei]--;
					if (indegree[nei] == 0) queue.offer(nei);
				}
			}
			time++;
		}
		return time;
	}
	
	// solution2: recursion + memorization
	public static int findMinimumTime2() {
		// 1. get dependency 
		Map<Integer, List<Integer>> dependencies = new HashMap<>();
		for (int i = 0; i < graph.size(); i++) {
			dependencies.put(i, new ArrayList<>());
		}
		for (Integer node : graph.keySet()) {
			for (Integer nei : graph.get(node)) {
				dependencies.get(nei).add(node);
			}
		}
		
		// 2. dfs
		Map<Integer, Integer> visited = new HashMap<>(); // 1. mark visited 2. time for each node
		int max = 1;
		for (int i = 0; i < graph.size(); i++) {
			if (visited.containsKey(i))
				max = Math.max(visited.get(i), max);
			else 
				max = Math.max(dfs(i, visited, dependencies), max);
		}

		return max;
	}
	
	private static int dfs(int node, Map<Integer, Integer> visited, Map<Integer, List<Integer>> dependencies) {
		if (visited.containsKey(node)) return visited.get(node);
		
		List<Integer> list = dependencies.get(node);
		if (list.size() == 0) {
			visited.put(node, 1);
			return 1;
		}
		
		int nei_max_time = 0;
		for (Integer nei : list) {
			nei_max_time = Math.max(dfs(nei, visited, dependencies), nei_max_time);
		}
		// mark visited
		visited.put(node, nei_max_time + 1);
		
		return nei_max_time + 1;
	}
	
	public static void main(String[] args) {
		createTestCase();
		System.out.println(findMinimumTime2());
	}
}







