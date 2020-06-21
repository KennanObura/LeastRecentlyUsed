package kennan.co.ke;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LeastRecentlyUsed<Integer> lru = new LeastRecentlyUsed<>(5);
        lru.lookUp(3);
        lru.lookUp(10);
        lru.lookUp(12);
//        lru.lookUp(23);
//        lru.lookUp(231);
        lru.lookUp(31);
//        lru.lookUp(3);
        lru.isCyclic();
//        lru.print();
    }
}
