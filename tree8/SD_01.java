// Given a binary tree and two nodes, return the path between these two nodes
// 	solution1: pure recursion
// 	solution2: backtracking

// subproblem: find the path from root to one node

import java.util.*;

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	TreeNode(int val) {
		this.val = val;
	}
}

public class test {
	// solution1: pure recursion
	static class ReturnType {
		List<TreeNode> path;
		int found = 0; // 1. 找到一个直上直下的path 2. already find the result
		ReturnType (int found) {
			this.found = found;
			this.path = new ArrayList<>();
		}
	}
	
	private static List<TreeNode> findPaths(TreeNode root, TreeNode a, TreeNode b) {
		ReturnType res = dfs(root, a, b);
		return res.path;
	}
	
	private static ReturnType dfs(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return new ReturnType(0);
		}
		if (root == a || root == b)  {
			List<TreeNode> list = new ArrayList<>();
			list.add(root);
			ReturnType res = new ReturnType(1);
			res.path = list;
			return res;
		}
		
		ReturnType left = dfs(root.left, a, b);
		ReturnType right = dfs(root.right, a, b);
		if (left.found == 2) {
			return left;
		} else if (right.found == 2) {
			return right;
		} else if (left.found == 1 && right.found == 1) {
			List<TreeNode> leftList = new ArrayList<>(left.path);
			List<TreeNode> rightList = new ArrayList<>(right.path);
			List<TreeNode> list = new ArrayList<>();
			list.addAll(leftList);
			list.add(root);
			list.addAll(rightList);
			ReturnType res = new ReturnType(2);
			res.path = list;
			return res;
		} else if (left.found == 1) {
			List<TreeNode> list = new ArrayList<>(left.path);
			list.add(root);
			ReturnType res = new ReturnType(1);
			res.path = list;
			return res;
		} else if (right.found == 1) {
			List<TreeNode> list = new ArrayList<>(right.path);
			list.add(root);
			ReturnType res = new ReturnType(1);
			res.path = list;
			return res;
		}
		return new ReturnType(0);
	}
	
	// solution2: backtracking
	public static List<TreeNode> findPaths2(TreeNode root, TreeNode a, TreeNode b) {
		List<TreeNode> list1 = new ArrayList<>();
		dfs2(root, a, list1);
		Collections.reverse(list1);
		
		List<TreeNode> list2 = new ArrayList<>();
		dfs2(root, b, list2);
		Collections.reverse(list2);
		
		return combineLists(list1, list2);
	}
	
	private static List<TreeNode> combineLists(List<TreeNode> list1, List<TreeNode> list2) {
		List<TreeNode> res = new ArrayList<>();
		int i = 0; 
		int j = 0;
		// find the last common ele
		while (i < list1.size() && j < list2.size()) {
			int val1 = list1.get(i).val;
			int val2 = list2.get(j).val;
			if (val1 == val2) {
				i++;
				j++;
			} else {
				break;
			}
		}
		
		res.add(list1.get(i - 1));
		while (i < list1.size()) {
			res.add(list1.get(i));
			i++;
		}
		while (j < list2.size()) {
			res.add(list2.get(j));
			j++;
		}
		return res;
	}
	
	// pure recursion: https://algorithms.tutorialhorizon.com/print-a-path-from-root-to-node-in-binary-tree/
	public static boolean dfs2(TreeNode root, TreeNode target, List<TreeNode> path) {
		if (root == null) return false;
		if (root == target) {
			path.add(root);
			return true;
		}
		if (dfs2(root.left, target, path)) {
			path.add(root);
			return true;
		}
		if (dfs2(root.right, target, path)) {
			path.add(root);
			return true;
		}
		return false;
	}
	
	// backtracking: https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/
	public static boolean dfs3(TreeNode root, TreeNode target, List<TreeNode> path) {
		if (root == null) return false;
		
		path.add(root);
		if (root == target) return true;
		
		if (dfs3(root.left, target, path)) return true;
		if (dfs3(root.right, target, path)) return true;
		
		path.remove(path.size() - 1);
		return false;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(9);
		
		root.left = new TreeNode(7);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = a;
		root.left.left.right.left = new TreeNode(3);
		root.left.right = new TreeNode(10);
		root.left.right.left = b;
		root.left.right.right = new TreeNode(13);
		root.left.right.right.right = new TreeNode(18);
		
//		List<TreeNode> res = findPaths(root, a, b);
//		for (TreeNode ele : res) {
//			System.out.print(ele.val + " ");
//		}
//		List<TreeNode> res = findPaths2(root, a, b);
//		for (TreeNode ele : res) {
//			System.out.print(ele.val + " ");
//		}
		
		List<TreeNode> testPath = new ArrayList<>();
		dfs3(root, b, testPath);
		for (TreeNode ele : testPath) {
			System.out.print(ele.val + " ");
		}
	}
}


