package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import implementation.BinarySearchTree;
import implementation.Callback;
import implementation.KeyValuePair;
import implementation.TreeIsEmptyException;
import implementation.TreeIsFullException;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	@Test
	public void testingAdd0() {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 10);
		assertEquals(0, bst.size());
	}
	@Test
	public void testingAddandIncreaseTreeSize() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 10);
		 bst.add("a", 10);
		 bst.add("b", 120);
		 bst.add("c", 5);


	     assertEquals(3, bst.size());

	}
	@Test
	public void testingTreeIsFullException() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 5);
		 bst.add("a", 10);
		 bst.add("b", 120);
		 bst.add("c", 5);
		 bst.add("d", 1);
		 bst.add("e", 53);
		 
		 assertEquals(5, bst.size());
	     assertThrows(TreeIsFullException.class, () -> bst.add("r", 5));

	}
	@Test
	public void testingAddingKeyValueDoesntIncreaseSize() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 5);
		 bst.add("a", 10);
		 bst.add("b", 120);
		 
		 bst.add("a", 20);
		 assertEquals(2, bst.size());
		 assertTrue(20 == bst.find("a").getValue());
		
	}
	@Test
	public void testingToStringEmpty() {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 5);
		String expected = "EMPTY TREE";
		assertEquals(bst.toString(), expected);
	}
	@Test
	public void testingToStringNotEmpty() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 5);
		 bst.add("a", 10);
		 bst.add("b", 120);
		 bst.add("c", 5);
		 
		 String expected = "{a:10}{b:120}{c:5}";
		 assertEquals(expected, bst.toString());

	}
	@Test
	public void testingIsEmpty() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 5);
		assertTrue(bst.isEmpty());
		bst.add("a", 10);
		bst.add("b", 120);
		assertFalse(bst.isEmpty());
	}
	@Test
	public void testingIsFull() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 3);
		bst.add("a", 10);
		bst.add("b", 120);
		
		assertFalse(bst.isFull());
		bst.add("c", 5);
		
		assertTrue(bst.isFull());
	}
	@Test
	public void testingGetMinMaxKeyValue() throws TreeIsFullException, TreeIsEmptyException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 3);
		bst.add("a", 10);
		bst.add("b", -12);	
		bst.add("c", -12);
		
		KeyValuePair<String, Integer> max = bst.getMaximumKeyValue();
		KeyValuePair<String, Integer> min = bst.getMinimumKeyValue();
		
		String expected1 = max.getKey() +  "" + max.getValue();
		String expected2 = min.getKey() +  "" + min.getValue();
		
		
		assertEquals(expected1, "c-12");
		assertEquals(expected2, "a10");
	}
	@Test
	public void testingFindExistingKey() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		bst.add("d", 1);
		bst.add("e", 53);
		
		String expected = bst.find("a").getKey() + bst.find("a").getValue();
		assertEquals("a10", expected);
		
		String expected2 = bst.find("d").getKey() + bst.find("d").getValue();
		assertEquals("d1", expected2);
	}
	
	@Test
	public void testingNonExistantKey() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		
        KeyValuePair<String, Integer> result = bst.find("p");
		assertNull(result);
	}
	@Test
	public void testingDeleteRoot() throws TreeIsFullException, TreeIsEmptyException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);       
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		bst.add("d", 1);
		
		bst.delete("a");
		
		assertEquals(3, bst.size());
		assertNull(bst.find("a"));
		
		String expected = "{b:120}{c:5}{d:1}";
		assertEquals(expected, bst.toString());
	}
	@Test
	public void testingDeleteALeaf() throws TreeIsFullException, TreeIsEmptyException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);       
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		bst.add("d", 1);
		
		bst.delete("c");
		
		assertEquals(3, bst.size());
		assertNull(bst.find("c"));
		
		String expected = "{a:10}{b:120}{d:1}";
		assertEquals(expected, bst.toString());
	}
	@Test
	public void testingDeleteNonExistantKey() throws TreeIsFullException, TreeIsEmptyException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);       
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		bst.add("d", 1);
		
		bst.delete("p");
		
		assertEquals(4, bst.size());
		assertNull(bst.find("p"));
		
		String expected = "{a:10}{b:120}{c:5}{d:1}";
		assertEquals(expected, bst.toString());
	}
	@Test
	public void testingDeleteFromAnEmptyTree() {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);
		assertThrows(TreeIsEmptyException.class, () -> bst.delete("a"));
	}
	@Test
	public void testingProcessInOrder() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6); 
		
		StringBuilder result = new StringBuilder();
		Callback<String, Integer> callback = (key, value) -> result.append(key);
		
		bst.processInorder(callback);
	    assertEquals("", result.toString());
	    
		bst.add("c", 3);
	    bst.add("b", 2);
	    bst.add("a", 1);
	    bst.add("e", 5);
	    bst.add("d", 4);
	       
	    bst.processInorder(callback);
	    assertEquals("abcde", result.toString());
	}
	@Test
	public void testingSubTree() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 6);       
		bst.add("a", 10);
		bst.add("b", 120);
		bst.add("c", 5);
		bst.add("d", 1);
		bst.add("e", 12);
		bst.add("f", 3);
		
		BinarySearchTree<String, Integer> subTree = bst.subTree("b", "e" );
		assertEquals("{b:120}{c:5}{d:1}{e:12}", subTree.toString());
	}
	@Test
    public void testSubTreeWithNoMatchingKeys() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20);       
		bst.add("one", 1 );
		bst.add("two", 2);
		bst.add("three", 3);
		bst.add("four", 4);
		bst.add("five", 5);
		bst.add("six", 6);

        BinarySearchTree<String, Integer> subTree = bst.subTree("nine", "eleven");
        assertEquals("EMPTY TREE", subTree.toString());
    }
	@Test
    public void testSubTreeWithOneElement() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20);       
		bst.add("one", 1 );
		bst.add("two", 2);
		bst.add("three", 3);
		bst.add("four", 4);
		bst.add("five", 5);
		bst.add("six", 6);

        BinarySearchTree<String, Integer> subTree = bst.subTree("two", "two");
        assertEquals("{two:2}", subTree.toString());
    }
	@Test
	public void testingGetLeavesValuesEmptyTree() {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20); 
		TreeSet<String> expectedLeavesValues = new TreeSet<>();
	    TreeSet<Integer> actualLeavesValues = bst.getLeavesValues();

	    assertEquals(expectedLeavesValues, actualLeavesValues);

	}
	@Test
	public void testingGetLeavesEmptyTreeOneNode() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20);       	   
		TreeSet<Integer> expectedLeavesValues = new TreeSet<>();
	    TreeSet<Integer> actualLeavesValues = bst.getLeavesValues();
	    bst.add("a", 1);
	    actualLeavesValues = bst.getLeavesValues();
	    expectedLeavesValues.add(1);
	    assertEquals(expectedLeavesValues, actualLeavesValues);
	}
	@Test
	public void testingGetLeavesTwoNode() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20);       	   
		TreeSet<Integer> expectedLeavesValues = new TreeSet<>();
	    TreeSet<Integer> actualLeavesValues = bst.getLeavesValues();
	    bst.add("a", 1);
	    bst.add("b", 2);
	    actualLeavesValues = bst.getLeavesValues();
	    expectedLeavesValues.add(2);
	    assertEquals(expectedLeavesValues, actualLeavesValues);
	}
	
	@Test
	public void testGetLeavesValues2() throws TreeIsFullException {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, 20); 
	    // Test with an empty tree
	    assertEquals(new TreeSet<>(), bst.getLeavesValues());

	    // Test with a tree with only one node
	    bst.add("A", 1);
	    assertEquals(new TreeSet<>(Arrays.asList(1)), bst.getLeavesValues());

	    // Test with a tree with only left nodes
	    bst.add("B", 2);
	    bst.add("C", 3);
	    bst.add("D", 4);
	    bst.add("E", 5);
	    assertEquals(new TreeSet<>(Arrays.asList(5)), bst.getLeavesValues());

	    // Test with a tree with only right nodes
	    bst = new BinarySearchTree<>(String.CASE_INSENSITIVE_ORDER, 5);
	    bst.add("E", 5);
	    bst.add("D", 4);
	    bst.add("C", 3);
	    bst.add("B", 2);
	    assertEquals(new TreeSet<>(Arrays.asList(2)), bst.getLeavesValues());

	    // Test with a tree with both left and right nodes
	    bst = new BinarySearchTree<>(String.CASE_INSENSITIVE_ORDER, 7);
	    bst.add("D", 4);
	    bst.add("B", 2);
	    bst.add("A", 1);
	    bst.add("C", 3);
	    bst.add("F", 6);
	    bst.add("E", 5);
	    bst.add("G", 7);
	    assertEquals(new TreeSet<>(Arrays.asList(1,3,5,7)), bst.getLeavesValues());
	}
	
}
