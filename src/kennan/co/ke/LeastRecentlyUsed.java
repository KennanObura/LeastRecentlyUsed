package kennan.co.ke;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeastRecentlyUsed<R> {
    LeastRecentlyUsed(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
    }

    private final int capacity;
    private ConcurrentHashMap<R, Node<R>> cache;
    private Node<R> head = null;
    public Node<R> tail = null;

    /**
     * looks up in our cache and updates the Most Recently Used
     * In this case, the most recently used will be the tail, for simplicity
     * while the least used be the head
     * <p>
     * :: On look up
     * if item present in map
     * - remove it from current position in list
     * - attach it at the tail :: Make it Most recently used
     * <p>
     * else
     * while capacity is not reached
     * - add to map
     * - add at the tail of the list
     * capacity is reached
     * - evict/remove the head of the list to make space
     * - remove from map
     * <p>
     * - add new entry in map
     * - add to the tail of the list
     *
     * @param key of the node to lookup
     */
    public void lookUp(R key) {

        if (cache.containsKey(key)) {
            Node<R> node = cache.get(key);
            this.deleteNode(node);
            this.addNodeAtTheTail(node);
            return;
        }

        Node<R> new_node = new Node<>(key);
        if (isCapacityReached()) {
            this.cache.remove(head.value);
            this.deleteNode(head);
        }
        this.cache.put(key, new_node);
        this.addNodeAtTheTail(new_node);
    }


    private void deleteNode(Node<R> node) {
        if (node == null) return;

        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;

        }
    }

    private boolean isCapacityReached() {
        return (this.cache.size()) >= this.capacity;
    }

    private void addNodeAtTheTail(Node<R> node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.previous = tail;
        tail = node;
    }


    public void print() {
        /*
        Print map content
         */
        System.out.println("Printing map");
        for (Map.Entry<R, Node<R>> entry : cache.entrySet())
            System.out.println(entry.getKey() + " nodes" + entry.getValue());


        System.out.println("Printing list");
        print(head);
    }

    public void isCyclic(){
        if(isCyclic(head, head)) System.out.println("Circle detected");
        else System.out.println("No Circle" );
    }

    private boolean isCyclic(Node<R> fast, Node<R> slow) {
        if(fast == null) return false;
        if(fast.value == slow.value){
            System.out.println(fast.value + "is the cirle");
            return true;
        }
        return isCyclic(fast.next.next, slow.next);
    }

    private void print(Node<R> head) {
        if (head == null) return;
        System.out.println(head.value);
        print(head.next);
    }
}
