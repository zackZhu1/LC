class StockSpanner {
    private Stack<int[]> stack;
    private int index;
    
    public StockSpanner() {
        stack = new Stack<>();
        index = -1;
    }
    
    public int next(int price) {
        index += 1;
        while (!stack.isEmpty() && price >= stack.peek()[1]) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            stack.push(new int[] { index, price });
            return index + 1;
        } else {
            int result = stack.peek()[0];
            stack.push(new int[] { index, price });
            return index - result;
        }
        
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */