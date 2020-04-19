// K closest to target
// solution: inorder iterator

import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}

// smallest Larger
class AscIterator {
	Deque<TreeNode> stack = new LinkedList<>();
	
	public AscIterator(TreeNode root, int target) {
		// find smallest larger 
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val <= target) {
				cur = cur.right;
			} else {
				stack.push(cur);
				cur = cur.left;
			}
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode next() { // amortized O(1)
		if (!hasNext()) return null;
		
		TreeNode cur = stack.pop();
		TreeNode result = cur;
		cur = cur.right;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
		return result;
	}
}

// largest smaller or equals
class DescIterator {
	Deque<TreeNode> stack = new LinkedList<>();
	
	public DescIterator(TreeNode root, int target) {
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val > target) {
				cur = cur.left;
			} else {
				stack.push(cur);
				cur = cur.right;
			}
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode next() {
		if (!hasNext()) return null;
		TreeNode cur = stack.pop();
		TreeNode result = cur;
		cur = cur.left;
		while (cur != null) {
			stack.push(cur);
			cur = cur.right;
		}
		return result;
	}
}

public class test {
	public static TreeNode createTestCase() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(7);
		root.left.right.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(9);
		root.right.left.right = new TreeNode(12);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(17);
		root.right.right.right = new TreeNode(25);
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = createTestCase();
		AscIterator iterator = new AscIterator(root, 9);
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().val);
//		}
		
		DescIterator iterator2 = new DescIterator(root, 9);
//		while (iterator2.hasNext()) {
//			System.out.println(iterator2.next().val);
//		}
		
		int k = 4; // should return 9, 8, 7, 12
		TreeNode smallestLarger = iterator.next();
		TreeNode largestSmallerOrEquals = iterator2.next();
		
		for (int i = 0; i < k; i++) {
			if (smallestLarger == null) {
				System.out.println(largestSmallerOrEquals.val);
				largestSmallerOrEquals = iterator2.next();
			} else if (largestSmallerOrEquals == null) {
				System.out.println(smallestLarger.val);
				smallestLarger = iterator.next();
			} else {
				if (Math.abs(9 - smallestLarger.val) < Math.abs(9 - largestSmallerOrEquals.val)) {
					System.out.println(smallestLarger.val);
					smallestLarger = iterator.next();
				} else {
					System.out.println(largestSmallerOrEquals.val);
					largestSmallerOrEquals = iterator2.next();
				}
			}							
		}
	}
}







