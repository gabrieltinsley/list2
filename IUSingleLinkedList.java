import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author Gabriel Tinsley
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private Node<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(head);
		head = newNode;

		if (tail == null) { // puts tail in the right place
			tail = newNode;
		}

		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T>(element);

		if (head == null) { // empty list
			head = newNode;
		} else { // every other kind of list
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

		Node<T> current = head;

		while (current != null && !current.getElement().equals(target)) { // finds target
			current = current.getNext();
		}

		if (current == null) {
			throw new NoSuchElementException();
		}

		Node<T> newNode = new Node<T>(element);
		newNode.setNext(current.getNext());
		current.setNext(newNode);

		if (newNode.getNext() == null) { // puts tail in the right place
			tail = newNode;
		}

		size++;
		modCount++;

	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			addToFront(element);
		} else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) { // finds index to add after
				current = current.getNext();
			}
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			if (newNode.getNext() == null) { // puts tail in the right place
				tail = newNode;
			}

			size++;
			modCount++;
		}
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
			retVal = head.getElement();
			head = tail = null;
		} else { // removes everything else
			Node<T> current = head;

			for (int i = 0; i < size - 2; i++) {
				current = current.getNext();
			}
			retVal = tail.getElement();
			current.setNext(null);
			tail = current;
		}

		size--;
		modCount++;

		return retVal;
	}

	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		Node<T> previous = null;
		Node<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { // only node
			head = tail = null;
		} else if (current == head) { // first node
			head = current.getNext();
		} else if (current == tail) { // last node
			tail = previous;
			tail.setNext(null);
		} else { // somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T retVal;

		if (index == 0) { // first node
			retVal = removeFirst();
		} else { // somewhere in the middle
			Node<T> current = head;
			Node<T> previous = null;

			for (int i = 0; i < index; i++) {
				previous = current;
				current = current.getNext();
			}

			retVal = current.getElement();

			previous.setNext(current.getNext());
			if (current.getNext() == null) { // puts the tail in place
				tail = previous;
			}
			size--;
			modCount++;
		}

		return retVal;

	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> current = head;

		for (int i = 0; i < index; i++) { // finds index to change
			current = current.getNext();
		}

		current.setElement(element);

		modCount++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T retVal;
		if (index == 0) { // first node
			retVal = head.getElement();
		} else { // somewhere in the middle
			Node<T> current = head;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}

			retVal = current.getElement();

		}
		return retVal;
	}

	@Override
	public int indexOf(T element) {
		Node<T> current = head;
		int currentIndex = 0;

		while (current != null && !current.getElement().equals(element)) { // finds index of element
			current = current.getNext();
			currentIndex++;
		}

		if (current == null) {
			currentIndex = -1;
		}

		return currentIndex;
	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if (isEmpty()) {
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
	public String toString() {
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

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private Node<T> nextNode;
		private int iterModCount;
		private boolean removable;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			removable = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			return nextNode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal = nextNode.getElement();
			nextNode = nextNode.getNext();
			removable = true;

			return retVal;
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if (!removable) {
				throw new IllegalStateException();
			}

			removable = false;

			Node<T> prevPrevNode = null;
			if (head.getNext() == nextNode) { // for the case of removing first element
				head = nextNode;
			} else { // removing everything else
				prevPrevNode = head;
				while (prevPrevNode.getNext().getNext() != nextNode) {
					prevPrevNode = prevPrevNode.getNext();
				}

				prevPrevNode.setNext(nextNode);

			}
			if (nextNode == null) { // puts tail in the right spot
				tail = prevPrevNode;
			}

			size--;

			modCount++;
			iterModCount++;
		}
	}

	
}