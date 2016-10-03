package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Singly-linked basic LinkedList implementation.
 * 
 * <p> Guarantees the following:
 * 	<li> O(1) insertion and removal at head of list
 * 	<li> O(n) insertion and removal at tail of list
 * 	<li> O(n) removal by search of element
 * 	<li> O(n) size inquiry, although O(1) is certainly possible
 * 
 * @param <T>
 */
public class LinkedList<T> implements Iterable<T>
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
	 * @throws IllegalArgumentException if user tries to add a NULL element
	 */
	public void addLast(T ele)
	{
		if (ele == null)
			throw new IllegalArgumentException("Cannot add NULL elements!");
		
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
	 * @throws IllegalArgumentException if user tries to add a NULL element
	 */
	public void addFirst(T ele)
	{
		if (ele == null)
			throw new IllegalArgumentException("Cannot add NULL elements!");
		
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
	 * Peeks at, but does not remove the first element from the list
	 * 
	 * @return The first element
	 * @throws IllegalStateException if list is empty
	 */
	public T peekFirst()
	{
		if (head == null)
			throw new IllegalStateException("List is empty!");
		else
			return head.data;
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

			// Handle singleton list
			if (tmp.next == null)
			{
				head = null;
				return tmp.data;
			}

			while (tmp.next.next != null)
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
		else if (head.data.equals(ele) == true)
		{
			head = head.next;
			return true;
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
	 * Removes ALL instances of the supplied element
	 * 
	 * @param ele Element to search for and remove
	 * @return true if an element(s) was removed, false if nothing was done
	 */
	public boolean removeAll(T ele)
	{
		if (head == null)
			return false;
		
		if (head.data.equals(ele) == true)
		{
			head = head.next;
		}

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
	
	/**
	 * Checks whether this list contains the specified element.
	 * @param ele Element to check for
	 * @return the evaluation of any o in the list where o.equals(ele)
	 */
	public boolean contains(T ele)
	{
		Node tmp = head;
		
		while (tmp != null)
		{
			if (tmp.data.equals(ele) == true)
				return true;
			
			tmp = tmp.next;
		}
		
		return false;
	}
	
	/**
	 * Appends another given LinkedList to the end of this one.
	 * @param other The other list to add elements from
	 */
	public void addAll(LinkedList<T> other)
	{
		for (T ele : other)
			addLast(ele);
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new Iterator<T>()
		{
			Node curr = head;
			
			public boolean hasNext()
			{
				return curr != null;
			}
			
			public T next()
			{
				if (hasNext() == false)
					throw new NoSuchElementException("Iterator has no more elements!");
				
				T ret = curr.data;
				curr = curr.next;
				
				return ret;
			}
		};
	}

	@Override
	public String toString()
	{
		if (head == null)
			return "[]";
		
		String a = "[";

		for (T ele : this)
			a += ele.toString() + ",";

		a = a.substring(0, a.length() - 1);
		a += "]";

		return a;
	}
}
