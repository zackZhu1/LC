// Determine if a pre-order sequence is a valid BST
int preIndex = 0;
public boolean construct(int[] pre, int min, int inOrderSuccessor) {
	if (preIndex == pre.length || pre[preIndex] > inOrderSuccessor) {
		return true;
	}

	if (pre[preIndex] <= min) return false;

	int rootValue = pre[preIndex];
	preIndex++;

	return construct(pre, min, rootValue) && construct(pre, rootValue, inOrderSuccessor);
}