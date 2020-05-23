class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] map = new int[26];
        for (char ch : p.toCharArray()) {
            map[ch - 'a']++;
        }
        
        int left = 0;   // include 
        int right = 0;  // include
        int count = p.length(); 
        while (right < s.length()) {
            char ch = s.charAt(right);
            
            // character exists in map
            if (map[ch - 'a']-- >= 1) {
                count--;
            }
            
            if (count == 0) res.add(left);
            
            if (right - left == p.length() - 1) {
                // only increase the count if character is in p
                // count >= 0 indicate it was original in p, cuz it won't go below 0
                if (map[s.charAt(left) - 'a']++ >= 0) {
                    count++;
                }
                left++;
            }
            
            right++;
        }
        return res;
    }
}