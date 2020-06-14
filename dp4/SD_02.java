// solution1:
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        // step1: find maximum subarray
        int[] dp = new int[A.length];
        int max = A[0];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (dp[i - 1] < 0)
                dp[i] = A[i];
            else 
                dp[i] = dp[i - 1] + A[i];
            max = Math.max(dp[i], max);
        }
        if (A.length < 3) return max;
        
        // step2: find minimum subarray from [1, n - 1]
        dp = new int[A.length];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < dp.length - 1; i++) {
            if (dp[i - 1] > 0)
                dp[i] = A[i];
            else
                dp[i] = dp[i - 1] + A[i];
            min = Math.min(dp[i], min);
        }
        
        int sum = 0;
        for (int ele : A)
            sum += ele;
        
        return Math.max(max, sum - min);
    }
}

// solution2:
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        // step1: find maximum subarray from left to right
        int[] dp = new int[A.length];
        int max = A[0];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (dp[i - 1] < 0)
                dp[i] = A[i];
            else 
                dp[i] = dp[i - 1] + A[i];
            max = Math.max(dp[i], max);
        }
            
        int[] leftMax = new int[A.length];
        int[] prefixSum = new int[A.length];
        leftMax[0] = A[0];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
            leftMax[i] = Math.max(leftMax[i - 1], prefixSum[i]);
        }
        
        int[] rightMax = new int[A.length];
        int[] postfixSum = new int[A.length];
        rightMax[rightMax.length - 1] = A[A.length - 1];
        postfixSum[postfixSum.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            postfixSum[i] = postfixSum[i + 1] + A[i];
            rightMax[i] = Math.max(rightMax[i + 1], postfixSum[i]);
        }
        
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            max2 = Math.max(max2, leftMax[i] + rightMax[i + 1]);
        }
        
        return Math.max(max, max2);
    }
}
// optimization:
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        int max = A[0];
        dp[0] = A[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < 0)
                dp[i] = A[i];
            else 
                dp[i] = dp[i - 1] + A[i];
            max = Math.max(dp[i], max);
        }
            
        int[] leftMax = new int[n];
        leftMax[0] = A[0];
        int prefixSum = A[0];
        for (int i = 1; i < n; i++) {
            prefixSum = prefixSum + A[i];
            leftMax[i] = Math.max(leftMax[i - 1], prefixSum);
        }
        
        int[] rightMax = new int[n];
        rightMax[n - 1] = A[n - 1];
        int postfixSum = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            postfixSum = postfixSum + A[i];
            rightMax[i] = Math.max(rightMax[i + 1], postfixSum);
        }
        
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            max2 = Math.max(max2, leftMax[i] + rightMax[i + 1]);
        }
        
        return Math.max(max, max2);
    }
}