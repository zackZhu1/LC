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

public class test {
	// find the closest
	// solution1:
	public static TreeNode closest(TreeNode root, int target) {
		TreeNode closest = root;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val == target) return cur;
			if (Math.abs(cur.val - target) < Math.abs(closest.val - target)) {
				closest = cur;
			}
			if (target > cur.val)
				cur = cur.right;
			else 
				cur = cur.left;
		}
		return closest;
	}
	
	// solution2: find the largestSmaller + smallestLargerOrEquals
	public static TreeNode closest2(TreeNode root, int target) {
		TreeNode largestSmaller = null;
		TreeNode smallestLargerOrEquals = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val < target) {
				largestSmaller = cur;
				cur = cur.right;
			} else {
				smallestLargerOrEquals = cur;
				cur = cur.left;
			}
		}
		// find the closest
		System.out.println(largestSmaller.val);
		System.out.println(smallestLargerOrEquals.val);
		return Math.abs(target - largestSmaller.val) < Math.abs(target - smallestLargerOrEquals.val) ? largestSmaller : smallestLargerOrEquals;
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
	
	// largest number smaller than or equals to target
	public static TreeNode largestSmallerOrEquals(TreeNode root, int target) {
		TreeNode result = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val <= target) {
				result = cur;
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		return result;
	}
	
	public static TreeNode smallestLargerThenEquals(TreeNode root, int target) {
		TreeNode result = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val < target) {
				cur = cur.right;
			} else {
				result = cur;
				cur = cur.left;
			}
		}
		return result;
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
		
		int target = 10;
		System.out.println(closest2(root, target).val);
//		System.out.println(largestSmaller(root, target).val);
//		System.out.println(smallestLarger(root, target).val);
//		
//		target = 9;
//		System.out.println(largestSmallerOrEquals(root, target).val);
//		System.out.println(smallestLargerThenEquals(root, target).val);
	}
}







