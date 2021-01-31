package serie3;
import java.util.Comparator;


public class Heap<T>{
    //Default size of the heap
    private final int INITIAL_SIZE = 10;
    private int size, num_elements = 0;
    private T[] array;
    private Comparator<T> comparator;

    //In case the user don't set an initial size, this constructor will set one as default
    public Heap(Comparator<T> comparator){
        this.comparator = comparator;
        this.size = INITIAL_SIZE;
        this.array = (T[]) new Object[this.size];
    }

    //Constructor of the Heap
    public Heap(Comparator<T> comparator, int size){
        this.comparator = comparator;
        this.size = size;
        this.array = (T[]) new Object[this.size];
    }

    //Returns the size of the heap
    public int size() {
        return this.num_elements;
    }

    //Adds a new element to the heap and heapify up right after.
    public void add(T a) {

        //If the heap is currently at max size, it will increments its side by 2 times
        if (num_elements == size){
            resizeArray();
        }

        //If is not it will just add
        array[num_elements++] = a;

        //Organizes the elements from leaf to root
        siftUp(num_elements);
    }

    //Removes the root of the heap and heapify down right after.
    public T poll() {

        //If theres is no elements just returns null
        if (num_elements == 0) return null;

        //The element is the root of the Heap
        T element = array[0];

        //Puts the last leaf of the heap on to the root
        array[0] = array[num_elements - 1];

        //If in that current position still doesnt exist any elements then it will check for the
        //position before it until it finds one position containing an element
        array[num_elements - 1] = null;
        num_elements--;

        //When it finds an elements siftDown it o organize the heap
        siftDown(1);

        //Returns the removed element
        return element;
    }

    //Shows the current root of the Heap
    public T peek() {
        if (num_elements == 0) return null;
        return array[0];
    }

    //Repleaces the root of the Heap and siftDown it so it stays organized
    public void replaceHead(T head){
        array[0] = head;
        siftDown(1);
    }

    //Passes the Heap to an array
    public Object[] toArray() {
        Object[] array=  new Object[num_elements];
        if (num_elements > 0)
            System.arraycopy(this.array, 0, array, 0, num_elements);
        return array;
    }

    //Swaps the comparators being used
    public void changeComparator(Comparator<T> comparator){
        this.comparator = comparator;
        heapify();
    }

    //Increases the Heap by 2 times is size
    private void resizeArray() {
        this.size *= 2;
        Object[] new_array = new Object[this.size];;
        if (num_elements > 0) {
            System.arraycopy(array, 0, new_array, 0, num_elements);
            array = (T[]) new_array;
        }
    }

    //Heapify all the Heap by calling siftDown starting on the parents of the leafs and finishing
    //on the child of the root
    public void heapify(){
        for (int i = num_elements / 2; i > 0; i--){
            siftDown(i);
        }
    }

    //Swaps positions in the array
    private void switchElements(int pos1, int pos2){
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }

    //siftDown starting at the leaf
    private void siftUp(int current_position){
        //Terminal case. Check if root element.
        if (current_position == 1) return;

        //Divides the Heap in two to find the parents of the element in the current_position
        int parentPosition = current_position / 2;

        //Swaps the parent with the current position being checked in case the child is greater
        //than its parent
        if (comparator.compare(array[current_position - 1], array[parentPosition - 1]) < 0)
            switchElements(current_position - 1, parentPosition - 1);

        //Recursively do the same for all the parents till it reaches the root
        siftUp(parentPosition);
    }

    //siftDown starting at the rot
    private void siftDown(int current_position){
        //Terminal case. Check if there is a left child, if it doesnt exist it will just end
        if (current_position * 2 > num_elements) return;

        //Checks the child of the element in the current position being checked
        int child_position;

        //Checks for child on the right side, if the child position on the right is greater
        //than the current Heap size, then there is no right child, and so the current child
        //will become the left child
        if (((current_position * 2) + 1) > num_elements){
            child_position = current_position * 2;
        } else {
            //Compares the left and right child, child_position will become the greater of the 2 childs
            if (comparator.compare(array[current_position * 2 - 1], array[current_position * 2]) <= 0){
                child_position = current_position * 2;
            } else {
                child_position = current_position * 2 + 1;
            }
        }

        //Swaps the child_position(child) element with the current_position(parent) element in case the
        //child is greater than the parent
        if (comparator.compare(array[child_position - 1], array[current_position - 1]) < 0) {
            switchElements(current_position - 1, child_position - 1);
            siftDown(child_position);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
