import java.util.*;


public class test {
	// 给出一个数n >= 1, 找出所有unique的和的组合
	// 给出一个数n >= 1, 找出所有unique的和的排列
	
	public static List<List<Integer>> findAnswer(int num) {
		// 1. find all divisions
		List<Integer> options = getOptions(num);
		// 2. dfs
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		dfs2(num, options, 0, cur, res);
		return res;
	}
	
	private static List<Integer> getOptions(int num) {
		List<Integer> options = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			options.add(i);
		}
		return options;
	}
	
	private static void dfs(int num, List<Integer> options, int index, List<Integer> cur, List<List<Integer>> res) {
		if (num == 0) {
			res.add(new ArrayList<>(cur));
			return;
		}
		
		for (int i = index; i < options.size(); i++) {
			if (num - options.get(i) < 0) continue;
			cur.add(options.get(i));
			dfs(num - options.get(i), options, i, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
	
	private static void dfs2(int num, List<Integer> options, int index, List<Integer> cur, List<List<Integer>> res) {
		if (num == 0) {
			res.add(new ArrayList<>(cur));
			return;
		}
		
		for (int i = 0; i < options.size(); i++) {
			if (num - options.get(i) < 0) continue;
			cur.add(options.get(i));
			dfs2(num - options.get(i), options, i, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int num = 3;
		List<List<Integer>> res = findAnswer(num);
		for (List<Integer> list : res) {
			for (Integer ele : list) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
}







