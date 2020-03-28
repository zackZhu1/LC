class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty() || minStack.peekFirst() >= x) {
            minStack.offerFirst(x);
        }       
        stack.offerFirst(x);
    }
    
    public void pop() {
        if (stack.isEmpty()) return;
        Integer tmp = stack.pollFirst();
        if (minStack.peekFirst().equals(tmp))
            minStack.pollFirst();
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minStack.peekFirst();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */