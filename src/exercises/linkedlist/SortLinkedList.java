package exercises.linkedlist;

import datastructures.LinkedList;

/**
 * Sort a Linkedlist WITHOUT using an external data structure.
 */
public class SortLinkedList 
{

	/**
	 * Implements merge sort for linkedlists.
	 * O(n log n) time.
	 * 
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<? super T>>
			LinkedList<T> sort(LinkedList<T> list)
	{
		// Base case: Empty or singleton list is already sorted
		if (list.size() <= 1)
			return list;
		
		int n = list.size() / 2;
		LinkedList<T> first = new LinkedList<>();
		LinkedList<T> second = new LinkedList<>();
		
		// Partition list into two halves
		int i = 0;
		for (T item : list)
		{
			if (i++ < n)
				first.addFirst(item);
			else
				second.addFirst(item);
		}
		
		// Recursively sort the two halves and merge accordingly
		return merge(sort(first), sort(second));
	}
	
	// merge two sorted linked lists
	private static <T extends Comparable<? super T>>
			LinkedList<T> merge(LinkedList<T> one, LinkedList<T> two)
	{
		LinkedList<T> merged = new LinkedList<>();
		
		while (one.isEmpty() == false && two.isEmpty() == false)
		{
			T first = one.removeFirst();
			T second = two.removeFirst();
			
			if (first.compareTo(second) < 0)
			{
				merged.addLast(first);
				two.addFirst(second);
			}
			else
			{
				merged.addLast(second);
				one.addFirst(first);
			}
		}
		
		if (one.isEmpty())
			merged.addAll(two);
		if (two.isEmpty())
			merged.addAll(one);
		
		return merged;
	}
	
	
	
	public static void main(String[] args) 
	{
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 20; i++)
			list.addFirst(i);
		
		for (Integer i : sort(list))
			System.out.println(i);
	}

}
