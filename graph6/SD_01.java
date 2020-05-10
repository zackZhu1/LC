import java.util.*;

class Node {
	int val;
	List<Node> neighbors = new ArrayList<>();
	Node(int val) {
		this.val = val;
	}
}

public class test {
	public static List<Node> findOneShortestPath(Node init, Node goal) {
		
		// 1. BFS 
		Map<Node, Node> prevMap = new HashMap<>(); // 1. mark visited  2. record parent node
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(init);
		prevMap.put(init, null);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node nei : cur.neighbors) {
				if (prevMap.containsKey(nei)) continue;
				
				prevMap.put(nei, cur);
				if (nei == goal) break;
				queue.offer(nei);
			}
		}
		
		// 2. reversely construct the path
		List<Node> path = new ArrayList<>();
		path.add(goal);
		Node cur = goal;
		while (cur != init) {
			cur = prevMap.get(cur);
			path.add(cur);
		}
		Collections.reverse(path);
		return path;
	}
	
	
	public static void main(String[] args) {
		Node init = new Node(1);
		Node goal = new Node(3);
		Node node2 = new Node(2);
		Node node4 = new Node(4);
		init.neighbors.add(node2);
		init.neighbors.add(node4);
		node2.neighbors.add(goal);
		goal.neighbors.add(init);
		node4.neighbors.add(goal);
		
		List<Node> res = bfs(init, goal); 
		for (Node node : res) {
			System.out.print(node.val + " ");
		}
	}
}







