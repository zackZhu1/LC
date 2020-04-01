import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

public class test {
	/*
	 * find subset
	 */
	// solution1
	public List<String> subSets(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) return result;
		
		char[] arraySet = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		helper(arraySet, sb, 0, result);
		return result;
	}
	
	private void helper(char[] set, StringBuilder sb, int index, List<String> result) {
		if (index == set.length) {
			result.add(sb.toString());
			return;
		}
		// not pick
		helper(set, sb, index + 1, result);
		// pick
		sb.append(set[index]);
		helper(set, sb, index + 1, result);
		sb.deleteCharAt(sb.length() - 1);
	}
	
	// solution2:
	public List<String> subSets2(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) return result;
		
		char[] arraySet = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		helper2(arraySet, sb, 0, result);
		return result;
	}
	
	private void helper2(char[] set, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		for (int i = index; i < set.length; i++) {
			sb.append(set[i]);
			helper2(set, sb, i + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	/*
	 * all valid permutations of parentheses
	 */
	public List<String> validParentheses(int k) {
		List<String> result = new ArrayList<>();
		char[] cur = new char[k * 2];
		helper3(cur, k, k, 0, result);
		return result;
	}
	
	private void helper3(char[] cur, int left, int right, int index, List<String> result) {
		if (left == 0 && right == 0) {
			result.add(new String(cur));
			return;
		}
		if (left > 0) {
			cur[index] = '(';
			helper3(cur, left - 1, right, index + 1, result);
		}
		if (right > left) {
			cur[index] = ')';
			helper3(cur, left, right - 1, index + 1, result);
		}
	}
	
	/*
	 * combinations of coins
	 */
	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		helper4(target, coins, 0, cur, result);
		return result;
	}
	
	private void helper4(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
		if (index == coins.length - 1) {
			if (target % coins[coins.length - 1] == 0) {
				cur.add(target / coins[coins.length - 1]);
				result.add(new ArrayList<>(cur));
				cur.remove(cur.size() - 1);
			}
			return;
		}
		
		int max = target / coins[index];
		for (int i = 0; i <= max; i++) {
			cur.add(i);
			helper4(target - i * coins[i], coins, index + 1, cur, result);
			cur.remove(cur.size() - 1);
		}
	}
	
	/*
	 * all permutations
	 */
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) return result;
		char[] array = set.toCharArray();
		helper5(array, 0, result);
		return result;
	}
	
	private void helper5(char[] array, int index, List<String> result) {
		if (index == array.length) {
			result.add(new String(array));
			return;
		}
		
		for (int i = index; i < array.length; i++) {
			swap(array, index, i);
			helper5(array, index + 1, result);
			swap(array, index, i);
		}
	}
	
	private void swap(char[] array, int i, int j) {
		char tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public static void main(String[] args) {
		
	}
}








