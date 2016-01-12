package datastructures;

/**
 * A LIFO Stack implemented with LinkedLists. This means that
 * peek/pop/push are O(1) operations per the LinkedList implementation.
 * 
 * This specific implementation DOES NOT permit NULL arguments
 * 
 * @param <T>
 */
public class Stack<T>
{
	private LinkedList<T> list;
	
	/**
	 * Constructs an empty queue.
	 */
	public Stack()
	{
		list = new LinkedList<T>();
	}
	
	/**
	 * Pushes an element onto the stack
	 * 
	 * @param ele Element to add
	 * @throws IllegalArgumentException if client tries to add a NULL element
	 */
	public void push(T ele)
	{
		if (ele == null)
			throw new IllegalArgumentException("Tried to add a NULL element!");
		
		list.addFirst(ele);
	}
	
	/**
	 * Pops an element from the stack.
	 * 
	 * @return The removed element
	 * @throws IllegalStateException if stack is empty
	 */
	public T offer()
	{
		return list.removeFirst();
	}
	
	/**
	 * Peeks an element from the stack.
	 * 
	 * @return The peek element
	 * @throws IllegalStateException if stack is empty
	 */
	public T peek()
	{
		T ret = list.removeFirst();
		list.addFirst(ret);
		return ret;
	}
	
	/**
	 * Returns the size of the stack
	 * @return The number of contained elements
	 */
	public int size()
	{
		return list.size();
	}
}
