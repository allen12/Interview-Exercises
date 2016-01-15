package exercises.tree;

import datastructures.LinkedList;

/**
 * Construct a compact BST given its preorder traversal.
 * (This means no cheating, like making a single branch down)
 *
 */
public class PreorderConstruct 
{
	
	private static class BSTNode<T extends Comparable<? super T>>
	{
		T data;
		BSTNode<T> left;
		BSTNode<T> right;
		
		BSTNode(T d)
		{
			data = d;
		}
		
		public void printTreePreorder()
		{
			System.out.println(data);
			if (left != null)
				left.printTreePreorder();
			if (right != null)
				right.printTreePreorder();
		}
		
		public void printTreeInorder()
		{
			if (left != null)
				left.printTreePreorder();
			
			System.out.println(data);

			if (right != null)
				right.printTreePreorder();
		}
		
		public int height()
		{
			if (left == null && right == null)
				return -1;
			
			int leftHeight = left == null ? -1 : left.height();
			int rightHeight = right == null ? -1 : right.height();
			
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}
	
	public static <T extends Comparable<? super T>>
			BSTNode<T> constructBST(LinkedList<T> preorder)
	{
		if (preorder.isEmpty())
			return null;
		
		BSTNode<T> root = new BSTNode<>(preorder.removeFirst());
		
		LinkedList<T> lessThan = new LinkedList<>();
		while (preorder.isEmpty() == false)
		{
			if (preorder.peekFirst().compareTo(root.data) < 0)
				lessThan.addLast(preorder.removeFirst());
			else
				break;
		}
		
		root.left = constructBST(lessThan);
		root.right = constructBST(preorder);
		
		return root;
	}
	

	public static void main(String[] args) 
	{
		LinkedList<Integer> preorder = new LinkedList<>();
		preorder.addLast(3);
		preorder.addLast(-1);
		preorder.addLast(5);
		preorder.addLast(0);
		preorder.addLast(7);
		preorder.addLast(12);
		preorder.addLast(21);

		BSTNode<Integer> bst = constructBST(preorder);
		bst.printTreePreorder();
		bst.printTreeInorder();
		System.out.println(bst.height() == 3);
	}

}
