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

class AscIterator {
	Deque<TreeNode> stack = new LinkedList<>();
	
	public AscIterator(TreeNode root) {
		TreeNode cur = root;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode next() {
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

class DescIterator {
	Deque<TreeNode> stack = new LinkedList<>();
	
	public DescIterator(TreeNode root) {
		TreeNode cur = root;
		while (cur != null) {
			stack.push(cur);
			cur = cur.right;
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
	// solution1:
	public static boolean twoSumInBST(TreeNode root, int target) {
		// find smallest & largest node 
		TreeNode smallest = findSmallest(root);
		TreeNode largest = findLargest(root);
		
		// 谁小移谁
		while (smallest.val != largest.val) {
			if (smallest.val + largest.val == target) {
				System.out.println(smallest.val + " " + largest.val);
				return true;
			}
			else if (smallest.val + largest.val < target) {
				smallest = smallestLarger(root, smallest.val);
			} else {
				largest = largestSmaller(root, largest.val);
			}
		}
		return false;
	}
	
	private static TreeNode findSmallest(TreeNode root) {
		TreeNode smallest = root;
		while (root != null) {
			smallest = root;
			root = root.left;
		}
		return smallest;
	}
	
	private static TreeNode findLargest(TreeNode root) {
		TreeNode largest = root;
		while (root != null) {
			largest = root;
			root = root.right;
		}
		return largest;
	}
	
	// largest number smaller than target
	public static TreeNode largestSmaller(TreeNode root, int target) {
		TreeNode largestSmaller = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val < target) {
				largestSmaller = cur;
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		return largestSmaller;
	}
	
	// smallest number larger than target
	public static TreeNode smallestLarger(TreeNode root, int target) {
		TreeNode smallestLarger = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val <= target) {
				cur = cur.right;
			} else {
				smallestLarger = cur;
				cur = cur.left;
			}
		}
		return smallestLarger;
	}
	
	// solution2: use two iterators: ascending and descending
	// Time = O(n)
	public static boolean twoSumInBST2(TreeNode root, int target) {
		AscIterator ascIterator = new AscIterator(root);
		DescIterator descIterator = new DescIterator(root);
		
		TreeNode smallest = ascIterator.next();
		TreeNode largest = descIterator.next();
		while (smallest.val != largest.val) {
			if (smallest.val + largest.val == target) {
				System.out.println(smallest.val + " " + largest.val);
				return true;
			}
			else if (smallest.val + largest.val < target) {
				smallest = smallestLarger(root, smallest.val);
			} else {
				largest = largestSmaller(root, largest.val);
			}
		}
		return false;
	}
	
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
		twoSumInBST2(root, 20);
	}
}







