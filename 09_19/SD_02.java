// solution1: 
class Solution {
	class Triple {
		int x;
		int y;
		int val;
		Triple(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	public int[][] multiply(int[][] A, int[][] B) {
		int[][] result = new int[A.length][B[0].length];
		List<Triple> listA = new ArrayList<>();
		List<Triple> listB = new ArrayList<>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] != 0) listA.add(new Triple(i, j, A[i][j]));
			}
		}
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				if (B[i][j] != 0) listB.add(new Triple(i, j, B[i][j]));
			}
		}

		for (Triple tripleA : listA) {
			for (Triple tripleB : listB) {
				if (tripleA.y == tripleB.x) {
					result[tripleA.x][tripleB.y] += tripleA.val * tripleB.val;
				}
			}
		}

		return result;
	}
}

// solution2:
class Solution {
    class Triple {
        int x;
        int y;
        int val;
        Triple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        
        HashMap<Integer, List<Triple>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] != 0) {
                    if (!map.containsKey(i)) {
                        map.put(i, new ArrayList<>());
                    } 
                    map.get(i).add(new Triple(i, j, B[i][j]));
                }
            }
        }
        
        List<Triple> listA = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                listA.add(new Triple(i, j, A[i][j]));
            }
        }
        
        for (Triple tripleA : listA) {
            if (map.containsKey(tripleA.y)) {
                List<Triple> listB = map.get(tripleA.y);
                for (Triple tripleB : listB) {
                    result[tripleA.x][tripleB.y] += tripleA.val * tripleB.val;       
                }
            }
        }
        return result;
    }
}