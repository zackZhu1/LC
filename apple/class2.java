import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

class Dictionary {
	Integer get(int index) {
		return index;
	}
}

public class test {
	/*
	 * a to the power of b
	 */
	public static long power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (a == 0) {
			return 0;
		}
		long half = power(a, b / 2);
		return b % 2 == 0 ? half * half : half * half * a;
	}
	
	/*
	 * classical binary search
	 * 	注意while循环条件 和 left, right的更新
	 */
	public int binarySearch(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
	
	/*
	 * binary search on sorted 2D array I
	 * 	convert 2D array to 1D array
	 */
	public int[] search(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new int[] {-1, -1};
		}
		
		int rows = matrix.length; 
		int cols = matrix[0].length;
		int left = 0;
		int right = rows * cols - 1;
		while (left <= right) {
			// 1D coordinate
			int mid = left + (right - left) / 2;
			// convert to 2D coordinate
			int row = mid / cols;
			int col = mid % cols;
			if (matrix[row][col] == target) {
				return new int[] { row, col };
			} else if (matrix[row][col] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return new int[] {-1, -1};
	}
	
	/*
	 * first occurrence
	 */
	public static int firstOccur(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				right = mid;
			} else if (array[mid] < target) {
				left = mid;
			}
		}
		// post-processing
		if (array[left] == target) {
			return left;
		} else if (array[right] == target) {
			return right;
		} 
		return -1;
	}
	
	/*
	 * last occurrence
	 */
	public static int lastOccur(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		// post-processing
		if (array[right] == target) {
			return right;
		} else if (array[left] == target) {
			return left;
		}
		return -1;
	}
	
	/*
	 * closest number in sorted array
	 */
	public int closest(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (Math.abs(array[left] - target) < Math.abs(array[right] - target)) {
			return left;
		}
		return right;
	}
	
	/*
	 * k closest in sorted array
	 */
	public int[] kClosest(int[] array, int target, int k) {
		// sanity check
		if (array == null || array.length == 0) {
			return array;
		}
		if (k == 0) {
			return new int[0];
		}
		
		int left = largestSmallerEqual(array, target);
		int right = left + 1;
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			// left is smaller
			if (right >= array.length || left >= 0 && target - array[left] <= array[right] - target) {
				result[i] = array[left--];
			} 
			// right is smaller
			else {
				result[i] = array[right++];
			}
		}
		return result;
	}
	
	private int largestSmallerEqual(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (array[right] <= target) {
			return right;
		}
		if (array[left] <= target) {
			return left;
		}
		return -1;
	}
	
	/*
	 * search in unknown sized sorted array
	 */
	public int search(Dictionary dictionary, int target) {
		if (dictionary == null) return -1;
		int left = 0;
		int right = 1;
		while (dictionary.get(right) != null && dictionary.get(right) < target) {
			left = right;
			right = 2 * right;
		}
		return binarySearch(dictionary, target, left, right);
	}
	
	private int binarySearch(Dictionary dict, int target, int left, int right) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (dict.get(mid) == null || dict.get(mid) > target) {
				right = mid - 1;
			} else if (dict.get(mid) < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(power(2, 3));
	}
}








