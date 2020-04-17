// solution1:
public void construct(int[] pre, int pStart, int pEnd, int[] in, int iStart, int iEnd, Map<Integer, Integer> map, List<Integer> postOrder) {
	if (pStart > pEnd) return;

	int rootValue = pre[pStart];
	int inIndex = map.get(root.val);
	int leftSize = inIndex - 1 - iStart + 1;
	int rightSize = iEnd - (inIndex + 1) + 1;
	root.left = construct(pre, pStart + 1, pStart + 1 + leftSize - 1, in, iStart, inIndex - 1, map, postOrder);
	root.right = construct(pre, pEnd - rightSize + 1, pEnd, in, inIndex + 1, iEnd, map, postOrder);
	postOrder.add(rootValue);
}

// solution2:
int preIndex = 0;
int inIndex = 0;
public void construct(int[] pre, int[] in, int inOrderSuccessor, List<Integer> postOrder) {
	if (inIndex == in.length || in[inIndex] == inOrderSuccessor) {
		return;
	}

	int rootValue = pre[preIndex];
	preIndex++;

	construct(pre, in, rootValue, postOrder);
	inIndex++;
	construct(pre, in, inOrderSuccessor, postOrder);
	postOrder.add(rootValue);
}
construct(pre, in, Integer.MAX_VALUE);