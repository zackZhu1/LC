// House Robber 
// https://www.youtube.com/watch?v=H75Qp7ExCwo

// DP
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = Math.max( (i > 1 ? dp[i - 2] : 0) + nums[i],
                              (i > 0 ? dp[i - 1] : 0));
        }
        return dp[dp.length - 1];
    }
}

// O(1)
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp2 = 0;
        int dp1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int dp = Math.max(dp2 + nums[i], dp1);
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }
}