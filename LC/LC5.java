// https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();
        int longest = 1;
        int startIndex = 0;
        int endIndex = 0;
        boolean[][] dp = new boolean[len][len];
        
        // 斜对角线开始fill 
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < dp.length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                longest = 2;
                startIndex = i;
                endIndex = i + 1;
            }
        }
        
        for (int i = 3; i <= len; i++) {
            for (int start = 0; start + i - 1 < len; start++) {
                int end = start + i - 1;
                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    if (longest < end - start + 1) {
                        longest = end - start + 1;
                        startIndex = start;
                        endIndex = end;
                    }
                }
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }
}