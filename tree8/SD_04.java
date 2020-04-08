// return value: sum of the current subtree
public int sum(List<NestedInteger> input, int level) {
	int sum = 0;
	for (NestedInteger ni : input) {
		if (ni.isInteger()) {
			sum += ni.getInteger() * level;
		} else {
			sum += sum(ni.getList(), level + 1);
		}
	}
	return sum;
}
sum(input, 1);

// sum: prefixSum before traversing current subtree
// return value: prefixSum after traversing current subtree
public int sum(List<NestedInteger> input, int level, int sum) {
	for (NestedInteger ni : input) {
		if (ni.isInteger()) {
			sum += ni.getInteger() * level;
		} else {
			sum = sum(ni.getList(), level + 1, sum);
		}
	}
	return sum;
}
sum(input, 1, 0);