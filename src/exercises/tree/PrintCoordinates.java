package exercises.tree;

/**
 * Print the coordinates of eery node in a binary
 * tree, where the root is (0, 0)
 */
public class PrintCoordinates 
{
	
	private static class BinaryTreeNode<T>
	{
		T value;
		BinaryTreeNode<T> left;
		BinaryTreeNode<T> right;
		
		BinaryTreeNode(T val)
		{
			value = val;
		}
	}
	
	public static <T> void printCoordinates(BinaryTreeNode<T> root)
	{
		print_aux(root, 0, 0);
	}
	
	public static <T> void print_aux(BinaryTreeNode<T> root, int x, int y)
	{
		if (root == null)
			return;
		
		System.out.println(root.value + " at (" + x + ", " + y + ")");
		
		print_aux(root.left, x-1, y-1);
		print_aux(root.right, x+1, y-1);
	}

	public static void main(String[] args) 
	{
		/* Test tree looks like:
		 * 
		 *       3
		 *      / \
		 *     1   4
		 *    /   /
		 *   5   2
		 * 
		 */
			
		BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[5];
		for (int i = 0; i < nodes.length; i++)
			nodes[i] = new BinaryTreeNode<Integer>(i+1);
		
		nodes[2].left = nodes[0];
		nodes[2].right = nodes[3];
		nodes[0].left = nodes[4];
		nodes[3].left = nodes[1];
		
		BinaryTreeNode<Integer> root = nodes[2];

		printCoordinates(root);
	}

}
