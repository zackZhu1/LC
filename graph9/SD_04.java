import java.util.*;


public class test {
	// 输出某个>1的整数分解的全部解 要求no duplicate
	
	public static List<List<Integer>> findAnswer(int num) {
		// 1. find all divisions
		List<Integer> divisions = getDivisions(num);
		// 2. dfs
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		dfs(num, divisions, 0, cur, res);
		return res;
	}
	
	private static List<Integer> getDivisions(int num) {
		List<Integer> divisions = new ArrayList<>();
		for (int i = 2; i <= num; i++) {
			if (num % i == 0) {
				divisions.add(i);
			}
		}
		return divisions;
	}
	
	private static void dfs(int num, List<Integer> divisions, int index, List<Integer> cur, List<List<Integer>> res) {
		if (num == 1) {
			res.add(new ArrayList<>(cur));
			return;
		}
		
		for (int i = index; i < divisions.size(); i++) {
			if (num % divisions.get(i) != 0) continue;
			cur.add(divisions.get(i));
			dfs(num / divisions.get(i), divisions, i, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int num = 12;
		List<List<Integer>> res = findAnswer(num);
		for (List<Integer> list : res) {
			for (Integer ele : list) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
}







