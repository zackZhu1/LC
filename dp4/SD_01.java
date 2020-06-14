/* solution1
1. state: dp[i] = maximum subarray sum from all subarrays ending at index i
2. induction rule:
    dp[i] = dp[i - 1] + array[i]  include i - 1
    	  = array[i]              not include i - 1
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                dp[i] = nums[i];
            else {
                if (dp[i - 1] < 0) 
                    dp[i] = nums[i];
                else
                    dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

// optimization: 
class Solution {
    public int maxSubArray(int[] nums) {
        int tmp = nums[0];
        int max = tmp;
        for (int i = 1; i < nums.length; i++) {
            if (tmp < 0) 
                tmp = nums[i];
            else
                tmp = tmp + nums[i];
            max = Math.max(max, tmp);
        }
        return max;
    }
}


// solution2: prefixSum
// e.g. [-2, -1] 
// 注意第一个特殊值的处理
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] += nums[i] + preSum[i - 1];
        }
        
        int[] dp = new int[n];
        dp[0] = 0; // special value
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], preSum[i - 1]);
        }
        
        int max = preSum[0]; // use preSum[0] 
        for (int j = 1; j < dp.length; j++) {
            max = Math.max(max, preSum[j] - dp[j]);
        }
        return max;
    }
}

// 上一种解法第一个特殊值不太好处理 所以加一个dummy element
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], preSum[i - 1]);
        }
        
        int max = Integer.MIN_VALUE;
        for (int j = 1; j < dp.length; j++) {
            max = Math.max(max, preSum[j] - dp[j]);
        }
        return max;
    }
}
