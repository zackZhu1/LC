// Reconstruct a general n-ary tree using pre-order and post-order
import java.util.*;

class TreeNode {
	String val;
	List<TreeNode> children;
	
	public TreeNode(String value) {
		this.val = new String(value);  // String assignment
		this.children = new ArrayList<>();
	}
}

public class test {
	public static TreeNode reconstruct(String[] pre, String[] post) {
		Map<String, Integer> postMap = new HashMap<>();
		for (int i = 0; i < post.length; i++) {
			postMap.put(post[i], i);
		}
		
		return helper(pre, 0, pre.length - 1, post, 0, post.length - 1, postMap);
	}
	
	private static TreeNode helper(String[] pre, int preStart, int preEnd, String[] post, int postStart, int postEnd, Map<String, Integer> postMap) {
		//if (preStart > preEnd) return null;
		if (preStart == preEnd) return new TreeNode(pre[preStart]);
		
		TreeNode root = new TreeNode(pre[preStart]);

		int preIndex = preStart + 1;
		int lastPostIndex = 0;
		while (preIndex < pre.length) {
			int preIndexInPost = postMap.get(pre[preIndex]);
			int subTreeLen = preIndexInPost - lastPostIndex + 1;
			
			int preStart2 = preIndex;
			int preEnd2 = preStart2 + subTreeLen - 1;
			int postStart2 = lastPostIndex;
			int postEnd2 = preIndexInPost;
			
			// test correctness of boundary 
			System.out.println(preStart2 + " " + preEnd2 + " " + postStart2 + " " + postEnd2);
			
			// recursion
			root.children.add(helper(pre, preStart2, preEnd2, post, postStart2, postEnd2, postMap));
			
			// update index
			preIndex = preEnd2 + 1;
			lastPostIndex = postEnd2 + 1;
		}
		return root;
	}
	
	private static int preIndex = 0;
	private static int postIndex = 0;
	public static TreeNode reconstruct2(String[] pre, String[] post) {
		TreeNode root = new TreeNode(pre[preIndex]);
		preIndex++;
		while (!post[postIndex].equals(root.val)) {
			root.children.add(reconstruct2(pre, post));
		}
		postIndex++;
		return root;
	}
	
	private static void levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				System.out.print(cur.val + " ");
				for (TreeNode child : cur.children) {
					queue.offer(child);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String[] pre = {"A", "B", "F", "G", "H", "C", "I", "D", "J", "K", "E"};
		String[] post = {"F", "G", "H", "B", "I", "C", "J", "K", "D", "E", "A"};
		TreeNode root = reconstruct2(pre, post);
		levelOrder(root);
	}
}












