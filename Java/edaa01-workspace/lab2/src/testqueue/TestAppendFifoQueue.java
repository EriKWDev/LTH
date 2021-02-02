package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myPrimaryIntQueue;
	private FifoQueue<Integer> mySecondaryIntQueue;

	@BeforeEach
	void setUp() {
		myPrimaryIntQueue = new FifoQueue<Integer>();
		mySecondaryIntQueue = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() {
		myPrimaryIntQueue = null;
		mySecondaryIntQueue = null;
	}
	
	@Test
	void assertSecondaryIsEmpty() {
		assertEquals(0, mySecondaryIntQueue.size());
		assertTrue(mySecondaryIntQueue.isEmpty());
	}

	@Test
	void testAppendTwoEmptyQueues() {
		myPrimaryIntQueue.append(mySecondaryIntQueue);
		assertEquals(0, myPrimaryIntQueue.size());
		assertSecondaryIsEmpty();
	}
	
	@Test
	void testAppendSameEmptyQueue() {
		assertThrows(IllegalArgumentException.class, () -> myPrimaryIntQueue.append(myPrimaryIntQueue));
	}
	
	@Test
	void testAppendSameQueue() {
		for(int i = 1; i <= 5; i++) {
			myPrimaryIntQueue.offer(i);
		}
		
		assertThrows(IllegalArgumentException.class, () -> myPrimaryIntQueue.append(myPrimaryIntQueue));
	}
	
	@Test
	void testAppendTwoQueuesWherePrimaryIsEmpty() {
		
		for(int i = 1; i <= 5; i++) {
			mySecondaryIntQueue.offer(i);
		}
		
		 myPrimaryIntQueue.append(mySecondaryIntQueue);
		 assertSecondaryIsEmpty();
		 Iterator<Integer> myIterator = myPrimaryIntQueue.iterator();
		 assertEquals(5, myPrimaryIntQueue.size());
		 
		 for(int i = 1; myIterator.hasNext(); i++) {
			 Integer value = myIterator.next();
			 assertEquals(Integer.valueOf(i), value);
		 }
		 
		 assertFalse(myIterator.hasNext());
		 assertThrows(NoSuchElementException.class, () -> myIterator.next());
	}
	
	@Test
	void testAppendTwoQueuesWhereSecondaryIsEmpty() {
		
		for(int i = 1; i <= 5; i++) {
			myPrimaryIntQueue.offer(i);
		}
		
		 assertEquals(5, myPrimaryIntQueue.size());
		 myPrimaryIntQueue.append(mySecondaryIntQueue);
		 assertSecondaryIsEmpty();
		 Iterator<Integer> myIterator = myPrimaryIntQueue.iterator();
		 assertEquals(5, myPrimaryIntQueue.size());
		 
		 for(int i = 1; myIterator.hasNext(); i++) {
			 Integer value = myIterator.next();
			 assertEquals(Integer.valueOf(i), value);
		 }
		 
		 assertFalse(myIterator.hasNext());
		 assertThrows(NoSuchElementException.class, () -> myIterator.next());
	}
	
	@Test
	void testAppendTwoQueues() {
		for(int i = 1; i <= 5; i++) {
			myPrimaryIntQueue.offer(i);
		}
		
		for(int i = 6; i <= 10; i++) {
			mySecondaryIntQueue.offer(i);
		}
		
		 myPrimaryIntQueue.append(mySecondaryIntQueue);
		 assertSecondaryIsEmpty();
		 Iterator<Integer> myIterator = myPrimaryIntQueue.iterator();
		 assertEquals(10, myPrimaryIntQueue.size());
		 
		 for(int i = 1; myIterator.hasNext(); i++) {
			 Integer value = myIterator.next();
			 assertEquals(Integer.valueOf(i), value);
		 }
		 
		 assertFalse(myIterator.hasNext());
		 assertThrows(NoSuchElementException.class, () -> myIterator.next());
	}
}