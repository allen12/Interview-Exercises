package exercises.linkedlist;

/**
 * Check if a linked list has a cycle.
 * 
 */
public class CycleList 
{

	private static class LinkedListNode<T>
	{
		// T data;    not actually needed for this exercise
		LinkedListNode<T> next;
	}
	
	/**
	 * Uses the "tortoise and the hare" technique. One pointer moves
	 * two links at once, the other only moves once. If either pointer
	 * is ever null, then we have reached the end of the list, but
	 * if they are equal then we have a cycle.
	 * 
	 * @param head
	 * @return
	 */
	public static <T> boolean checkForCycles(LinkedListNode<T> head)
	{
		LinkedListNode<T> first = head;
		LinkedListNode<T> second = head;
		
		while (first != null && second != null)
		{
			if (first.next == null) // Reached end of list
				return false;
			
			first = first.next.next;
			second = second.next;
			
			if (first != null && first == second)
				return true;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) 
	{
		LinkedListNode<Integer>[] d = new LinkedListNode[5];
		for (int i = 0; i < d.length; i++)
			d[i] = new LinkedListNode<Integer>();
		for (int i = 0; i < d.length - 1; i++)
			d[i].next = d[i+1];
		
		// not a cycle
		System.out.println(checkForCycles(d[0]));
		
		// create a cycle
		d[d.length-1].next = d[d.length - 3];
		System.out.println(checkForCycles(d[0]));
	}

}
