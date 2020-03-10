int fibo(int x, Map<Integer, Integer> cache) {
	// base case
	if (x <= 0) return 0;
	if (x == 1) return 1;
	// check if subproblem visited
	if (cache.contains(x)) return cache.get(x);

	// induction rule
	int result = 0;
	int n1 = fibo(x - 1, cache);
	int n2 = fibo(x - 2, cache);

	// mark visited
	cache.put(x, n1 + n2);

	return n1 + n2;
}