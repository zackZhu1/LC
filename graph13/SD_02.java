import java.util.*;

class Node {
	int val;  // identifier
	int cost; // 往下传递所花时间
	List<Node> neis = new ArrayList<>();
	
	Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}
}

class BFSIterator {
	PriorityQueue<Node> minHeap;
	
	public BFSIterator(Node ceo) {
		minHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		minHeap.offer(ceo);
	}
	
	public boolean hasNext() {
		return !minHeap.isEmpty();
	}
	
	public Node next() {
		if (!hasNext()) return null;
		Node cur = minHeap.poll();
		System.out.println(cur.val + " " + cur.cost);
		
		for (Node nei : cur.neis) {
			nei.cost += cur.cost;
			minHeap.offer(nei);
		}
		return cur;
	}
}

class BFSIterator2 {
	PriorityQueue<Node> minHeap;
	Set<Node> expanded; // expand一次 generate多次
	
	public BFSIterator2(Node ceo) {
		minHeap = new PriorityQueue<>();
		expanded = new HashSet<>();
		minHeap.offer(ceo);
		expanded.add(ceo);
	}
	
	public boolean hasNext() {
		// skip all elements already expanded
		while (!minHeap.isEmpty() && expanded.contains(minHeap.peek())) minHeap.poll();
		return !minHeap.isEmpty();
	}
	
	public Node next() {
		if (!hasNext()) return null;
		// expand
		Node cur = minHeap.poll();
		// generate
		for (Node nei : cur.neis) {
			int new_cost = cur.cost + nei.cost;
			if (new_cost < nei.cost) {
				nei.cost = new_cost;
				minHeap.offer(nei);
			}
		}
		return cur;
	}
}

public class test {
	private static Node createTestCase() {
		Node ceo = new Node(1, 1);
		Node node2 = new Node(2, 2);
		Node node3 = new Node(3, 100);
		Node node5 = new Node(5, 0); // leaf node
		Node node6 = new Node(6, 0);
		Node node7 = new Node(7, 0);
		Node node4 = new Node(4, 0);
		ceo.neis.add(node2); ceo.neis.add(node3); ceo.neis.add(node4);
		node2.neis.add(node5); node2.neis.add(node6);
		node3.neis.add(node7);
		return ceo;
	}
	
	// solution1: pure recursion 
	private static int largestPathSum(Node root) {
		if (root == null) return 0;
		if (root.neis.size() == 0) return root.cost;
		
		int max = 0;
		for (Node nei : root.neis) {
			max = Math.max(max, largestPathSum(nei));
		}
		max += root.cost;
		return max;
	}
	
	// solution2: backtracking
	private static int largestPathSum2(Node root) {
		int[] max = new int[] {0};
		dfs(root, max, 0);
		return max[0];
	}
	
	private static void dfs(Node root, int[] max, int prev) {
		if (root == null) return;
		if (root.neis.size() == 0) {
			max[0] = Math.max(prev + root.cost, max[0]);
			return;
		}
		for (Node nei : root.neis) {
			dfs(nei, max, prev + root.cost);
		}
	}
	
	public static void main(String[] args) {
		Node ceo = createTestCase();
		System.out.println(largestPathSum2(ceo));
		
//		BFSIterator iterator = new BFSIterator(ceo);
//		while (iterator.hasNext()) {
//			Node node = iterator.next();
//		}
	}
}




