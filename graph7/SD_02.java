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
	
	public static List<List<Integer>> findAllTopoOrders() {
		List<List<Integer>> orders = new ArrayList<>();
		int[] input = { 0, 1, 2, 3, 4};
		int[] indegree = getIndegree();
		// permutation
		dfs(input, 0, indegree, orders);
		return orders;
	}
	
	private static void dfs(int[] input, int index, int[] indegree, List<List<Integer>> orders) {
		if (index == input.length) {
			List<Integer> list = new ArrayList<>();
			for (int node : input) list.add(node);
			orders.add(list);
			return;
		}
		
		// recursion rule
		for (int i = index; i < input.length; i++) {
			int candidate = input[i];
			if (indegree[candidate] == 0) {
				update(candidate, indegree);
				swap(input, index, i);
				dfs(input, index + 1, indegree, orders);
				swap(input, index, i);
				recover(candidate, indegree);
			}
		}
	}
	
	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	private static void update(int candidate, int[] indegree) {
		List<Integer> neighbors = graph.get(candidate);
		for (Integer nei : neighbors) {
			indegree[nei]--;
		}
	}
	
	private static void recover(int candidate, int[] indegree) {
		List<Integer> neighbors = graph.get(candidate);
		for (Integer nei : neighbors) {
			indegree[nei]++;
		}
	}
	
	public static void main(String[] args) {
		createTestCase();
		
		List<List<Integer>> res = findAllTopoOrders();
		
		for (List<Integer> list : res) {
			for (Integer node : list) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
	}
}







