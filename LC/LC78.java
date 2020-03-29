class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>(); 
        dfs(nums, 0, cur, res);
        return res;
    }
    
    private void dfs(int[] nums, int level, List<Integer> cur, List<List<Integer>> res) {
        if (level == nums.length) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        // not add
        dfs(nums, level + 1, cur, res);
        // add
        cur.add(nums[level]);
        dfs(nums, level + 1, cur, res);
        cur.remove(cur.size() - 1);
    }
}