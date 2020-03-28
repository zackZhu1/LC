/*
1. in order to find if key is in the cache: use HashMap
2. in order to update the priority of key: need to know its prev and next, thus use DLL

put(int key, int value):
	if key is already in the cache: 
		update key's priority by moving it to the tail of the cache
	if not:
		if the cache is full: delete the head in both DLL and HashMap
		if the cache is not full: insert key to the tail

get(int key):
	if key already in the cache:
		update its priority to the tail
	if key is not in the cache:
		return -1
*/


class LRUCache {
    class Node {
        Integer key; 
        Integer value;
        Node prev;
        Node next;
        
        Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
        
    private Map<Integer, Node> map; 
    private int capacity;
    private Node head; // dummy head
    private Node tail; // dummy tail
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        // move the current node to the tail of the DLL
        detach(node);  
        attach(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            detach(node);
            attach(node);
        } else {
            if (map.size() == capacity) {
                map.remove(head.next.key);
                detach(head.next);
            }
            node = new Node(key, value);
            map.put(key, node);
            attach(node);
        }
    }
    
    void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
    
    void attach(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */