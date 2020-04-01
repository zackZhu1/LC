import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val) {
		this.val = val;
	}
}

class GraphNode {
	int val;
	List<GraphNode> neighbors = new ArrayList<>();
	GraphNode(int val) {
		this.val = val;
	}
}

public class test {
	
	/*
	 * K smallest in unsorted array
	 */
	// solution1: max heap
	public int[] kSmallest(int[] array, int k) {
		if (array.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1.equals(o2)) return 0;
				return o1 > o2 ? -1 : 1;
			}
		});
		
		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				maxHeap.offer(array[i]);
			} else if (array[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	// solution2: quick select
	public static int[] kSmallest2(int[] array, int k) {
		quickSelect(array, 0, array.length - 1, k - 1);
		int[] result = Arrays.copyOf(array, k);
		Arrays.sort(result);
		return result;
	}
	
	// target is index
	private static void quickSelect(int[] array, int left, int right, int target) {
		int mid = partition(array, left, right);
		if (mid == target) {
			return;
		} else if (target < mid) {
			quickSelect(array, left, mid - 1, target);
		} else {
			quickSelect(array, mid + 1, right, target);
		}
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int start = left;
		int end = right - 1;
		while (start <= end) {
			if (array[start] < pivot) {
				start++;
			} else if (array[end] >= pivot) {
				end--;
			} else {
				swap(array, start++, end--);
			}
		}
		swap(array, start, right);
		return start;
	}
	
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	/*
	 * BFS level by level
	 */
	public List<List<Integer>> layerByLayer(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			List<Integer> curLayer = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				curLayer.add(cur.val);
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			res.add(curLayer);
		}
		return res;
	}
	
	/*
	 * bipartite
	 */
	public boolean isBipartite(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> visited = new HashMap<>();
		for (GraphNode node : graph) {
			if (!bfs(node, visited)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean bfs(GraphNode node, HashMap<GraphNode, Integer> visited) {
		if (visited.containsKey(node)) return true;
		Queue<GraphNode> queue = new LinkedList<>();
		queue.offer(node);
		visited.put(node, 0);
		
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			int curGroup = visited.get(cur);
			int neiGroup = curGroup == 0 ? 1 : 0;
			for (GraphNode nei : cur.neighbors) {
				if (!visited.containsKey(nei)) {
					visited.put(nei, neiGroup);
					queue.offer(nei);
				} else if (visited.get(nei) != neiGroup) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * check if binary tree is complete
	 */
	public boolean isCompleted(TreeNode root) {
		if (root == null) return true;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean flag = false;
		
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur.left == null) {
				flag = true;
			} else if (flag) {
				return false;
			} else {
				queue.offer(cur.left);
			}
			
			if (cur.right == null) {
				flag = true;
			} else if (flag) {
				return false;
			} else {
				queue.offer(cur.right);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] array = { 3, 4, 1, 5, 2};
		int[] res = kSmallest2(array, 4);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}
}








