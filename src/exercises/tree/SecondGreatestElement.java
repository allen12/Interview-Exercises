package exercises.tree;
import datastructures.BinarySearchTree;

/**
 * Finds the second-greatest element in a BST

 */
public class SecondGreatestElement 
{

	/**
	 * Returns the second-greatest element in the provided BST
	 * @param root Head of the BST
	 * @return The second-greatest element, or null if the BST
	 * is empty or contains only one element
	 */
	public static <T extends Comparable<T>> T 
		find2ndGreatestElement(BinarySearchTree<T> root)
	{
		if (root == null || root.isEmpty() == true)
			return null;
		if (root.hasRight() == false)
		{
			if (root.hasLeft() == false) // Singleton tree
				return null;
			else // Return max of the left subbranch
				return root.getLeft().max();
		}
		else // Charge down the right
		{
			return find_aux(root.getRight(), root);
		}
		
	}
	
	public static <T extends Comparable<T>> T 
		find_aux(BinarySearchTree<T> node, BinarySearchTree<T> parent)
	{
		if (node.hasRight() == true) // Charge down the right until node is the right-most node
			return find_aux(node.getRight(), node);
		else
		{
			T maxLeftSubbranch;
			if (node.hasLeft() == true)
				maxLeftSubbranch = node.getLeft().max();
			else
				return parent.getValue();
			
			if (parent.getValue().compareTo(maxLeftSubbranch) > 0)
				return maxLeftSubbranch;
			else
				return parent.getValue();
		}
	}
	
	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		System.out.println(find2ndGreatestElement(bst)); //null
		bst.add(3);
		System.out.println(find2ndGreatestElement(bst)); //null
		bst.add(-1);
		System.out.println(find2ndGreatestElement(bst)); //-1
		bst.add(7);
		System.out.println(find2ndGreatestElement(bst)); //3
		bst.add(12);
		System.out.println(find2ndGreatestElement(bst)); //7
		bst.add(-5);
		System.out.println(find2ndGreatestElement(bst)); //7
		bst.add(0);
		bst.add(21);
		
		System.out.println(find2ndGreatestElement(bst)); //12
	}

}
