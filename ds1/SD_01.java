import java.util.*;

// capacity limited MinHeap
public class test {
	public class MinHeap {
		private int[] array;
		private int size;
		
		public MinHeap(int[] array) throws IllegalAccessException {
			if (array == null || array.length == 0) {
				throw new IllegalAccessException();
			}
			this.array = array;
			this.size = array.length;
			heapify();
		}
		
		public MinHeap(int capacity) throws IllegalAccessException {
			if (capacity <= 0) 
				throw new IllegalAccessException();
			this.array = new int[capacity];
			this.size = 0;
		}
		
		private void heapify() {
			for (int i = size / 2 - 1; i >= 0; i--) {
				percolateDown(i);
			}
		}
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public boolean isFull() {
			return size == array.length;
		}
		
		public int peek() {
			if (size == 0) return -1;
			return array[0];
		}
		
		public int poll() {
			if (size == 0) throw new NoSuchElementException();
			int result = array[0];
			array[0] = array[size - 1]; // move last one to the first one
			size--;
			percolateDown(0);
			return result;
		}
		
		public void offer(int ele) {
			if (size == array.length) {
				int[] newArray = new int[(int) (array.length * 1.5)];
				copy(array, newArray);
				array = newArray;
			}
			array[size] = ele;
			size++;
			percolateUp(size - 1);
		}
		
		// return the original value
		public int update(int index, int ele) {
			if (index < 0 || index > size - 1) {
				throw new ArrayIndexOutOfBoundsException("invalid index range");
			}
			int prev_val = array[index];
			array[index] = ele;
			if (prev_val > ele) 
				percolateUp(index);
			else 
				percolateDown(index);
			return prev_val;
		}
		
		private void copy(int[] array, int[] newArray) {
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
		}
		
		private void percolateUp(int index) {
			while (index > 0) {
				int parentIndex = (index - 1) / 2;
				if (array[parentIndex] > array[index]) {
					swap(array, parentIndex, index);
				} else {
					break;
				}
				
				// update
				index = parentIndex;
			}
		}
		
		private void percolateDown(int index) {
			while (index <= size / 2 - 1) { // when it has at least one child
				int leftIndex = index * 2 + 1;
				int rightIndex = index * 2 + 2;
				int swapCandidate = leftIndex;
				if (rightIndex <= size - 1 && array[leftIndex] > array[rightIndex]) 
					swapCandidate = rightIndex;
				
				if (array[index] > array[swapCandidate]) {
					swap(array, index, swapCandidate);
				} else {
					break;
				}
				
				// update
				index = swapCandidate;
			}
		}
		
		private void swap(int[] array, int i, int j) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}
	
	public static void main(String[] args) {
	
	}
}




