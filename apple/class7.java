import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

public class test {
	/*
	 * top k frequent words
	 */
	public String[] topKFrequent(String[] combo, int k) {
		if (combo.length == 0) {
			return new String[0];
		}
		
		Map<String, Integer> freqMap = getFreqMap(combo);
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry);
			} else if (entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}
		}
		return freqArray(minHeap);
	}
	
	private Map<String, Integer> getFreqMap(String[] combo) {
		Map<String, Integer> freqMap = new HashMap<>();
		for (String s : combo) {
			Integer freq = freqMap.get(s);
			if (freq == null) {
				freqMap.put(s, 1);
			} else {
				freqMap.put(s, freq + 1);
			}
		}
		return freqMap;
	}
	
	private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
		String[] result = new String[minHeap.size()];
		for (int i = minHeap.size() - 1; i >= 0; i--) {
			result[i] = minHeap.poll().getKey();
		}
		return result;
	}
	
	/*
	 * missing number 
	 */
	public int findMissingNumber(int[] array) {
		int n = array.length + 1;
		int xor = 0;
		for (int i = 1; i <= n; i++) {
			xor ^= i;
		}
		
		for (int num : array) {
			xor ^= num;
		}
		return xor;
	}
	
	/*
	 * remove certain characters
	 */
	public String remove(String input, String t) {
		char[] array = input.toCharArray();
		Set<Character> set = buildSet(t);
		int slow = 0;
		for (int fast = 0; fast < input.length(); fast++) {
			if (!set.contains(array[fast])) {
				array[slow++] = array[fast];
			}
		}
		return new String(array, 0, slow);
	}
	
	private Set<Character> buildSet(String t) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}
		return set;
	}
	
	/*
	 * remove spaces
	 */
	public String removeSpaces(String input) {
		char[] array = input.toCharArray();
		int end = 0; // exclude
		for (int i = 0; i < array.length; i++) {
			if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
				continue;
			}
			array[end++] = array[i];
		}
		// post-processing
		if (end > 0 && array[end - 1] == ' ') {
			return new String(array, 0, end - 1);
		}
		return new String(array, 0, end);
	}
	
	/*
	 * remove adjacent repeated characters
	 */
	public static String deDup(String input) {
		if (input == null) return null;
		char[] array = input.toCharArray();
		int end = 0; // exclude
		for (int i = 0; i < array.length; i++) {
			if (i == 0 || array[i] != array[end - 1]) {
				array[end++] = array[i];
			}
		}
		return new String(array, 0, end);
	}
	
	/*
	 * remove adjacent repeated characters repeatedly
	 */
	public static String deDup2(String input) {
		char[] array = input.toCharArray();
		int end = 0; // include, top of the stack
		for (int i = 1; i < array.length; i++) {
			if (end == -1 || array[i] != array[end]) {
				array[++end] = array[i];
			} else {
				end--; // stack pop
				while (i + 1 < array.length && array[i] == array[i + 1]) {
					i++;
				}
			}
		}
		return new String(array, 0, end + 1);
	}
	
	
	public static void main(String[] args) {
		System.out.println(deDup("ab_cabbbbbbb_cccc"));
		System.out.println(deDup2("abbbbbaaaaaaaz"));
	}
}








