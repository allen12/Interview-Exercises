package exercises.array;

import java.util.Arrays;

/**
 * Given an m x n matrix of 1s and 0s, count the number of distinct "islands
 * of 1s", groups of connecting 1s.
 */
public class CountIslands
{
    /**
     * O(n^2) time
     *
     * @param matrix
     * @return
     */
    private static int countIslands(int[][] matrix)
    {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        return countIslands_aux(matrix, visited);
    }

    private static int countIslands_aux(int[][] m, boolean[][] v)
    {
        int count = 0;

        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[0].length; j++)
            {
                if (v[i][j] == false && m[i][j] != 0)
                {
                    count++;
                    countIslands_aux_flood(m, v, i, j);
                }
            }
        }

        return count;
    }

    private static void countIslands_aux_flood(int[][] m, boolean[][] v, int r, int c)
    {
        if (r < 0 || r >= m.length || c < 0 || c >= m[0].length)
            return;
        if (m[r][c] == 0 || v[r][c] == true)
            return;

        v[r][c] = true;
        countIslands_aux_flood(m, v, r+1, c);
        countIslands_aux_flood(m, v, r-1, c);
        countIslands_aux_flood(m, v, r, c+1);
        countIslands_aux_flood(m, v, r, c-1);
    }

    public static void main(String[] args)
    {
        int[][] matrix = {{1, 1, 0, 1, 1, 1, 1},
                          {1, 1, 1, 0, 0, 1, 1},
                          {1, 0, 0, 1, 0, 1, 0},
                          {0, 0, 1, 0, 0, 0, 1},
                          {0, 0, 0, 0, 1, 0, 1}};
        System.out.println(countIslands(matrix));
    }
}
