package datastructures;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p> Binary search tree implementation, an extension of a binary tree in which
 * the ordering relation is as follows:
 * 	<li> The left child (if any) is <i> less than</i> its parent
 * 	<li> The right child (if any) is <i> greater than or equal to</i> its parent
 * 
 * <p> Because there is an ordering relation, comparability of the generic
 * type is required. In addition, this BST prohibits NULL elements.
 * 
 * <p> Performance-wise, this implementation offers:
 * 	<li> O(log n) on average for insertion, removal, and traversal
 * 	<li> O(n) for the worst case, since this implementation is not self-balancing
 * 
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>>
{
	private Node root;
	
	private class Node
	{
		private T data;
		private Node left;
		private Node right;
		
		Node(T ele)
		{
			data = ele;
		}
		
		public void add(T ele)
		{
			if (ele.compareTo(data) < 0)
			{
				if (left == null)
					left = new Node(ele);
				else
					left.add(ele);
			}
			else
			{
				if (right == null)
					right = new Node(ele);
				else
					right.add(ele);
			}
		}
		
		public boolean remove(T ele, Node parent)
		{
			if (data.equals(ele) == true)
			{
				// This is the node we're looking for
				
				if (left != null && right != null)
				{
					// This node is not a leaf or part of a branch, so
					// replace with the min of the right branch and
					// recursively delete the duplicate value
					data = right.min();
					right.remove(data, this);
				}
				else if (parent.left == this)
				{
					// This node is a leaf node or is part of a branch
					// going in the left direction
					parent.left = (this.left == null) ? null : this.left;
				}
				else
				{
					// This node is a leaf node or is part of a branch
					// going in the right direction
					parent.right = (this.right == null) ? null : this.right;
				}
				
				return true;
			}
			else if (ele.compareTo(data) < 0)
			{
				return (left != null) ? left.remove(ele, this) : false;
			}
			else
			{
				return (right != null) ? right.remove(ele, this) : false;
			}
		}
		
		public T min()
		{
			if (left == null)
				return data;
			else
				return left.min();
		}
		
		public T max()
		{
			if (right == null)
				return data;
			else
				return right.max();
		}
		
		public int size()
		{
			int n = (left == null) ? 0 : left.size();
			n += (right == null) ? 0 : right.size();
			
			return n+1;
		}
		
		public boolean contains(T ele)
		{
			if (data.equals(ele) == true)
				return true;
			else if (ele.compareTo(data) < 0)
				return (left == null) ? false : left.contains(ele);
			else
				return (right == null) ? false : right.contains(ele);
		}
	}
	

	
	/**
	 * Constructs a new BST with an empty BST
	 */
	public BinarySearchTree()
	{

	}
	
	/**
	 * Constructs a new BST internally with a specified Node
	 * @param root Node to make the new object's root
	 */
	private BinarySearchTree(Node root)
	{
		this.root = root;
	}
	
	/**
	 * Adds an element to the binary search tree
	 * 
	 * @param ele Element to add
	 * @throws IllegalArgumentException if passed-in element is NULL
	 */
	public void add(T ele)
	{
		if (ele == null)
			throw new IllegalArgumentException("Tried to add a NULL element!");
		
		if (root == null)
			root = new Node(ele);
		else
			root.add(ele);
	}
	
	/**
	 * Removes the first instance of an element encountered
	 * @param ele Element to remove
	 * @return true is an element was removed, false otherwise
	 */
	public boolean remove(T ele)
	{
		if (root == null)
			return false;
		
		if (root.data.equals(ele) == true)
		{
			// Root has what we're looking for, so check if it has
			// a left and right branches
			if (root.left != null && root.right != null)
			{
				// Follow the same logic as in Node class above
				root.data = root.right.min();
				root.right.remove(root.data, root);
			}
			else
			{
				// Root is either a singleton or has only one branch,
				// so simply detach it
				root = (root.left == null) ? root.right : root.left;
			}
			
			return true;
		}
		else
			return root.remove(ele, null);
	}
	
	/**
	 * Returns the maximum element of this BST.
	 * 
	 * @return Greatest element contained
	 * @throws IllegalStateException if tree is empty
	 */
	public T max()
	{
		if (root == null)
			throw new IllegalStateException("Tree is empty!");
		else
			return root.max();
	}
	
	/**
	 * Returns the minimal element of this BST.
	 * 
	 * @return Minimum element contained
	 * @throws IllegalStateException if tree is empty
	 */
	public T min()
	{
		if (root == null)
			throw new IllegalStateException("Tree is empty!");
		else
			return root.min();
	}
	
	/**
	 * Checks whether this BST has any elements contained
	 * @return true if this BST is empty, false if it contains elements
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * Checks whether this BST has a left subtree
	 * @return true if this BST has a left node
	 * @throws IllegalStateException if this tree is empty
	 */
	public boolean hasLeft()
	{
		if (root == null)
			throw new IllegalStateException("Tree is empty!");
		else
			return root.left != null;
	}
	
	/**
	 * Checks whether this BST has a right subtree
	 * @return true if this BST has a right node
	 * @throws IllegalStateException if this tree is empty
	 */
	public boolean hasRight()
	{
		if (root == null)
			throw new IllegalStateException("Tree is empty!");
		else
			return root.right != null;
	}
	
	/**
	 * Obtain the left subtree
	 * @return The left BST, if any
	 * @throws IllegalStateException if this tree is empty or has no left subtree
	 */
	public BinarySearchTree<T> getLeft()
	{
		if (hasLeft() == false) // hasLeft() handles the case where this tree is empty
			throw new IllegalStateException("Tree is empty!");
		else
			return new BinarySearchTree<T>(root.left);
	}
	
	/**
	 * Obtain the right subtree
	 * @return The right BST, if any
	 * @throws IllegalStateException if this tree is empty or has no right subtree
	 */
	public BinarySearchTree<T> getRight()
	{
		if (hasRight() == false) // hasRight() handles the case where this tree is empty
			throw new IllegalStateException("Tree is empty!");
		else
			return new BinarySearchTree<T>(root.right);
	}
	
	/**
	 * Returns the number of elements contained within this tree
	 * @return Size of this BST
	 */
	public int size()
	{
		if (root == null)
			return 0;
		else
			return root.size();
	}
	
	/**
	 * Check whether this tree contains the specified element
	 * 
	 * @param ele Element to search for
	 * @return true if this BST contains it, false if not
	 */
	public boolean contains(T ele)
	{
		if (root == null)
			return false;
		else
			return root.contains(ele);
	}
	
	/**
	 * Performs an inorder traversal on this BST
	 * 
	 * @param func Functional interface to apply with the input data
	 */
	public void inOrderTraversal(Consumer<T> func)
	{
		if (root == null) // Empty tree -> empty traversal
			return;
		
		if (hasLeft() == true)
			getLeft().inOrderTraversal(func);
		
		func.accept(root.data);
		
		if (hasRight() == true)
			getRight().inOrderTraversal(func);
	}
	
	
	/**
	 * Performs an inorder traversal on this BST
	 * 
	 * @param func Functional interface to apply with the input data
	 * @return A LinkedList of the return results from the function, in order
	 *  of the inorder traversal
	 */
	public <R> LinkedList<R> inOrderTraversal(Function<T, R> func)
	{
		LinkedList<R> results = new LinkedList<>();
		
		if (root == null) // Empty tree -> empty traversal
			return results;
		
		if (hasLeft() == true)
			results.addAll(getLeft().inOrderTraversal(func));
		
		results.addLast(func.apply(root.data));
		
		if (hasRight() == true)
			results.addAll(getRight().inOrderTraversal(func));
		
		return results;
	}
}
