// Serialize and deserialize binary tree 

// solution1: preorder + special character(delimiter, null node) which can uniquely reconstruct a binary tree
public class Codec {
    private static final String SPLITER = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    private void buildString(TreeNode node, StringBuilder sb) {
        // base case
        if (node == null) {
            sb.append(NN).append(SPLITER);
            return;
        }
        // induction rule
        sb.append(node.val).append(SPLITER);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(SPLITER);
        Deque<String> preorder = new ArrayDeque<>();
        for (String str : arr) {
            preorder.offerLast(str);
        }
        return reconstruct(preorder);
    }
    
    private TreeNode reconstruct(Deque<String> preorder) {
        String cur = preorder.pollFirst();
        // base case
        if (cur.equals(NN)) return null;
        
        // induction rule
        TreeNode root = new TreeNode(Integer.valueOf(cur));
        root.left = reconstruct(preorder);
        root.right = reconstruct(preorder);
        return root;
    }
}

// solution2: level-order + special character
// level-order + special character (delimiter, null node)
public class Codec {
    private static final String SPLITER = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) sb.append(NN).append(SPLITER);
            else {
                sb.append(cur.val).append(SPLITER);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(SPLITER);
        if (arr[0].equals(NN)) return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        queue.offer(root);
        
		for (int i = 1; i < arr.length; i++) {
            TreeNode parent = queue.poll();
            if (!arr[i].equals(NN)) {
                parent.left = new TreeNode(Integer.valueOf(arr[i]));
                queue.offer(parent.left);
            }
            i++;
            if (!arr[i].equals(NN)) {
                parent.right = new TreeNode(Integer.valueOf(arr[i]));
                queue.offer(parent.right);
            }
        }
        return root;
    }
    
}
