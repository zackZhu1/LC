class Solution {
    class Cell {
        int row;
        int col;
        int val;
        public Cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    
    class MyComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell c1, Cell c2) {
            return Integer.compare(c1.val, c2.val);
        }
    }
    
    public int[] smallestRange(List<List<Integer>> nums) {
        int minRange = Integer.MAX_VALUE;
        int[] range = new int[2];
        int max = Integer.MIN_VALUE;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(new MyComparator());
        for (int i = 0; i < nums.size(); i++) {
            minHeap.offer(new Cell(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }
        
        while (minHeap.size() == nums.size()) {
            Cell cur = minHeap.poll();
            // update min
            if (max - cur.val < minRange) {
                minRange = max - cur.val;
                range[0] = cur.val;
                range[1] = max;
            }
            
            // update max during insertion
            if (cur.col + 1 < nums.get(cur.row).size()) {
                int newEle = nums.get(cur.row).get(cur.col + 1);
                minHeap.offer(new Cell(cur.row, cur.col + 1, newEle));
                max = Math.max(max, newEle);
            }
        }
        return range;
    }
}