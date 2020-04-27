// Binary Tree Zigzag Level Order Traversal
// solution1:
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (level % 2 == 1) {
                Collections.reverse(list);
            }
            result.add(list);
            level++;
        }
        return result;
    }
}

// solution2:
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        boolean leftToRight = true;
        
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (leftToRight) {
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null) deque.addLast(cur.left);
                    if (cur.right != null) deque.addLast(cur.right);
                } else {
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null) deque.addFirst(cur.right);
                    if (cur.left != null) deque.addFirst(cur.left);
                }
            }
            leftToRight = leftToRight ? false : true;
            result.add(list);
        }
        return result;
    }
}
























