class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(11, new Comparator<>() {
            @Override
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            maxHeap.offer(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry entry = maxHeap.poll();
            for (int i = 0; i < (int) entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}

class Solution {
    class MyComparator implements Comparator<Map.Entry<Character, Integer>> {
        @Override
        public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
            return b.getValue() - a.getValue();
        }
    }
    
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(11, new MyComparator());
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            maxHeap.offer(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry entry = maxHeap.poll();
            for (int i = 0; i < (int) entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
