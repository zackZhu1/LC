// LC 589, 590, 429
class Node {
    int val;
    List<Node> children = new ArrayList<>();
    Node(int val) {
        this.val = val;
    }
}

public class test {
    /*
     * postorder traversal
     */
    // solution1: recursive
    public static List<Integer> postOrder(Node root) {
        List<Integer> order = new ArrayList<>();
        dfs(root, order);
        return order;
    }
    
    private static void dfs(Node node, List<Integer> order) {
        if (node == null) return;
        for (Node child : node.children) {
            dfs(child, order);
        }
        order.add(node.val);
    }
    
    // solution2: iterative
    public static List<Integer> postOrder2(Node root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) return order;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            order.add(root.val);
            for (Node child : root.children) {
                stack.push(child);
            }
        }
        Collections.reverse(order);
        return order;
    }
    
    /*
     * preorder traversal
     */
    public static List<Integer> preOrder(Node root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) return order;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            order.add(root.val);
            Collections.reverse(root.children);
            for (Node child : root.children) {
                stack.push(child);
            }
        }   
        return order;
    }
    
    public static Node createTestCase() {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        root.children.add(node2); root.children.add(node3); root.children.add(node4);
        node2.children.add(node5); node2.children.add(node6);
        return root;
    }
    
    /*
     * inorder traversal
     */
    // solution1: BFS
    public static List<List<Integer>> inOrder(Node root) {
        List<List<Integer>> order = new ArrayList<>();
        if (root == null) return order;
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                for (Node child : cur.children) {
                    queue.offer(child);
                }
            }
            order.add(level);
        }
        return order;   
    }
    
    // solution1: BFS without queue
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> order = new ArrayList<>();
            if (root == null) return order;
            
            List<Node> curLevel = new ArrayList<>();
            curLevel.add(root);
            while (!curLevel.isEmpty()) {
                List<Node> nextLevel = new ArrayList<>();
                List<Integer> cur = new ArrayList<>();
                for (Node node : curLevel) {
                    cur.add(node.val);
                    for (Node child : node.children) {
                        nextLevel.add(child);
                    }
                }
                order.add(cur);
                // update
                curLevel = nextLevel;
            }
            return order;
        }
    }
    
    public static void main(String[] args) {
        List<Integer> res = preOrder(createTestCase());
        for (Integer value : res) {
            System.out.print(value + " ");
        }
    }
}







