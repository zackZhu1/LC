import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

public class test {
	/*
	 * selection sort
	 */
	public static int[] selectionSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		for (int i = 0; i < array.length - 1; i ++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
		return array;
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
   
	/* 
	 * merge sort
	 */
	public static int[] mergeSort(int[] array) {
		if (array == null) {
			return array;
		}
		
		// allocate helper array to help merge step
		int[] helper = new int[array.length];
		mergeSort(array, helper, 0, array.length - 1);
		return array;
	}

	private static void mergeSort(int[] array, int[] helper, int left, int right) {
		if (left >= right) return;
		int mid = left + (right - left) / 2;
		// step1: sort two halves
		mergeSort(array, helper, left, mid);
		mergeSort(array, helper, mid + 1, right);
		// step2: merge two halves
		merge(array, helper, left, mid, right);
	}
	
	private static void merge(int[] array, int[] helper, int left, int mid, int right) {
		for (int i = left; i <= right; i++) {
			helper[i] = array[i];
		}
		
		int leftIndex = left;
		int rightIndex = mid + 1;
		while (leftIndex <= mid && rightIndex <= right) {
			if (helper[leftIndex] <= helper[rightIndex]) {
				array[left++] = helper[leftIndex++];
			} else {
				array[left++] = helper[rightIndex++];
			}
		}
		// post-processing
		while (leftIndex <= mid) {
			array[left++] = helper[leftIndex++];
		}
	}
	
	/*
	 *  quick sort
	 */
	public static int[] quickSort(int[] array) {
		if (array == null) return array;
		quickSort(array, 0, array.length - 1);
		return array;
	}
	
	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) return;
		// step1: define a pivot and partition the array into two halves
		int pivotPos = partition(array, left, right);
		// step2: pivot is already at its position, sort two halves 
		quickSort(array, left, pivotPos - 1);
		quickSort(array, pivotPos + 1, right);
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivotIndex = right;
		int pivot = array[right];
		
		int leftBound = left; // exclude
		int rightBound = right - 1; // exclude 
		while (leftBound <= rightBound) {
			if (array[leftBound] < pivot) {
				leftBound++;
			} else if (array[rightBound] >= pivot) {
				rightBound--;
			} else {
				swap(array, leftBound++, rightBound--);
			}
		}
		swap(array, leftBound, right);
		return leftBound;
	}
	
	/*
	 * move 0s to the end
	 */
	public static int[] moveZero(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			if (array[left] != 0) {
				left++;
			} else if (array[right] == 0) {
				right--;
			} else {
				swap(array, left++, right--);
			}
		}
		return array;
	}
	
	/*
	 * Rainbow sort
	 */
	public static int[] rainbowSort(int[] array) {
		// -1: (... , neg) exclude
		//  1: (one, ...) exclude
		//  0: [neg, zero)
		//  explored area: [zero, one]
		int neg = 0;
		int one = array.length - 1;
		int zero = 0;
		while (zero <= one) {
			if (array[zero] == -1) {
				swap(array, neg++, zero++);
			} else if (array[zero] == 0) {
				zero++;
			} else {
				swap(array, zero, one--);
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
//		int[] array = {2, 4, 1, 5, 3};
//		int[] res = quickSort(array);
		
//		int[] array = {1, 0, 2, 0, 5};
//		int[] res = moveZero(array);
		
		int[] array = {1, 0, 1, -1, -1, 0};
		int[] res = rainbowSort(array);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}
}

