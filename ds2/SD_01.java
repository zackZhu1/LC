// k pointers 谁小移谁
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(11, new Comparator<>() {
            @Override
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        
        for (ListNode node : lists) {
            if (node != null)
                minHeap.offer(node);
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            if (minNode.next != null)
                minHeap.offer(minNode.next);
            
            cur.next = minNode;
            cur = cur.next;
        }
        return dummy.next;
    }
}