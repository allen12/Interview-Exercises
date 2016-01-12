package datastructures;

/**
 * <p> A FIFO Queue implemented with LinkedLists. This means that
 * enqueue is a O(n) operation and dequeue is a O(1) operation
 * per the LinkedList implementation.
 * 
 * <p> This specific implementation DOES NOT permit NULL arguments
 * 
 * @param <T>
 */
public class Queue<T>
{
	private LinkedList<T> list;
	
	/**
	 * Constructs an empty queue.
	 */
	public Queue()
	{
		list = new LinkedList<T>();
	}
	
	/**
	 * Offers, or enqueues an element onto the queue.
	 * 
	 * @param ele Element to add
	 * @throws IllegalArgumentException if client tries to add a NULL element
	 */
	public void offer(T ele)
	{
		if (ele == null)
			throw new IllegalArgumentException("Tried to add a NULL element!");
		
		list.addLast(ele);
	}
	
	/**
	 * Polls, or dequeues an element from the queue.
	 * 
	 * @return The removed element
	 * @throws IllegalStateException if queue is empty
	 */
	public T offer()
	{
		return list.removeFirst();
	}
	
	/**
	 * Returns the size of the queue
	 * @return The number of contained elements
	 */
	public int size()
	{
		return list.size();
	}
}
