import java.util.*;

public class test {
	public static void sortKDiff(int[] array, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		// 1. put k + 1 elements into the minHeap and handle a corner case
		for (int i = 0; i <= k && i < array.length; i++) {
			minHeap.offer(array[i]);
		}
		// 2. determine the elements one by one
		for (int i = 0; i < array.length; i++) {
			array[i] = minHeap.poll();
			if (i + k + 1 < array.length) {
				minHeap.offer(array[i + k + 1]);
			}
			
			System.out.print(array[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		int[] array = {3, 2, 1, 5, 4, 6};
		sortKDiff(array, 2);
	}
}




