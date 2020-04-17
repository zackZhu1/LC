// construct binary tree from string

// preorder with special character (boundary) to reconstruct the binary tree
class Solution {
    public TreeNode str2tree(String s) {
        if (s.equals("")) return null;
        return helper(s);
    }
    
    private int index = 0;
    
    private TreeNode helper(String s) {
        TreeNode root = new TreeNode(getIntValue(s));
        
        // find possible left child
        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            root.left = helper(s);
        }
        // find possible right child
        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            root.right = helper(s);
        }
        
        // ')' or index == s.length()
        index++;
        return root;
    }
    
    private int getIntValue(String s) {
        StringBuilder sb = new StringBuilder();
        while (index < s.length()) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')')
                break;
            sb.append(s.charAt(index));
            index++;
        }
        int val = Integer.valueOf(sb.toString());
        return val;
    }
}




// follow-up: general n-ary tree
class Node {
    String value;
    List<Node> children;
    public Node(String value) {
        this.value = new String(value);
        children = new ArrayList<>();
    }
}


public class test {
    private static int index = 0;
    public static Node reconstruct(String[] preorder) {
        // invariant: current index points to '('
        index++;
        
        Node root = new Node(preorder[index]);
        index++;
        while (index < preorder.length && !preorder[index].equals(")")) {
            root.children.add(reconstruct(preorder));
        }
        
        index++;
        return root;
    }
    
    public static void preOrder(Node node) {
        if (node == null) return;
        
        System.out.print(node.value + " ");
        for (Node child : node.children) {
            preOrder(child);
        }
    }
    
    public static void main(String[] args) {
        // ( a ( b ( c ) ( d ) ( f ) ) ( e ) )
        String[] preorder = {"(", "a", "(", "b", "(", "c", ")", "(", "d", ")", "(", "f", ")", ")", "(", "e", ")", ")"};
        Node root = reconstruct(preorder);
        preOrder(root);
    }
}
