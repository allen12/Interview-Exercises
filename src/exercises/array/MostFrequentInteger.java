package exercises.array;

import datastructures.HashTable;

/**
 * Find the most frequent integer in an array.
 * If there are ties, return just one.
 */
public class MostFrequentInteger 
{
	
	/**
	 * This solution creates a hash table to map each integer to
	 * its frequency in the array.
	 * 
	 * Takes optimal O(n) time, but also O(n) space.
	 * 
	 * @param arr Array to examine
	 * @return Most common integer
	 */
	public static int mostFrequentInteger(int[] arr)
	{
		// Map the integer to its frequency in the array
		HashTable<Integer, Integer> tbl = new HashTable<>();
		
		for (int i : arr)
		{
			if (tbl.get(i) == null)
				tbl.put(i, 1);
			else
				tbl.put(i, tbl.get(i) + 1);
		}
		
		int ans = -1;
		int freq = -1;
		for (HashTable<Integer, Integer>.Entry entry : tbl)
		{
			if (entry.value > freq)
			{
				ans = entry.key;
				freq = entry.value;
			}
		}

		return ans;
	}
	
	/**
	 * This solution sorts the array and examines the resulting array
	 * for the longest string of the same number.
	 * 
	 * Takes O(n log n) time, but only O(1) extra space.
	 * 
	 * @param arr Array to examine
	 * @return Most frequent integer
	 */
	public static int mostFrequentInteger2(Integer[] arr)
	{
		Quicksort.quicksort_inplace(arr);
		
		Integer mostFreq = -1;
		int maxCount = 0;
		
		Integer currentNumber = null;
		int count = 0;

		for (Integer i : arr)
		{
			if (i != currentNumber)
			{
				if (count > maxCount)
				{
					mostFreq = currentNumber;
					maxCount = count;
				}
				
				count = 1;
				currentNumber = i;
			}
			else
				count++;
		}
		
		return mostFreq;
	}

	public static void main(String[] args) 
	{
		int[] testArr = {-4, 2, 1, 5, 3, 3, 1, 5, -4, 3, -4, 2, 3, 1, 3, 5, 3};
		System.out.println(mostFrequentInteger(testArr));
		
		Integer[] testArr2 = {-4, 2, 1, 5, 3, 3, 1, 5, -4, 3, -4, 2, 3, 1, 3, 5, 3};
		System.out.println(mostFrequentInteger2(testArr2));
	}

}
