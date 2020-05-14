class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, res);
        return res;
    }
    
    private void dfs(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        cur.add(nums[index]);
        dfs(nums, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        
        dfs(nums, index + 1, cur, res);
    }
}



class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, res);
        return res;
    }
    
    private void dfs(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}