package exercises.tree;

/**
 * Find the lowest common ancestor, or the lowest-level shared
 * root node, of two nodes in a binary tree.
 * 
 * Assume that all node values in the tree are unique.
 * 
 */
public class LowestCommonAncestor 
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
	
	
	/**
	 * Finds the lowest common ancestor of two nodes in a binary tree.
	 * O(n^2) time.
	 * 
	 * @param root
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> T findLCA(BinaryTreeNode<T> root, T first, T second)
	{
		if (root == null)
			return null;
		if (root.value.equals(first) || root.value.equals(second))
			return root.value;
		
		int searchLeft = search(root.left, first, second);

		if (searchLeft == 1)
		{
			// left path contains a value, right node contains the other value
			// so this is node the LCA
			return root.value;
		}
		else if (searchLeft == 2)
		{
			// left path contains both values, so continue search on left
			return findLCA(root.left, first, second);
		}
		else
		{
			// continue search on right
			return findLCA(root.right, first, second);
		}
	}
	
	// searches the tree for how many times either element appears
	private static <T> int search(BinaryTreeNode<T> root, T first, T second)
	{
		if (root == null)
			return 0;
		
		int n = search(root.left, first, second) + search(root.right, first, second);
		
		if (root.value.equals(first) || root.value.equals(second))
			return n + 1;
		else
			return n;
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
		
		// Should print 1
		System.out.println(findLCA(root, 1, 5));
		// Should all print 3
		System.out.println(findLCA(root, 1, 4));
		System.out.println(findLCA(root, 5, 2));
		System.out.println(findLCA(root, 5, 4));
	}

}
