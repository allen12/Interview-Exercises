package exercises.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, return all possible partitions of the string in its orginal
 * order such that each partition is itself a palindrome.
 *
 * For example, calling the function on "aabaa" returns ->
 * [["a", "a", "b", "a", "a"], ["a", "a", "b", "aa"], ["a", "aba", "a"],
 *  ["aa", "b", "a", "a"], ["aa", "b", "aa"], ["aabaa"]]
 */
public class PalindromePartition
{

    private static List<List<String>> palindromePartition(String str)
    {
        List<List<String>> lst = new ArrayList<>();
        palindromePartition_aux(lst, new ArrayList<String>(), str, 0);
        return lst;
    }

    /**
     * Adds palindrome partitions to the list, starting from index n in String str
     *
     * @param list the return result, which partitions will be appended to
     * @param partitions The partial partitions list
     * @param str Full string to partition, never changes
     * @param n pointer to the index of the string to begin at
     */
    private static void palindromePartition_aux(List<List<String>> list, List<String> partitions,
                                                String str, int n)
    {
        // Base case: if the current pointer has traversed the entire string, then
        // the partitions have covered the entire string and is a valid partition
        if (n == str.length())
        {
            list.add(new ArrayList<>(partitions));
            return;
        }

        // Recursive case: For each palindrome of any length starting from the pointer,
        // add that substring to the partitions list and recurse on the rest of the string
        for (int i = n; i < str.length(); i++)
        {
            String substring = str.substring(n, i+1);
            if (Palindrome.isPalindrome_iter(substring))
            {
                // If substring is a palindrome, add it as a possible partition and recurse
                // on the remainder of the string
                partitions.add(substring);

                palindromePartition_aux(list, partitions, str, i+1);

                // After recursive call, remove the added substring from the partial list in
                // preparation for the next
                partitions.remove(partitions.size() - 1);
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println(palindromePartition("aabaa"));
    }
}
