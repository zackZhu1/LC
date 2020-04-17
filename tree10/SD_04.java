List<Integer> postOrder = new ArrayList<>();
int preIndex = 0;

public void reconstruct(int[] pre, int inOrderSuccessor) {
	if (preIndex == pre.length || pre[preIndex] > inOrderSuccessor) return;

	int rootValue = pre[preIndex];
	preIndex++;
	reconstruct(pre, root.val);
	reconstruct(pre, rootValue);
	postOrder.add(rootValue);
}