import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head, tail;
	private int size;
	private int modCount;

    /** Creates an empty list */
	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);
        if(head != null) {
            newNode.setNext(head);
            head.setPrevious(newNode);
        }
        head = newNode;

        if(tail == null) {
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if(head == null) {
            head = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
        }
        tail = newNode;

        size++;
        modCount++;
        
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal;

		if (size() == 1) { // puts head and tail in the right place for a one element list
			retVal = head.getElement();
			head = tail = null;
		} else { // removes everything else
			Node<T> current = head;

			retVal = current.getElement();
			head = current.getNext();
            head.setPrevious(null);
		}

		size--;
		modCount++;

		return retVal;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal;

		if (size() == 1) { // puts head and tail in the right place for a one element list
			retVal = tail.getElement();
			head = tail = null;
		} else { // removes everything else
			Node<T> current = tail;

			retVal = current.getElement();
			tail = current.getPrevious();
            tail.setNext(null);
		}

		size--;
		modCount++;

		return retVal;
    }

    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        int currentIndex = 0;

        while (current != null && !current.getElement().equals(element)) {
            current = current.getNext();
            currentIndex++;
        }

        if(current == null) {
            currentIndex = -1;
        }

        return currentIndex;
    }

    @Override
    public T first() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        return head.getElement();
    }

    @Override
    public T last() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        return (indexOf(target) != -1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
		str.append("[");
		for (T element : this) {
			str.append(element.toString());
			str.append(", ");
		}
		if (size > 0) {
			str.delete(str.length() - 2, str.length()); // remove trailing ", "
		}
		str.append("]");
		return str.toString();
    }


    
}
