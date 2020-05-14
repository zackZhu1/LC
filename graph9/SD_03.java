import java.util.*;


public class test {
	// assume no duplicates
	
	public static List<List<Integer>> subsetIII(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		dfs2(nums, 0, cur, res);
		return res;
	}
	
	public static void dfs2(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
		res.add(new ArrayList<>(cur));
		
		for (int i = index; i < nums.length; i++) {
			cur.add(nums[i]);
			dfs2(nums, i + 2, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5};
		List<List<Integer>> res = subsetIII(nums);
		for (List<Integer> list : res) {
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}







