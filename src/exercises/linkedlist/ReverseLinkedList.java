package exercises.linkedlist;

/**
 * Reverse the contents of a linked list.
 */
public class ReverseLinkedList 
{
	
	private static class LinkedListNode<T>
	{
		T data;
		LinkedListNode<T> next;
		
		LinkedListNode(T d)
		{
			data = d;
		}
	}
	
	/**
	 * In-place reverses a LinkedList iteratively.
	 * O(n) time and O(1) extra space.
	 * 
	 * @param head Head of the input linked list
	 * @return Head of the reversed list
	 */
	public static <T> LinkedListNode<T> reverse_iter(LinkedListNode<T> head)
	{
		// the empty or singleton list is already reversed
		if (head == null || head.next == null)
			return head;
		
		LinkedListNode<T> prev = null;
		LinkedListNode<T> curr = head;
		LinkedListNode<T> next = head.next;
		
		// Traverse the linked list, reversing the next pointers for each node
		while (curr != null)
		{
			curr.next = prev;
			
			prev = curr;
			curr = next;
			next = next == null ? null : next.next;
		}
		
		return prev;
	}
	
	/**
	 * Recursively reverses a linked list.
	 * O(n) time and O(n) extra space for the recursion stack space
	 * 
	 * @param head Head of the input linked list
	 * @return Head of the reversed linked list
	 */
	public static <T> LinkedListNode<T> reverse_recur(LinkedListNode<T> head)
	{
		// Base case: The reverse of the empty or singleton list is itself
		if (head == null || head.next == null)
			return head;
		
		// Recursive step: The reverse of a linkedlist is the reverse of
		// the list excluding the first element, followed by the first element
		
		LinkedListNode<T> firstElement = head;
		head = head.next;         // Remove first element from list
		firstElement.next = null; // Unlink first element from next element
		
		// keep track of second element (the "new" first element) so we can
		// attach the first element back on again after recursively reversing
		LinkedListNode<T> secondElement = head; 
		
		head = reverse_recur(head);
		secondElement.next = firstElement;
		
		return head;
	}
	
	// helper method to print a linked list
	private static <T> void printLinkedList(LinkedListNode<T> head)
	{
		while (head != null)
		{
			System.out.println(head.data);
			head = head.next;
		}
	}
	
	public static void main(String[] args) 
	{
		LinkedListNode<Integer>[] d = new LinkedListNode[5];
		for (int i = 0; i < d.length; i++)
			d[i] = new LinkedListNode<Integer>(i);
		for (int i = 0; i < d.length - 1; i++)
			d[i].next = d[i+1];
		
		printLinkedList(reverse_iter(d[0]));
	}

}
