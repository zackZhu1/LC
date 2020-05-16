/*
Kth smallest element in Two Sorted Array

分析:
smallest element -> shortest path
1. state(i, j): <i, j>
2. initial state: <0, 0>
3. cost: cost of state(i, j) = A[i] + B[j]
4. expand/generation rule: expand (i, j) generate (i + 1, j) (i, j + 1)
5. dedupe @ generation 
*/


import java.util.*;

class Node {
	int i; // index in A
	int j; // index in B
	int val;
	Node(int i, int j, int val) {
		this.i = i;
		this.j = j;
		this.val = val;
	}
}

class MyComparator implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.val - y.val;
    }
}

public class test {
	public static int kthSmallestSumInTwoSortedArray(int[] A, int[] B, int k) {
		PriorityQueue<Node> minHeap = new PriorityQueue<>(A.length + B.length, new MyComparator());
		boolean[][] visited = new boolean[A.length][B.length];
		minHeap.offer(new Node(0, 0, A[0] + B[0]));
		visited[0][0] = true;
		
		Node cur = null;
		while (k > 0) {
			// expand
			cur = minHeap.poll();
			int i = cur.i;
			int j = cur.j;
			
			// dedupe at generation
			if (i + 1 < A.length && !visited[i + 1][j]) {
				minHeap.offer(new Node(i + 1, j, A[i + 1] + B[j]));
				visited[i + 1][j] = true;
			} 
			if (j + 1 < B.length && !visited[i][j + 1]) {
				minHeap.offer(new Node(i, j + 1, A[i] + B[j + 1]));
				visited[i][j + 1] = true;
			}
			
			k--;
		}
		return cur.val;
	}
	
	public static void main(String[] args) {
		int[] A = { 1, 2, 3 };
		int[] B = { 4, 5, 6 };
		System.out.println(kthSmallestSumInTwoSortedArray(A, B, 4));
	}
}

//suppose n = matrix.length
//Time = O( (|V| + |E|) * logn ) = O((n^2 + 2n^2) * logn^2) = O(n^2 * logn^2)
//Space = O(n^2) includes heap and visited





