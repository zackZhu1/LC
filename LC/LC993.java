// solution1: BFS

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
         
        while (!queue.isEmpty()) {            // for each level
            int size = queue.size(); 

            TreeNode first = null;
            TreeNode second = null;
            for (int i = 0; i < size; i++) {  // for each node
                TreeNode cur = queue.poll(); // expand
                int count = 0;
                
                // generate
                if (cur.left != null) {
                    TreeNode left = cur.left;
                    queue.offer(left);
                    
                    if (left.val == x || left.val == y) {
                        count++;
                        if (first != null) {
                            second = cur; // return true;
                        }
                        else {
                            first = left;
                        }
                    }
                } 
                
                if (cur.right != null) {
                    TreeNode right = cur.right;
                    queue.offer(right);
                    
                    if (right.val == x || right.val == y) {
                        count++;
                        if (first == null) {
                            first = right;
                        } else {
                            second = right;
                        }
                    }
                }
                
                // two nodes have same parent
                if (count == 2) return false;
                
                // two nodes have different parents
                if (second != null) return true;
            }
        }
        return false;
    }
}


// DFS
class Solution {
    private TreeNode p1 = null;
    private TreeNode p2 = null;
    private int l1 = -1;
    private int l2 = -1;
   
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, null, 0);
        if (p1 == null || p2 == null) return false;
        if (p1 == p2) return false;
        return l1 == l2;
    }
    
    private void dfs(TreeNode node, int x, int y, TreeNode parent, int level) {
        if (node == null) return;
        if (node.val == x || node.val == y) {
            if (p1 == null) {
                p1 = parent;
                l1 = level;
            }
            else if (p2 == null) {
                p2 = parent;
                l2 = level;
            }
            return;
        }
        
        dfs(node.left, x, y, node, level + 1);
        dfs(node.right, x, y, node, level + 1);
    }
}