package exercises.string;

/**
 * Reverse a string iteratively and recursively
 */
public class ReverseString 
{

	/**
	 * Reverses a string iteratively, by swapping
	 * characters. O(n) time and O(1) space
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse_iter(String str)
	{
		char[] c = str.toCharArray();
		
		for (int i = 0; i < c.length / 2; i++)
			swap(c, i, c.length - i - 1);
		
		return String.valueOf(c);
	}
	
	private static void swap(char[] arr, int a, int b)
	{
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	/**
	 * Reverses a string recursively.
	 * O(n) time and O(n^2) extra space, split up into
	 * O(n) recursion stack space and O(n) for each stack
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse_recur(String str)
	{
		// Base case: The reverse of a singleton string
		// is itself.
		if (str.length() <= 1)
			return str;

		// The reverse of a string is
		// its last character followed by the reverse
		// of the rest of the string
		
		return new StringBuilder()
				.append(str.charAt(str.length() - 1))
				.append(reverse_recur(str.substring(0, str.length() - 1)))
				.toString();
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println(reverse_recur("dd3dss"));
	}

}
