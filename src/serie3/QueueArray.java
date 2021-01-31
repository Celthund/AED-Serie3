package serie3;
import java.util.EmptyStackException;

public class QueueArray<E> implements Queue<E>{
    private E[] q;
    private int head;
    private int tail;
    private int count;

    public QueueArray(int max) {
        q = (E[]) new Object[max]; //solução!
        head = 0;
        tail = 0;
        count = 0;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public E enqueue(E item) {
        if (count == q.length) return null;	// queue full
        q[tail] = item;
        tail = (tail + 1) % q.length;
        count++;
        return item;
    }

    public E dequeue() {
        if(isEmpty()) throw new EmptyStackException();
        E e = q[head];
        head = (head + 1) % q.length;
        count--;
        return e;
    }

    public E peek() {
        if(isEmpty()) throw new EmptyStackException();
        return q[head];
    }
}
