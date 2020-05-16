// Maximum Depth of N-ary Tree
// 1. 从上往下传值
class Solution {
    public int maxDepth(Node root) {
        int[] max = new int[] {0};
        dfs(root, max, 1);
        return max[0];
    }
    
    private void dfs(Node root, int[] max, int curLevel) {
        if (root == null) return;
        if (root.children.isEmpty()) {
            max[0] = Math.max(max[0], curLevel);   
            return;
        }
        
        for (Node child : root.children) {
            dfs(child, max, curLevel + 1);
        }
    }
}

// 2. pure recursion
class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;
        
        int curDepth = 0;
        for (Node child : root.children) {
            curDepth = Math.max(maxDepth(child), curDepth);
        }
        return curDepth + 1;
    }
}

