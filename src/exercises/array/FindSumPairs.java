package exercises.array;

import datastructures.HashTable;
import datastructures.LinkedList;

/**
 * Find pairs in an integer array whose sum is equal to another integer.
 */
public class FindSumPairs 
{
	
	private static class Pair
	{
		int a;
		int b;
		
		Pair(int aa, int bb)
		{
			a = aa; b = bb;
		}
	}
	
	/**
	 * Inserts elements into a hash table. Then, for each element x,
	 * look for an element target-x in the hash table. If that element exists,
	 * then it is a pair.
	 * 
	 * Requires O(n) time and O(n) extra space.
	 * 
	 * @param arr Array to examine
	 * @param target Target sum
	 * @return List of all pairs
	 */
	public static LinkedList<Pair> findPairsThatSumToTarget(int[] arr, int target)
	{
		final int PLACEHOLDER = -1; // value for not used values in hash table
		
		LinkedList<Pair> ans = new LinkedList<>();
		HashTable<Integer, Integer> tbl = new HashTable<>();
		
		for (int i : arr)
			tbl.put(i, PLACEHOLDER);
		
		for (int i : arr)
		{
			if (tbl.contains(target - i) == true)
				ans.addFirst(new Pair(i, target-i));
		}
		
		return ans;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {-32, -30, -28, -27, -25, -22, -19, -13, -9,
				-8, -4, -1, 1, 2, 4, 5, 9, 11, 13, 16, 19, 21, 24, 27};
		
		for (Pair p : findPairsThatSumToTarget(arr, 10))
			System.out.println(p.a + ", " + p.b);
	}
}
