package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> newNode = new QueueNode<E>(e);
		
		if(last == null) {
			last = newNode;
			last.next = last;
			size = 1;
			return true;
		}
		
		QueueNode<E> oldLast = last;
		newNode.next = last.next;
		last = newNode;
		oldLast.next = last;
		size++;
		
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;
		}
		
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null) {
			return null;
		}
		
		E value = last.next.element;
		
		last.next = last.next.next;
		
		size--;
		
		if(size <= 0) {
			last = null;
			size = 0;
		}
		
		return value;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this.equals(q)) {
			throw new IllegalArgumentException();
		}
		
		if(q.last == null) {
			return;
		}
		
		// Om denna lista är null kan vi bara peka till datan från den andra.
		if(last == null) {
			last = q.last;
			size = q.size;
			
			// Töm den andra listan.
			q.size = 0;
			q.last = null;
			return;
		}
		
		QueueNode<E> oldLast = last;
		QueueNode<E> first = last.next;
		oldLast.next = q.last.next;
		
		// Peka den andra listans sista next till denna listans första
		q.last.next = first;
		
		// Peka den sista till den andra listans sista
		last = q.last;
		
		// Töm den andra listan.
		size += q.size;
		q.size = 0;
		q.last = null;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new FifoQueueIterator();
	}
	
	private class FifoQueueIterator implements Iterator<E> {
		private QueueNode<E> position;
		private int i = 0;
		
		private FifoQueueIterator( ) {
			
			if(last == null) {
				position = null;
				return;
			}
			
			position = last.next;
		}
		
		@Override
		public boolean hasNext() {
			if(i >= size) {
				return false;
			}
			
			return  true;
		}

		@Override
		public E next() {
			if(position == null || i >= size) {
				throw new NoSuchElementException();
			}
		
			E value = position.element;
			position = position.next;
			i++;
			
			return value;
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E element) {
			this.element = element;
			next = null;
		}
	}

}
