package exercises.string;

/**
 * Print out all permutations of a string.
=
 */
public class StringPermutations 
{

	/**
	 * Prints all permutations of a string
	 * 
	 * O(n!) time complexity, since that's how many
	 * permutations there are
	 * 
	 * @param str
	 */
	public static void printPermutations(String str)
	{
		printPermutations("", str);
	}

	private static void printPermutations(String pre, String str)
	{
		// Base case: If str is empty, then all letters have been accounted for in the prefix
		if (str.isEmpty() == true)
			System.out.println(pre);
		
		// Recursive step: For each letter of the remaining string, add it
		// to the temp buffer and call printPermutations on that
		for (int i = 0; i < str.length(); i++)
			printPermutations(pre + str.charAt(i), str.substring(0, i) + str.substring(i+1));
	}
	
	public static void main(String[] args) 
	{
		printPermutations("abcdefghijk");
	}

}
