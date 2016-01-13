package exercises.misc;

public class IntegerStringConversions 
{

	/**
	 * Converts a string to an int, taking into account any negative sign
	 * 
	 * @param str String to convert
	 * @return The corresponding integer
	 * @throws NumberFormatException if string cannot be fully parsed
	 */
	public static int parseInt(String str)
	{
		if (str.length() == 0)
			throw new NumberFormatException("Invalid string!");
		
		boolean negative = false;
		
		if (str.charAt(0) == '-')
		{
			negative = true;
			str = str.substring(1);
			
			if (str.length() == 0)
				throw new NumberFormatException("Invalid string!");
		}
		
		char c = str.charAt(0);
		str = str.substring(1);
		if (c < '0' || c > '9')
			throw new NumberFormatException("Invalid string!");
		
		int ans = c - '0';
		
		for (char ch : str.toCharArray())
		{
			if (ch < '0' || ch > '9')
				throw new NumberFormatException("Invalid string!");
			
			ans *= 10;
			ans += ch - '0';
		}
		
		return negative ? -ans : ans;
	}
	
	/**
	 * Converts an integer to a string
	 * 
	 * @param n
	 * @return
	 */
	public static String toStr(int n)
	{
		StringBuilder sb = new StringBuilder();
		boolean negative = false;
		
		//handle negative
		if (n < 0)
		{
			negative = true;
			n = -n;
		}
		
		while (n > 0)
		{
			sb.append(n % 10);
			n /= 10;
		}
		
		return negative ? sb.append("-").reverse().toString() : sb.reverse().toString();
	}

}
