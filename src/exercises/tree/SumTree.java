package exercises.tree;

import datastructures.BinarySearchTree;

/**
 * Determine whether a BST is a sum tree
 * (child nodes add up to parent nodes)
 */
public class SumTree 
{

	public static boolean isSumTree(BinarySearchTree<Integer> bst)
	{
		// Base case: The empty or leaf tree is a sum tree
		if (bst.isEmpty() || (bst.hasLeft() == false && bst.hasRight() == false))
			return true;
		
		int sum = bst.hasLeft() ? bst.getLeft().getValue() : 0;
		sum += bst.hasRight() ? bst.getRight().getValue() : 0;
		
		if (sum != bst.getValue())
			return false;
		
		if (bst.hasLeft() == true && isSumTree(bst.getLeft()) == false)
			return false;
		if (bst.hasRight() == true && isSumTree(bst.getRight()) == false)
			return false;
		
		return true;
	}
	
	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(55);
		bst.add(-10);
		bst.add(65);
		bst.add(-30);
		bst.add(20);
		bst.add(65);

		// Should print true
		System.out.println(isSumTree(bst));
		
		bst.remove(20);

		// Should print false
		System.out.println(isSumTree(bst));
	}

}
