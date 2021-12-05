import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingDequeImpl<E> implements ResizingDeque<E> {

    E[] array;
    int head;
    int tail;
    int size;
    
    @SuppressWarnings("unchecked")
    public ResizingDequeImpl() {
        this.array = (E[]) new Object[2];
        this.size = 0;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E[] getArray() {
        return this.array;
    }

    @SuppressWarnings("unchecked")
    void resizeDouble() {
        E[] arr = (E[]) new Object[array.length * 2];
        System.arraycopy(this.array, this.head, arr, 0, this.array.length - this.head);
        System.arraycopy(this.array, 0, arr, this.array.length - this.head, this.head);
        this.head = 0;
        this.tail = this.array.length - 1;
        this.array = (E[]) arr;
    }
    
    @SuppressWarnings("unchecked")
    void resizeHalf() {
        E[] arr = (E[]) new Object[array.length / 2];
        if (this.array.length == 4) {
            this.array = (E[]) new Object[2];
            return;
        }
        if (this.head > this.tail) {
            System.arraycopy(this.array, this.head, arr, 0, this.array.length - this.head);
            System.arraycopy(this.array, 0, arr, this.array.length - this.head, this.tail);
        } else if (this.head == this.tail) {
            System.arraycopy(this.array, this.head, arr, 0, 1);
        } else {
            System.arraycopy(this.array, this.head, arr, 0, this.tail - this.head);
        }
        this.head = 0;
        this.tail = this.array.length / 4 - 1 - (this.array.length / 8);
        this.array = (E[]) arr;
    }
    
    @Override
    public void addFirst(E e) {
        if (this.size == this.array.length) {
            resizeDouble();
        }
        if (this.size == 0) {
            this.array[0] = e;
            this.head = 0;
            this.tail = 0;
        } else {
            if (this.head == 0) {
                this.head = this.array.length - 1;
            } else {
                this.head--;
            }
            this.array[this.head] = e;
        }
        this.size++;
    }

    @Override
    public void addLast(E e) {
        if (this.size == this.array.length) {
            resizeDouble();
        }
        if (this.size == 0) {
            this.array[0] = e;
            this.head = 0;
            this.tail = 0;
        } else {
            if (this.tail == this.array.length - 1) {
                this.tail = 0;
            } else {
                this.tail++;
            }
            this.array[this.tail] = e;
        }
        this.size++;
    }

    @Override
    public E pollFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("deque is empty");
        }
        E e = this.array[this.head];
        this.size--;
        this.array[this.head] = null;
        if (this.head == this.array.length - 1) {
            this.head = 0;
        } else {
            this.head++;
        }
        if (this.size < this.array.length / 4 && this.array.length >= 4) {
            resizeHalf();
        }
        return e;
    }

    @Override
    public E pollLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("deque is empty");
        }
        E e = this.array[this.tail];
        this.size--;
        this.array[this.tail] = null;
        if (this.tail == 0) {
            this.tail = this.array.length - 1;
        } else {
            this.tail--;
        }
        if (this.size < this.array.length / 4 && this.array.length >= 4) {
            resizeHalf();
        }
        return e;
    }

    @Override
    public E peekFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("deque is empty");
        }
        return this.array[this.head];
    }

    @Override
    public E peekLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("deque is empty");
        }
        return this.array[this.tail];
    }
    
    @SuppressWarnings("hiding")
    class LazyIterator<E> implements Iterator<E> {
        int position = 0;
        
        @Override
        public boolean hasNext() {
            return array.length > position;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next");
            }
            return (E) array[position++];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LazyIterator<E>();
    }

}
