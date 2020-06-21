package kennan.co.ke;

public class Node<R> {
    public Node<R> next;
    public Node<R> previous;
    public final R value;

    public Node(R value){
        this.value = value;
    }
}
