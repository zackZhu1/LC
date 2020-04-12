// solution1: O(nlogn)
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode mid = findMiddleNode(head);
        TreeNode node = new TreeNode(mid.val);
        
        // base case
        if (mid == head) return node;
        
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }
    
    // find middle node in the linked list
    private ListNode findMiddleNode(ListNode head) {
        ListNode prev = null; // the pointer used to disconnect the left half from the mid node
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) prev.next = null;
        return slow;
    }
}



// solution2: O(n)
class Solution {
    private ListNode head;
    
    public TreeNode sortedListToBST(ListNode head) {
        int length = findLength(head);
        this.head = head;
        return helper(0, length - 1);
    }
    
    private int findLength(ListNode head) {
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length += 1;
        }
        return length;
    }
    
    private TreeNode helper(int left, int right) {
        // base case
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode leftNode = helper(left, mid - 1);
        
        TreeNode curNode = new TreeNode(head.val);
        curNode.left = leftNode;
        head = head.next; // inorder traversal
        
        curNode.right = helper(mid + 1, right);
        return curNode;
    }
}