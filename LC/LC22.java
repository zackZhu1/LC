class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        
        char[] ch = new char[2 * n];
        dfs(n, n, 0, ch, res);
        return res;
    }
    
    private void dfs(int left, int right, int level, char[] ch, List<String> res) {
        if (level == ch.length) { // or if (left == 0 && right == 0)
            String str = new String(ch);
            res.add(str);
            return;
        }
        if (left > 0) {
            ch[level] = '(';
            dfs(left - 1, right, level + 1, ch, res);
        } 
        if (left < right) {
            ch[level] = ')';
            dfs(left, right - 1, level + 1, ch, res);
        }
    }
}