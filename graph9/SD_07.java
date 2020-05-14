class Solution {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char center = ' ';
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0 && center == ' ') {
                center = entry.getKey();
            } else if (entry.getValue() % 2 != 0) {
                return res;
            }
            
            for (int j = 0; j < entry.getValue() / 2; j++) {
                sb.append(entry.getKey());
            }
        }
        
        // permutate
        dfs(center, 0, sb, res);
        return res;
    }
    
    private void dfs(char center, int index, StringBuilder sb, List<String> res) {
        if (index == sb.length()) {
            StringBuilder tmp = new StringBuilder(sb);
            if (center != ' ') {
                tmp.append(center);
                for (int i = tmp.length() - 2; i >= 0; i--) {
                    tmp.append(tmp.charAt(i));
                }
                res.add(tmp.toString());
            } else {
                for (int i = tmp.length() - 1; i >= 0; i--) {
                    tmp.append(tmp.charAt(i));
                }
                res.add(tmp.toString());
            }
            return;
        }
        
        Set<Character> set = new HashSet<>();
        for (int i = index; i < sb.length(); i++) {
            if (set.add(sb.charAt(i))) {
                swap(sb, i, index);
                dfs(center, index + 1, sb, res);
                swap(sb, i, index);
            }
        }
    }
    
    private void swap(StringBuilder sb, int i, int j) {
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }
}










