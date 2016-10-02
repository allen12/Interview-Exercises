package exercises.string;

/**
 * Determine whether or not a given string is a Palindrome.
 */
public class Palindrome
{

    /**
     * Iterative solution - O(n) time and O(1) space
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome_iter(String str)
    {
        int n = str.length();
        for (int i = 0; i < n/2; i++)
        {
            if (str.charAt(i) != str.charAt(n - i - 1))
                return false;
        }

        return true;
    }

    /**
     * Recursive solution - O(n) time and O(n) space (for stack frames)
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome_recur(String str)
    {
        int n = str.length();

        // Base case: The empty string or singleton string is a palindrome
        if (n <= 1)
            return true;

        // Recursive step: Compare first and last letters for equality, then
        // check whether the rest of the string is a palindrome
        return (str.charAt(0) == str.charAt(n-1)) &&
                isPalindrome_recur(str.substring(1, n-1));
    }

    public static void main(String[] args)
    {
        String yes = "amanaplanacanalpanama";
        String no = "amanaplanacaanalpanama";
        System.out.println(isPalindrome_iter(yes));
        System.out.println(isPalindrome_recur(yes));
        System.out.println(isPalindrome_iter(no));
        System.out.println(isPalindrome_recur(no));

    }
}
