import java.util.*;

public class test {
	
	// dp[i] = maximum subarray sum ending at index i
	// 以index i 分解: exactly k elements OR at least k elements
	public static int maxSumWithK(int nums[], int k) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (dp[i - 1] < 0) dp[i] = nums[i];
			else dp[i] = dp[i - 1] + nums[i];
		}
		
		int windowSum = 0;
		for (int i = 0; i < k; i++) windowSum += nums[i];
		
		int max = windowSum;
		for (int i = k; i < nums.length; i++) {
			windowSum = windowSum + nums[i] - nums[i - k];
			max = Math.max(max, windowSum);
			max = Math.max(max, windowSum + dp[i - k]);
		}
		
		return max;
	}

	// solution2: prefixSum
	public static int maxSumWithK2(int nums[], int k) {
		int n = nums.length;
		int[] preSum = new int[n];
		preSum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			preSum[i] = preSum[i - 1] + nums[i];
		}
		int[] dp = new int[n]; // smallest prefixSum before current index 
		dp[0] = preSum[0];
		for (int i = 1; i < n; i++) {
			dp[i] = preSum[i] < dp[i - 1] ? preSum[i] : dp[i - 1];
		}
		
		dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], preSum[i - 1]);
        }
        
		int max = Integer.MIN_VALUE;
		for (int j = k; j < n; j++) {
			max = Math.max(max, preSum[j] - dp[j - k + 1]);
		}
		return max;
	}


	public static void main(String[] args) {
		int[] nums1 = {-4, -2, 1, -3}; int k1 = 2;
		int[] nums2 = {1, 1, 1, 1, 1, 1}; int k2 = 2;
		System.out.println(maxSumWithK(nums1, k1));
		System.out.println(maxSumWithK(nums2, k2));
	}
}


