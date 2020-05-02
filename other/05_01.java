import java.util.*;

public class test {
	// Given an int array, find the maximum sum of size k subarray
	// 代码可以优化 只需要右边指针即可
	public static int findMaximumSubarray(int[] array, int k) {
		// assume k <= array.length
		int globalMax = 0;
		for (int i = 0; i < k; i++) {
			globalMax += array[i];
		}
		int curSum = globalMax;
		int right = k;
		for (int left = 1; left <= array.length - k; left++) {
			curSum = curSum - array[left - 1] + array[right];
			globalMax = Math.max(globalMax, curSum);
			right++;
		}
		return globalMax;
	}
	
	public static int findMaximumSubarray2(int[] array, int k) {
		int globalMax = 0;
		int curSum = 0;
		for (int i = 0; i < array.length; i++) {
			curSum = curSum + array[i];
			if (i >= k) {
				curSum = curSum - array[i - k];
				globalMax = Math.max(globalMax, curSum);
			}
		}
		return globalMax;
	}
	
	// Find the maximum sum of 2 size k subarrays not overlap with each other
	public static int findMaximumSubarray3(int[] array, int k) {
		int[] dp = new int[array.length - k + 1];
		int curSum = 0;
		for (int i = 0; i < array.length; i++) {
			curSum = curSum + array[i];
			if (i == k - 1) dp[0] = curSum;
			if (i >= k) {
				curSum = curSum - array[i - k];
				dp[i - k + 1] = curSum;
			}
		}
		
		int[] left = new int[dp.length];
		for (int i = 0; i < left.length; i++) {
			if (i == 0) left[i] = dp[i];
			else left[i] = Math.max(left[i - 1], dp[i]);
		}
		
		int globalMax = 0;
		for (int i = k; i < left.length; i++) {
			int pairSum = dp[i] + left[i - k];
			globalMax = Math.max(pairSum, globalMax);
		}
		
		return globalMax;
	}
	
	public static void main(String[] args) {
		int[] array = { 1, -2, 3, 4, -3, 2};
		int k = 2;
		System.out.println(findMaximumSubarray3(array, k));
	}
}



// https://www.youtube.com/watch?v=dGS4O0110qA
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int n = len - k + 1;
        
        int[] dp = new int[n];
        int curSum = 0;
        for (int i = 0; i < len; i++) {
            curSum += nums[i];
            if (i == k - 1) dp[0] = curSum;
            if (i >= k) {
                curSum -= nums[i - k];
                dp[i - k + 1] = curSum;
            }
        }
        
        int[] left = new int[n];
        int[] right = new int[n];
        int max = nums[0];
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > dp[maxIndex]) maxIndex = i;
            left[i] = maxIndex;
        }
        
        max = nums[n - 1];
        maxIndex = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] >= dp[maxIndex]) maxIndex = i;
            right[i] = maxIndex;
        }
        
        int[] res = new int[3];
        Arrays.fill(res, -1);
        for (int i = k; i < n - k; i++) {
            if (res[0] == -1 || dp[res[0]] + dp[res[1]] + dp[res[2]] < dp[left[i - k]] + dp[i] + dp[right[i + k]]) {
                res[0] = left[i - k];
                res[1] = i;
                res[2] = right[i + k];
            }
        }
        return res;
    }
}


















