package serie3;

public interface Queue <E> {
    public boolean isEmpty();
    public E enqueue(E i);
    public E dequeue();
    public E peek();
}
