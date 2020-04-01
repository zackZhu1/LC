public int pathSum(TreeNode root, int sum) {
	Map<Integer, Integer> map = new HashMap<>();
	map.put(0, 1);
	return dfs(root, 0, sum, map);
}

private void dfs(TreeNode root, int prevSum, int target, Map<Integer, Integer> map) {
	if (root == null) return 0;

	prevSum += root.val;
	int count = 0;
	if (map.containsKey(prevSum - target)) {
		count += map.get(curSum - target);
	}

	if (!map.containsKey(prevSum)) {
		map.put(prevSum, 1);
	} else {
		map.put(prevSum, map.get(prevSum) + 1);
	}

	int leftCount = dfs(root.left, prevSum, target, map);
	int rightCount = dfs(root.right, prevSum, target, map);

	map.put(prevSum, map.get(curSum) - 1);
	return leftCount + rightCount + count;
}