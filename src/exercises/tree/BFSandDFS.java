package exercises.tree;

import datastructures.BinarySearchTree;
import datastructures.Queue;

/**
 * Print a BST using BFS and DFS
 * @author compu
 *
 */
public class BFSandDFS 
{

	/**
	 * Uses a Queue to print a BST using breadth-first-search,
	 * or in other words, print it by level.
	 * 
	 * @param bst
	 */
	public static <T extends Comparable<? super T>> 
			void printBFS(BinarySearchTree<T> bst)
	{
		if (bst.isEmpty() == true)
			return;
		
		Queue<BinarySearchTree<T>> queue = new Queue<>();
		queue.offer(bst);
		
		while (queue.size() != 0)
		{
			BinarySearchTree<T> b = queue.poll();
			System.out.println(b.getValue());
			
			if (b.hasLeft())
				queue.offer(b.getLeft());
			if (b.hasRight())
				queue.offer(b.getRight());
		}	
	}
	
	/**
	 * Uses recursion to print a BST using depth-first-search.
	 * @param bst
	 */
	public static <T extends Comparable<? super T>>
			void printDFS(BinarySearchTree<T> bst)
	{
		if (bst.isEmpty() == true)
			return;
		
		System.out.println(bst.getValue());
		
		if (bst.hasLeft())
			printDFS(bst.getLeft());
		if (bst.hasRight())
			printDFS(bst.getRight());
	}
	
	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(3);
		bst.add(-1);
		bst.add(7);
		bst.add(12);
		bst.add(-5);
		bst.add(0);
		bst.add(21);
		
		printBFS(bst);
		printDFS(bst);
	}

}
