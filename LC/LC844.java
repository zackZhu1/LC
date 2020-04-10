// solution1: stack
// space: O(n)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        String s1 = process(S);
        String s2 = process(T);
        return s1.equals(s2);
    }
    
    private String process(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '#') {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(ch);
            }
        }
        // StringBuilder sb = new StringBuilder();
        // while (!stack.isEmpty()) {
        //     sb.append(stack.pop());
        // }
        // return sb.toString();
        return String.valueOf(stack);
    }
}

// solution2: two pointer
// space: O(1)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) { // find the next char in S 
                if (S.charAt(i) == '#') { skipS++; i--; }
                else if (skipS > 0) { skipS--; i--; }
                else break;
            }
            while (j >= 0) { // find the next char in T
                if (T.charAt(j) == '#') { skipT++; j--; }
                else if (skipT > 0) { skipT--; j--; }
                else break;
            }
            
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
            
            System.out.println(i + " " + j);
            //if ((i >= 0) != (j >= 0)) return false;
            if ((i >= 0 && j < 0) || (j >= 0 && i < 0)) return false;
            
            i--; 
            j--;
        }
        return true;
    }
}