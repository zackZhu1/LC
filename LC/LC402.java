// https://www.youtube.com/watch?v=3QJzHqNAEXs
class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<Character>();
        int n = num.length();
        
        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            if (!stack.isEmpty() || ch != '0') 
                stack.push(ch);
        }
        
        // corner cases
        // 1. 123
        while (!stack.isEmpty() && k > 0) {   
            stack.pop();
            k--;
        }
        // 2. 1001
        if (stack.isEmpty())
            return "0";
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
}