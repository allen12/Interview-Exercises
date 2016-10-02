package exercises.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * HackerRank challenge: https://www.hackerrank.com/challenges/simple-text-editor
 */
public class SimpleTextEditor
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        processLines(scan, n);
    }

    private static void processLines(Scanner scan, int n)
    {
        Stack<Integer> type = new Stack<>();
        Stack<String> strings = new Stack<>();
        String str = "";

        for (int i = 0; i < n; i++) {
            String[] args = scan.nextLine().split(" ");

            // Handle undo operation - pop last operation off stacks and reverse the operation
            if (args[0].equals("4"))
            {
                if (type.pop() == 1)
                {
                    str = str.substring(0, str.length() - strings.pop().length());
                }
                else
                {
                    str += strings.pop();
                }
            }
            else if (args[0].equals("3")) // Handle print kth character of S
            {
                System.out.println(str.charAt(Integer.parseInt(args[1]) - 1));
            }
            else if (args[0].equals("2")) // Handle deletion of characters
            {
                int k = Integer.parseInt(args[1]);
                type.push(2);
                strings.push(str.substring(str.length() - k));

                str = str.substring(0, str.length() - k);
            }
            else // Handle insertion of characters
            {
                str += args[1];
                type.push(1);
                strings.push(args[1]);
            }
        }
    }
}
