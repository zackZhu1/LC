class Solution {
    public boolean canPermutePalindrome(String s) {
        char[] map = new char[256];
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] <= 0) {
                map[s.charAt(i)]++;
            } else {
                map[s.charAt(i)]--;
            }
        }
        
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) count++;
        }
        return count <= 1;
    }
}