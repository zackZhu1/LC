// reverse a directed graph
// https://www.geeksforgeeks.org/transpose-graph/
import java.util.*;

public class test {
	public static Map<Integer, List<Integer>> reverse(Map<Integer, List<Integer>> graph) {
		Map<Integer, List<Integer>> graph2 = new HashMap<>();
		for (Integer key : graph.keySet()) {
			List<Integer> neighbors = graph.get(key);
			for (Integer nei : neighbors) {
				if (!graph2.containsKey(nei)) {
					graph2.put(nei, new ArrayList<>());
				}
				graph2.get(nei).add(key);
			}
		}
		return graph2;
	}

	public static void traverseGraph(Map<Integer, List<Integer>> graph) {
		for (Integer key : graph.keySet()) {
			System.out.print(key.toString() + ": ");
			List<Integer> neighbors = graph.get(key);
			for (Integer nei : neighbors) {
				System.out.print(nei.toString() + ", ");
			}
			System.out.println();
		}
	}
	
	private static Map<Integer, List<Integer>> createTestGraph() {
		Map<Integer, List<Integer>> testGraph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>(); list1.add(2);
		testGraph.put(1, list1);
		
		List<Integer> list2 = new ArrayList<>(); list2.add(3);
		testGraph.put(2, list2);
		
		List<Integer> list3 = new ArrayList<>(); list3.add(1);
		testGraph.put(3, list3);
		
		return testGraph;
		
	}
	
	public static void main(String[] args) {
		Map<Integer, List<Integer>> graph = createTestGraph();
		traverseGraph(graph);
		Map<Integer, List<Integer>> reversedGraph = reverse(graph);
		traverseGraph(reversedGraph);
	}
}
