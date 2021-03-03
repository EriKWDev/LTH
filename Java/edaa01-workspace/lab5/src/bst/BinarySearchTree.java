package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class BinarySearchTree<E> {
	  BinaryNode<E> root;  // Används också i BSTVisaulizer
	  int size;            // Används också i BSTVisaulizer
	  private Comparator<E> comparator;
    
	  
	  public static void main(String[] args) {
		  drawIntegerTree();
		  //drawStringTree();
		  //rebuildAndDrawTree();
	  }
	  
	  private static void rebuildAndDrawTree() {
		  	BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			
			 int[] numbers = {1, 3, 5, 7, 9, 11, 13};
			  
			  for(int n : numbers) {
				  bst.add(n);
			  }
			  
			bst.printTree();
		  
		  BSTVisualizer treeVisualizer = new BSTVisualizer("Test", 800, 600);
		  bst.rebuild();
		  treeVisualizer.drawTree(bst);
	  }
	  
	  private static void drawIntegerTree() {
		  	BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		  
		  int[] numbers = {50, 10, 20, 80, 60, 70, 90};
		  // int[] numbers = {10, 20, 30, 50, 80, 60, 70, 90};
		 
			for(int number : numbers) {
				bst.add(number);
			}
			
			 Random random = new Random();
			  
			  for(int i = 0; i < 40; i++) {
				  bst.add(random.nextInt(100));
			  }
			  
			bst.printTree();
			bst.rebuild();
		  
		  BSTVisualizer treeVisualizer = new BSTVisualizer("Test", 800, 600);
		  treeVisualizer.drawTree(bst);
	  }
	  
	  private static void drawStringTree() {
		  	BinarySearchTree<String> bst = new BinarySearchTree<>();
		  
		  String[] words = {"Hello, World!", "Java", "Erik", "alpha", "Alpha", "Beta", "A", "a", "b", "B", "Potatis", "Nils", "Kub", "Hem", "Mus", "Test", "Fler ord", "Ord"};
			
			for(String word : words) {
				bst.add(word);
			}
			
			bst.printTree();
			
		  //bst.rebuild();
		  BSTVisualizer treeVisualizer = new BSTVisualizer("Test", 800, 600);
		  treeVisualizer.drawTree(bst);
	  }
	  
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
		this.comparator = (a, b) -> ((Comparable<E>) a).compareTo(b);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.size = 0;
		this.root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, new BinaryNode<E>(x));
	}
	
	private boolean add(BinaryNode<E> parentNode, BinaryNode<E> newNode) {
		if(newNode == null) return false;
		
		if(root == null) {
			root = newNode;
			size++;
			return true;
		}
		
		int compareResult = comparator.compare(newNode.element, parentNode.element);

		if(compareResult < 0) {
			if(parentNode.left == null) {
				parentNode.left = newNode;
				size++;
				
				return true;
			}
			
			return add(parentNode.left, newNode);
			
		} else if (compareResult > 0) {
			if(parentNode.right == null) {
				parentNode.right = newNode;
				size++;
				return true;
			}
			
			return add(parentNode.right, newNode);
		}
		
		return false;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> node) {
		if (node == null) return 0;
		
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printNode(root);
	}
	
	private void printNode(BinaryNode<E> node) {
		if(node == null) return;
		
		printNode(node.left);
		System.out.println(node.element);
		printNode(node.right);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sortedList = new ArrayList<>();
		
		// Skapa en sorterad lista från roten
		toArray(root, sortedList);
		
		System.out.println(sortedList);
		
		// Bygg ett nytt träd från den sorterade listan och sätt roten till trädet
		root = buildTree(sortedList, 0, sortedList.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> node, ArrayList<E> sorted) {
		if(node == null) return;
		
		toArray(node.left, sorted);
		sorted.add(node.element);
		toArray(node.right, sorted);
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last) return null;
		
		int mid = first + (last - first)/2;
		
		BinaryNode<E> newRoot = new BinaryNode<E>(sorted.get(mid));
		
		newRoot.left = buildTree(sorted, first, mid - 1);
		newRoot.right = buildTree(sorted, mid + 1, last);
		
		return newRoot;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
