// LC 77
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(n, k, 0, cur, res);
        return res;
    }
    
    private void dfs(int n, int k, int num, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (num == n) return;
        
        cur.add(num + 1);
        dfs(n, k, num + 1, cur, res);
        cur.remove(cur.size() - 1);
        
        dfs(n, k, num + 1, cur, res);
    }
}

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(n, k, 0, cur, res);
        return res;
    }
    
    private void dfs(int n, int k, int num, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = num; i < n; i++) {
            cur.add(i + 1);
            dfs(n, k, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}



import java.util.*;


public class test {
    // assume no duplicates
    
    public static List<List<Integer>> subsetII(int[] nums, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs2(nums, k, 0, cur, res);
        return res;
    }
    
    // solution1
    private static void dfs(int[] nums, int k, int index, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (index == nums.length) return;
        
        cur.add(nums[index]);
        dfs(nums, k, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        
        dfs(nums, k, index + 1, cur, res);
    }
    
    // solution2
    public static void dfs2(int[] nums, int k, int index, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs2(nums, k, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> res = subsetII(nums, 2);
        for (List<Integer> list : res) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}







