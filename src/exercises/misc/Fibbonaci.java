package exercises.misc;

/**
 * Various implementations of the fibbonaci method.
 * 
 * fib(0) = 0
 * fib(1) = 1
 * fib(2) = 1
 * fib(3) = 2
 * fib(4) = 3
 * ...
 * fib(n) = fib(n-1) + fib(n-2)
 * 
 */
public class Fibbonaci 
{

	/**
	 * Iterative implementation of fibbonaci
	 * @param n 
	 * @return
	 */
	public static int fib_iter(int n)
	{
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		
		int a = 0;
		int b = 1;
		
		for (int i = 2; i <= n; i++)
		{
			int c = a + b;
			a = b;
			b = c;
		}
		
		return b;
	}
	
	/**
	 * Recursive implementation of fibobonaci, utilizing dynamic
	 * programming and momentization to avoid repeated calculations
	 * leading to exponential time complexity.
	 * 
	 * O(2^n) --> now O(n) time
	 * 
	 * @param n
	 * @return
	 */
	public static int fib_recur(int n)
	{
		return fib_recur(n, new int[n+1]);
	}
	
	private static int fib_recur(int n, int[] ans)
	{
		// Base case: fib(0) = 0 and fib(1) = 1
		if (n == 0)
		{
			ans[0] = 0;
			return 0;
		}
		else if (n == 1)
		{
			ans[1] = 1;
			return 1;
		}
		
		// Recursive step
		if (ans[n-1] == 0)
			fib_recur(n-1, ans);
		if (ans[n-2] == 0)
			fib_recur(n-2, ans);
		
		ans[n] = ans[n-1] + ans[n-2];
		
		return ans[n];
	}
	
	public static void main(String[] args) 
	{
		for (int i = 0; i < 45; i++)
		{
			System.out.println(fib_iter(i));
			System.out.println(fib_recur(i));
		}
	}
}
