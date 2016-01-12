package datastructures;

/**
 * <p> Singly-linked basic LinkedList implementation.
 * 
 * <p> Guarantees the following:
 * 	<li> O(1) insertion and removal at head and tail of list
 * 	<li> O(n) removal by search of element
 * 
 * @param <T>
 */
public class LinkedList<T>
{
	private Node head;

	private class Node
	{
		T data;
		Node next;
		
		Node(T data)
		{
			this(data, null);
		}
		
		Node(T data, Node next)
		{
			this.data = data; this.next = next;
		}
	}
	
	/**
	 * Constructs an empty LinkedList.
	 */
	public LinkedList()
	{	/* Default constructor */	}
	
	/**
	 * Adds an element to the end (tail) of the list
	 * @param ele Element to add
	 */
	public void addLast(T ele)
	{
		if (head == null)
			head = new Node(ele);
		else
		{
			Node tmp = head;
			
			while (tmp.next != null)
				tmp = tmp.next;
			
			tmp.next = new Node(ele);
		}
	}
	
	/**
	 * Adds an element to the beginning (head) of this list
	 * @param ele Element to add
	 */
	public void addFirst(T ele)
	{
		if (head == null)
			head = new Node(ele);
		else
			head = new Node(ele, head);
	}
	
	/**
	 * Removes the first element from the list
	 * @return The removed element
	 * @throws IllegalStateException if list is empty
	 */
	public T removeFirst()
	{
		if (head == null)
			throw new IllegalStateException("List is empty!");
		else
		{
			T ret = head.data;
			head = head.next;
			return ret;
		}
	}
	
	/**
	 * Removes the last element from the list
	 * @return The removed element
	 * @throws IllegalStateException if list is empty
	 */
	public T removeLast()
	{
		if (head == null)
			throw new IllegalStateException("List is empty!");
		else
		{
			Node tmp = head;
			
			while (tmp.next != null)
				tmp = tmp.next;
			
			T ret = tmp.next.data;
			tmp.next = null;
			return ret;
		}
	}
	
	/**
	 * Removes the FIRST instance of the supplied element
	 * 
	 * @param ele Element to search for and remove
	 * @return true if an element was removed, false if nothing was done
	 */
	public boolean remove(T ele)
	{
		if (head == null)
			return false;
		else if (head.next == null)
		{
			if (head.data.equals(ele) == true)
			{
				head = null;
				return true;
			}
			else
				return false;
		}
		else
		{
			Node prev = head;
			Node curr = head.next;
			
			while (curr != null)
			{
				if (curr.data.equals(ele) == true)
				{
					prev.next = curr.next;
					return true;
				}
				
				prev = curr;
				curr = curr.next;
			}
			
			return false;
		}
	}
	
	/**
	 * Removes the FIRST instance of the supplied element
	 * 
	 * @param ele Element to search for and remove
	 * @return true if an element(s) was removed, false if nothing was done
	 */
	public boolean removeAll(T ele)
	{
		if (head == null)
			return false;
		else if (head.next == null)
		{
			if (head.data.equals(ele) == true)
			{
				head = null;
				return true;
			}
			else
				return false;
		}
		else
		{
			Node prev = head;
			Node curr = head.next;
			boolean removedAnything = false;
			
			while (curr != null)
			{
				if (curr.data.equals(ele) == true)
				{
					prev.next = curr.next;
					curr = curr.next; // Needed to keep the iteration going.
					removedAnything = true;
				}
				else
				{
					prev = curr;
					curr = curr.next;
				}
			}
			
			return removedAnything;
		}
	}
	
	/**
	 * Gets the size of the list
	 * @return The number of elements within the list
	 */
	public int size()
	{
		if (head == null)
			return 0;
		
		int size = 1;
		Node tmp = head;
		
		while ((tmp = tmp.next) != null)
			size++;
		
		return size;
	}
	
	/**
	 * Returns whether this list is empty
	 * @return true if size is list is zero
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}
}
