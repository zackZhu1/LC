/*
 Implement Queue by two Stacks
*/
import java.util.*;

public class QueueByTwoStack {
	private LinkedList<Integer> in;
	private LinkedList<Integer> out;
	
	public QueueByTwoStack() {
		in = new LinkedList<>();
		out = new LinkedList<>();
	}
	
	public void offer(int value) {
		in.offerFirst(value);
	}
	
	public Integer poll() {
		move();
		return out.isEmpty() ? null : out.pollFirst();
	}
	
	public Integer peek() {
		move();
		return out.isEmpty() ? null : out.peekFirst();
	}
	
	private void move() {
		if (out.isEmpty()) {
			while (!in.isEmpty()) {
				out.offerFirst(in.pollFirst());
			}
		}
	}
}


/*
 Min Stack
*/
import java.util.*;

public class StackWithMin {
	private Deque<Integer> stack;
	private Deque<Integer> minStack;
	
	public StackWithMin() {
		stack = new LinkedList<>();
		minStack = new LinkedList<>();
	}
	
	public Integer min() {
		if (minStack.isEmpty()) {
			return null;
		}
		return minStack.peekFirst();
	}
	
	public void push(int value) {
		stack.offerFirst(value);
		if (minStack.isEmpty() || minStack.peekFirst() >= value) {
			minStack.offerFirst(value);
		}
	}
	
	public Integer pop() {
		if (stack.isEmpty()) {
			return null;
		}
		Integer result = stack.pollFirst();
		if (minStack.peekFirst().equals(result)) {
			minStack.pollFirst();
		}
		return result;
	}
	
	public Integer top() {
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peekFirst();
	}
}



import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

class ListNode {
	int value;
	ListNode next;
	ListNode(int value) {
		this.value = value;
		this.next = null;
	}
}

public class test {
	/*
	 * reverse LinkedList
	 */
	public ListNode reverseLinkedList(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	
	public ListNode reverseLinkedList2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode newHead = reverseLinkedList2(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	/*
	 * Find middle node of LinkedList
	 */
	public ListNode findMiddle(ListNode head) {
		if (head == null) return null;
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/*
	 * check if LinkedList has cycle
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) return true;
		}
		return false;
	}
	
	/*
	 * insert in a sorted LinkedList
	 */
	public ListNode insert(ListNode head, int value) {
		ListNode newNode = new ListNode(value);
		if (head == null || head.value >= value) {
			newNode.next = head;
			return newNode;
		}
		ListNode prev = head;
		while (prev.next != null && prev.next.value < value) {
			prev = prev.next;
		}
		newNode.next = prev.next;
		prev.next = newNode;
		return head;
	}
	
	/*
	 * merge two sorted LinkedList
	 */
	public ListNode merge(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (one != null && two != null) {
			if (one.value <= two.value) {
				cur.next = one;
				one = one.next;
			} else {
				cur.next = two;
				two = two.next;
			}
			cur = cur.next;
		}
		// post-processing
		if (one != null) {
			cur.next = one;
		} else {
			cur.next = two;
		}
		return dummy.next;
	}
	
	/*
	 * reorder the LinkedList
	 */
	public ListNode reorder(ListNode head) {
		if (head == null || head.next == null) return head;
		// step1: find the middle node
		ListNode mid = findMiddle(head);
		ListNode one = head;
		ListNode two = mid.next;
		// delink
		mid.next = null;
		
		// step2: reverse the second half
		ListNode second = reverseLinkedList(two);
		// step3: merge two halves
		return merge2(one, second);
	}
	
	private ListNode merge2(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (one != null && two != null) {
			cur.next = one;
			one = one.next;
			cur.next.next = two;
			two = two.next;
			cur = cur.next.next;
		}
		if (one != null) {
			cur.next = one;
		} else {
			cur.next = two;
		}
		return dummy.next;
	}
	
	/*
	 * partition LinkedList
	 */
	public ListNode partition(ListNode head, int target) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode small = new ListNode(0);
		ListNode large = new ListNode(0);
		ListNode curSmall = small;
		ListNode curLarge = large;
		
		while (head != null) {
			if (head.value < target) {
				curSmall.next = head;
				curSmall = curSmall.next;
			} else {
				curLarge.next = head;
				curLarge = curLarge.next;
			}
			head = head.next;
		}
		// post-processing
		curSmall.next = large.next;
		curLarge.next = null;
		return small.next;
	}
	
	public static void main(String[] args) {
		
	}
}










