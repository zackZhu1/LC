import java.util.*;

import org.w3c.dom.Node;

public class test {
	public static Map<Integer, List<Integer>> graph = new HashMap<>();
	
	public static void createMap() {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		List<Integer> list4 = new ArrayList<>();
		List<Integer> list5 = new ArrayList<>();
		list1.add(2); list1.add(4);
		list2.add(3);
		list3.add(1);
		list4.add(3); list4.add(5);
		list5.add(3);
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(4, list4);
		graph.put(5, list5);
	}
	
	public static List<Integer> findKthlevelNodes(int seed, int kthLevel) {
		return bfs3(seed, kthLevel);
	}
	
	// bfs without mark visited
	private static List<Integer> bfs(int seed, int kthLevel) {
		int level = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(seed);
		while (!queue.isEmpty()) {
			// dedupe nodes at kth level
			if (level == kthLevel) {
				System.out.println("here");
				Set<Integer> set = new HashSet<>();
				while (!queue.isEmpty()) {
					int cur = queue.poll();
					if (set.contains(cur)) {
						continue;
					} else {
						set.add(cur);
					}
				}
				return new ArrayList<>(set);
			}
			
			int size = queue.size(); 
			for (int i = 0; i < size; i++) { // level by level
				Integer cur = queue.poll();
				for (Integer nei : graph.get(cur)) {
					queue.offer(nei);
				}
			}
			level++;
		}
		return new ArrayList<>();
	}

	// mark visited at expansion
	private static List<Integer> bfs2(int seed, int kthLevel) {
		int level = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(seed);
		while (!queue.isEmpty()) {
			// dedupe at each level
			Set<Integer> visited = new HashSet<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer cur = queue.poll();
				if (!visited.add(cur)) {
					continue;
				}
				for (Integer nei : graph.get(cur)) {
					queue.offer(nei);
				} 
			}
			if (level == kthLevel) {
				return new ArrayList<>(visited);
			}
			
			level++; // next level
		}
		return new ArrayList<>();
	}
	
	// mark visited at generation
	private static List<Integer> bfs3(int seed, int kthLevel) {
		Set<Integer> curLevel = new HashSet<>();
		curLevel.add(seed);
		int level = 0;
		
		while (!curLevel.isEmpty()) {
			Set<Integer> nextLevel = new HashSet<>();
			for (Integer cur : curLevel) {
				for (Integer nei : graph.get(cur)) {
					nextLevel.add(nei);
				}
			}
			level++;
			
			if (level == kthLevel) {
				return new ArrayList<>(nextLevel);
			}
			curLevel = nextLevel;
		}
		return new ArrayList<>();
	}
	
	public static void main(String[] args) {
		createMap();
		List<Integer> res = findKthlevelNodes(1, 2);
		for (Integer node : res) {
			System.out.print(node + " ");
		}
	}
}


// bfs1: Time = O(|b|^k)
// bfs2: Time = O((|V| + |E|) * k) Space = O(|E|)
// bfs3: Time = O((|V| + |E|) * k) space = O(|V|)




