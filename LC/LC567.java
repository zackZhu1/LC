class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        for (char ch : s1.toCharArray()) {
            map[ch - 'a']++;
        }
    
        int left = 0;
        int right = 0;
        int count = s1.length();
        while (right < s2.length()) {
            char ch = s2.charAt(right);
            if (map[ch - 'a']-- >= 1) count--;
            if (count == 0) return true;
            
            if (right - left == s1.length() - 1) {
                if (map[s2.charAt(left) - 'a']++ >= 0) count++;
                left++;
            }
            
            right++;
        }
        return false;
    }
}