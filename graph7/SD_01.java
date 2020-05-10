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
	
	// solution1: use set as the queue to store all nodes with indegree == 0
	public static boolean isTopoOrder(List<Integer> order) {
		int[] indegree = getIndegree();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) set.add(i);
		}
		
		int index = 0;
		while (!set.isEmpty()) {
			Integer cur = order.get(index);
			if (!set.contains(cur)) return false;
			set.remove(cur);
			index++;
			
			for (int child : graph.get(cur)) {
				indegree[child]--;
				if (indegree[child] == 0) set.add(child);
			}
		}
		
		return true;
	}
	
	// solution2: use indegree array to check
	public static boolean isTopoOrder2(List<Integer> order) {
		int[] indegree = getIndegree();

		for (int i = 0; i < order.size(); i++) {
			Integer cur = order.get(i);
			if (indegree[cur] != 0) return false;
			for (int child : graph.get(cur)) {
				indegree[child]--;
			}
		}
		
		return true;
	}
	
	// solution3: for each task, check if all its dependencies are placed before
	public static boolean isTopoOrder3(List<Integer> order) {
		// 1. find dependencies
		Map<Integer, List<Integer>> dependencies = new HashMap<>();
		for (int i = 0; i < graph.size(); i++) {
			dependencies.put(i, new ArrayList<>());
		}
		for (Integer node : graph.keySet()) {
			for (Integer nei : graph.get(node)) {
				dependencies.get(nei).add(node);
			}
		}
		
		// 2. for each task
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < order.size(); i++) {
			Integer curNode = order.get(i);
			List<Integer> list = dependencies.get(curNode);
			if (list.size() == 0) {
				set.add(curNode);
			} else {
				for (Integer node : list) {
					if (!set.contains(node)) return false;
				}
				set.add(curNode);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		createTestCase();
		List<Integer> order = new ArrayList<>();
		order.add(3); order.add(1); order.add(2); order.add(4); order.add(0); 
		
		List<Integer> order2 = new ArrayList<>();
		order2.add(3); order2.add(2); order2.add(1); order2.add(4); order2.add(0); 
		
		System.out.println(isTopoOrder3(order));
		
		System.out.println(isTopoOrder3(order2));
		
		
	}
}







