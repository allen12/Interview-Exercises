package exercises.array;

import java.util.Arrays;

/**
 * Implements variants of quicksort.
 * Average time complexity is O(n log n), although in the worst
 * case quicksort degrades to quadratic time.
 */
public class Quicksort 
{
	/**
	 * Implements quicksort in-place. O(1) extra space, although
	 * time complexity can degrade to O(n^2) on an already-sorted
	 * list because the chosen pivot is always the last element
	 * of the array.
	 * 
	 * @param arr Array to sort
	 */
	public static <T extends Comparable<? super T>> 
			void quicksort_inplace(T[] arr)
	{
		quicksort_inplace(arr, 0, arr.length);
	}
	
	// low and high are the indices of the array to be considered,
	// inclusive of low and exclusive of high
	private static <T extends Comparable<? super T>>
			void quicksort_inplace(T[] arr, int low, int high)
	{
		// Base case: The empty or singleton list is already sorted
		if (high <= low || Math.abs(high-low) <= 1)
			return;
		
		// Choose the pivot element to be the last element of array
		T pivot = arr[high-1];
		int n = low; // Index of the element being considered to swap
		
		for (int i = low; i < high-1; i++)
		{
			// Swap elements less than the pivot to the beginning of the array
			if (arr[i].compareTo(pivot) < 0)
				swap(arr, i, n++);
		}
		
		swap(arr, n, high-1); // Swap pivot element into the middle of the list
		
		quicksort_inplace(arr, low, n);  // Sort lesser half of array
		quicksort_inplace(arr, n+1, high); // Sort greater half of array
	}
	
	private static <T> void swap(T[] arr, int a, int b)
	{
		T temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
