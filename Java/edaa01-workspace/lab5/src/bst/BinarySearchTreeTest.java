package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	private BinarySearchTree<Integer> myIntegerBST1;
	private BinarySearchTree<Integer> myIntegerBST2;
	private BinarySearchTree<String> myStringBST1;
	private BinarySearchTree<String> myStringBST2;
	
	
	@BeforeEach
	void setUp() {
		myIntegerBST1 = new BinarySearchTree<>();
		myIntegerBST2 = new BinarySearchTree<>((a, b) -> a.compareTo(b));
		
		myStringBST1 = new BinarySearchTree<>();
		myStringBST2 = new BinarySearchTree<>((a, b) -> a.compareTo(b));
	}

	@AfterEach
	void tearDown() {
		myIntegerBST1 = null;
		myIntegerBST2 = null;
		myStringBST1 = null;
		myStringBST2 = null;
	}

	@Test
	void testHeightAndSizeOfEmptyTree() {
		assertEquals(0, myIntegerBST1.size());
		assertEquals(0, myIntegerBST1.height());
		assertEquals(0, myIntegerBST2.size());
		assertEquals(0, myIntegerBST2.height());
		
		assertEquals(0, myStringBST1.size());
		assertEquals(0, myStringBST2.height());
		assertEquals(0, myStringBST1.size());
		assertEquals(0, myStringBST2.height());
	}
	
	@Test
	void testIntegerBSTHeight() {
		int[] numbers = {50, 10, 20, 80, 60, 70, 90};
		
		for(int number : numbers) {
			myIntegerBST1.add(number);
			myIntegerBST2.add(number);
		}
		
		assertEquals(4, myIntegerBST1.height());
		assertEquals(4, myIntegerBST2.height());
	}
	
	@Test
	void testClear() {
		int[] numbers = {50, 10, 20, 80, 60, 70, 90};
		
		for(int number : numbers) {
			myIntegerBST1.add(number);
		}
		
		assertEquals(numbers.length, myIntegerBST1.size());
		 myIntegerBST1.clear();
		 assertEquals(0, myIntegerBST1.size());
	}
	
	@Test
	void testAddOnDefaultComparator() {
		testIntegerStringAdd(myIntegerBST1, myStringBST1);
	}
	
	@Test
	void testAddOnLambdaComparator() {
		testIntegerStringAdd(myIntegerBST2, myStringBST2);
	}
	
	void testIntegerStringAdd(BinarySearchTree<Integer> itree, BinarySearchTree<String> stree) {
		// Integer adding
		assertTrue(itree.add(10));
		assertTrue(itree.add(20));
		assertEquals(2, itree.size());
		assertFalse(itree.add(10));
		assertEquals(2, itree.size());
		
		assertTrue(itree.add(30));
		assertEquals(3, itree.size());
		assertEquals(3, itree.height());
		
		// String adding
		assertTrue(stree.add("A"));
		assertTrue(stree.add("B"));
		assertEquals(2, stree.size());
		assertFalse(stree.add("A"));
		assertEquals(2, stree.size());
		
		assertTrue(stree.add("C"));
		assertEquals(3, stree.size());
		assertEquals(3, stree.height());
	}
	
	@Test
	void testHeightAndSizeOfNonEmptyTree() {
		myIntegerBST1.add(10);
		assertEquals(1, myIntegerBST1.size());
		assertEquals(1, myIntegerBST1.height());
	}
	
	@Test
	void testRebuild() {
		int[] numbers = {1, 3, 5, 7, 9, 11, 13};
		  
		  for(int n : numbers) {
			  myIntegerBST1.add(n);
			  myIntegerBST2.add(n);
		  }
		  
		  assertEquals(7, myIntegerBST1.height());
		  assertEquals(7, myIntegerBST2.height());
		  
		  myIntegerBST1.rebuild();
		  myIntegerBST2.rebuild();
		  
		  assertEquals(3, myIntegerBST1.height());
		  assertEquals(3, myIntegerBST2.height());
	}
}