import java.util.*;

class Node {
	Integer value;
	List<Node> children;
	public Node(Integer value) {
		this.value = value;
		children = new ArrayList<>();
	}
}


class Pair {
	int nodeId;
	int parentId;
	Pair (int nodeId, int parentId) {
		this.nodeId = nodeId;
		this.parentId = parentId;
	}
}

public class test {
	// solution1: preorder + construct tree at same time
	public static Node construct(List<Pair> pre) {
		if (pre == null || pre.isEmpty()) return null;
		return helper(new ArrayDeque<>(pre));
	}
	
	private static Node helper(Deque<Pair> pre) {
		Node root = new Node(pre.pollFirst().nodeId);
		while (!pre.isEmpty() && pre.peekFirst().parentId == root.value) {
			root.children.add(helper(pre));
		}
		return root;
	}
	
	public static void preOrder(Node node) {
        if (node == null) return;
        
        System.out.print(node.value + " ");
        for (Node child : node.children) {
            preOrder(child);
        }
    }
	
	// use global variable index instead of converting list to deque
	private static int index = 0;
	public static Node construct2(List<Pair> pre) {
		Node root = new Node(pre.get(index).nodeId);
		index++;
		while (index != pre.size() && pre.get(index).parentId == root.value) {
			root.children.add(construct2(pre));
		}
		return root;
	}
	
	// solution2: iterative 
	// 	stack: 记录没有构造完的subtree的root
	public static Node construct3(List<Pair> pairs) {
		Deque<Node> stack = new ArrayDeque<>();
		Node root = new Node(pairs.get(0).nodeId);
		stack.push(root);
		for (int i = 1; i < pairs.size(); i++) {
			Pair cur = pairs.get(i);
			while (stack.peek().value != cur.parentId) {
				stack.pop();
			}
			Node curNode = new Node(cur.nodeId);
			stack.peek().children.add(curNode);
			stack.push(curNode);
		}
		return root;
	}
	
	// follow-up: check if input list is valid or not
	public static boolean isValid(List<Pair> pairs) {
		Deque<Node> stack = new ArrayDeque<>();
		Set<Integer> traversed = new HashSet<>();
		
		Node root = new Node(pairs.get(0).nodeId);
		stack.push(root);
		traversed.add(root.value);
		
		for (int i = 1; i < pairs.size(); i++) {
			Pair cur = pairs.get(i);
			
			// first check: no duplicates
			if (traversed.add(cur.nodeId)) return false;
			
			while (stack.peek().value != cur.parentId) {
				stack.pop();
			}
			
			// second check: 当前node的parent只能是stack里面的 因为preorder
			if (stack.isEmpty()) return false;
			
			Node curNode = new Node(cur.nodeId);
			stack.peek().children.add(curNode);
			stack.push(curNode);
		}
		return true;
	}
	
	public static void main(String[] args) {
		Pair p1 = new Pair(3, -1);
		Pair p2 = new Pair(1, 3);
		Pair p3 = new Pair(2, 3);
		Pair p4 = new Pair(4, 2);
		Pair p5 = new Pair(5, 2);
		Pair p6 = new Pair(6, 3);
		
		List<Pair> pre = new ArrayList<>();
		pre.add(p1); pre.add(p2); pre.add(p3); pre.add(p4); pre.add(p5); pre.add(p6);
		Node root = construct3(pre);
		preOrder(root);
	}
}







