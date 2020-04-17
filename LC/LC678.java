// solution1: stack
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(') 
                leftStack.push(i);
            else if (ch == '*')
                starStack.push(i);
            else {
                if (!leftStack.isEmpty()) 
                    leftStack.pop();
                else if (!starStack.isEmpty()) 
                    starStack.pop();
                else 
                    return false;
            }
        }
        
        while (!leftStack.isEmpty()) {
            if (starStack.isEmpty()) return false;
            else if (leftStack.peek() < starStack.peek()) {
                leftStack.pop();
                starStack.pop();
            } else
                return false;
        }
        return true;
    }
}

// solution2: counter
class Solution {
    public boolean checkValidString(String s) {
        int leftBalance = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '(') || (s.charAt(i) == '*')) 
                leftBalance++;
            else
                leftBalance--;
            
            if (leftBalance < 0) return false;
        }
        
        if (leftBalance == 0) return true;
        
        int rightBalance = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if ((s.charAt(i) == ')') || (s.charAt(i) == '*')) 
                rightBalance++;
            else
                rightBalance--;
            
            if (rightBalance < 0) return false;
        }
        
        return true;
    }
}