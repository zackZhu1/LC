// Reconstruct a binary tree with level-order + in-order
// Time = O(n^2)
// Space = O(n)
// https://www.techiedelight.com/construct-binary-tree-from-inorder-level-order-traversals/

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
	public static TreeNode buildTree(int[] in, int[] level) {
		Map<Integer, Integer> levelMap = getLevelMap(level);
		return reconstruct(in, 0, in.length - 1, levelMap);
	}
	
	private static Map<Integer, Integer> getLevelMap(int[] level) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < level.length; i++) {
			map.put(level[i], i);
		}
		return map;
	}
	
	private static TreeNode reconstruct(int[] in, int start, int end, Map<Integer, Integer> levelMap) {
		if (start > end) return null;
		
		int index = start;
		for (int i = start + 1; i <= end; i++) {
			if (levelMap.get(in[index]) < levelMap.get(in[index])) {
				index = i;
			}
		}
		
		TreeNode root = new TreeNode(in[index]);
		root.left = reconstruct(in, start, index - 1, levelMap);
		root.right = reconstruct(in, index + 1, end, levelMap);
		return root;
	}
	
	private static void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		int[] in = {4, 2, 5, 1, 6, 3, 7};
		int[] level = {1, 2, 3, 4, 5, 6, 7};
		TreeNode root = buildTree(in, level);
		inorder(root);
	}
}


