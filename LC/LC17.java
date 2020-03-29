// DFS
class Solution {
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.equals("")) return res;
        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb, res);
        return res;
    }
    
    private void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String cur = KEYS[digits.charAt(index) - '0'];
        for (int i = 0; i < cur.length(); i++) {
            sb.append(cur.charAt(i));
            dfs(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}