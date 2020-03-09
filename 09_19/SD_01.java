class Pair {
	int index;
	int value;
}


public int dotProduct(ArrayList<Pair> a, ArrayList<Pair> b) {
	int i, j = 0;
	int dotSum = 0;
	while (i < a.size() && j < b.size()) {
		Pair ap = a.get(i);
		Pair bp = b.get(j);
		if (ap.index == bp.index) {
			dotSum += ap.value * bp.value;
			i++;
			j++;
		} else if (ap.index < bp.index) {
			i++;
		} else {
			j++;
		}
	}
	return dotSum;
}