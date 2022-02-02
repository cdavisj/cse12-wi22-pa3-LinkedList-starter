
/**
 * Name: Caleb Davis
 * Email: cjd001@ucsd.edu
 * Sources used: None
 * 
 * This file contains the JUnit tester for the MyLinkedList class. This tester
 * will make sure all of the edge cases are working properly for the class.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tester for MyLinkedList class. Tests all member methods' functionality to
 * ensure everything is working as expected.
 */
public class MyLinkedListCustomTester {
    // Declare tester lists
    MyLinkedList<Object> emptyList;
    MyLinkedList<Integer> intList;

    // Arbitrary number for testing
    static final int SOME_NUMBER = 5;

    // Error messages
    static final String EXPECTED_EXCEPTION = "Expected exception: ";
    static final String INDEX_EXCEPTION = "IndexOutOfBoundsException";
    static final String NULL_POINTER_EXCEPTION = "NullPointerException";

    // Invalid index
    static final int INVALID_INDEX = -1;
    // Valid index
    static final int VALID_INDEX = 0;

    /**
     * Set up lists for testing
     */
    @Before
    public void setUp() throws Exception {
        // Initialize tester lists
        emptyList = new MyLinkedList<>();
        intList = new MyLinkedList<>();

        // Populate intList
        MyLinkedList<Integer>.Node node0 = this.intList.new Node(1);
        MyLinkedList<Integer>.Node node1 = this.intList.new Node(2);
        MyLinkedList<Integer>.Node node2 = this.intList.new Node(3);

        this.intList.head.next = node0;
        node0.prev = this.intList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.intList.tail;
        this.intList.tail.prev = node2;
        this.intList.size = 3;
    }

    /**
     * Test the add method when null argument is passed
     */
    @Test
    public void testAdd() {
        try {
            // Call add on intList with null arg
            intList.add(null);

            // If exception was not thrown, the test fails
            fail(EXPECTED_EXCEPTION + NULL_POINTER_EXCEPTION);
        }
        catch (NullPointerException e) {
            // Exception is caught, so the test passes
        }

    }

    /**
     * Test the add method when null data is passed
     */
    @Test
    public void testAddWithIndexTestOne() {
        try {
            // Try to add a null element to the list
            intList.add(VALID_INDEX, null);

            // If exception was not thrown, the test fails
            fail(EXPECTED_EXCEPTION + NULL_POINTER_EXCEPTION);
        }
        catch (NullPointerException e) {
            // Exception is caught, so the test passes
        }
    }

    /**
     * Test the add method when index argument is invalid
     */
    @Test
    public void testAddWithIndexTestTwo() {
        // Test lower index out of bounds
        try {
            // Call add with iedex below loeer bound
            intList.add(INVALID_INDEX, SOME_NUMBER);

            // Fail if exception was not thrown
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // Exception caught, so the test is passed
        }

        // Test upper index out of bounds
        try {
            // Cal add with index above upper bound
            intList.add(intList.size + 1, SOME_NUMBER);

            // Fail if exception was not thrown
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // Exception caught, so the test is passed
        }
    }

    /**
     * Test the get method when index is out of bounds
     */
    @Test
    public void testGet() {
        // Test lower index out of bounds
        try {
            // Call get with index below lower bound
            intList.get(INVALID_INDEX);

            // If an exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, test passes
        }

        // Test upper index out of bounds
        try {
            // Call get with index above upper bound
            intList.get(intList.size);

            // If an exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, test passes
        }
    }

    /**
     * Test the getNth method when index is invalid
     */
    @Test
    public void testGetNth() {
        // Test lower index out of bounds
        try {
            // Call getNth with index below lower bound
            intList.getNth(INVALID_INDEX);

            // If an exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, test passes
        }

        // Test upper index out of bounds
        try {
            // Call get Nth with index above upper bound
            intList.getNth(intList.size);

            // If an exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, test passes
        }
    }

    /**
     * Test the set method with null data and index out of bounds
     */
    @Test
    public void testSet() {
        // Test set with null data
        try {
            // Try to set first node to null
            intList.set(VALID_INDEX, null);

            // If exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + NULL_POINTER_EXCEPTION);
        }
        catch (NullPointerException e) {
            // If exception was caught, this test is passed
        }

        // Test set with index out of bounds
        // Lower bound test
        try {
            // Call set with index below lower bound
            intList.set(INVALID_INDEX, SOME_NUMBER);

            // If exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, passed test
        }

        // Upper bound test
        try {
            // Call set with index above upper bound
            intList.set(intList.size, SOME_NUMBER);

            // If exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was caught, pass test
        }
    }

    /**
     * Test the remove method with invalid index
     */
    @Test
    public void testRemoveTestOne() {
        // Lower bound test
        try {
            // Call remove with index below lower bound
            intList.remove(INVALID_INDEX);

            // If exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was thrown, pass test
        }

        // Upper bound test
        try {
            // Call remove with index above upper bound
            intList.remove(intList.size);

            // If exception was not thrown, fail test
            fail(EXPECTED_EXCEPTION + INDEX_EXCEPTION);
        }
        catch (IndexOutOfBoundsException e) {
            // If exception was thrown, pass test
        }
    }

    /**
     * Test the remove method with middle elements
     */
    @Test
    public void testRemoveTestTwo() {
        // Store index and element to be removed
        int indexToRemove = 1;
        Integer elementToRemove = intList.get(indexToRemove);

        // Create variables for the removed element and surrounding nodes
        Integer removedElement;
        MyLinkedList<Integer>.Node curr, prev, next;

        // Store current and surrounding nodes
        curr = intList.getNth(indexToRemove);
        next = curr.next;
        prev = curr.prev;

        // Remove curr
        removedElement = intList.remove(indexToRemove);

        // Make sure next and prev now point to each other
        assertEquals(next.prev, prev);
        assertEquals(prev.next, next);

        // Check that the removed data is correct
        assertEquals(elementToRemove, removedElement);
    }

    /**
     * Test the clear method when list is empty
     */
    @Test
    public void testClear() {
        // Try to clear empty list
        emptyList.clear();

        // List should still be empty
        assertTrue(emptyList.isEmpty());

        // List should still have size of 0
        assertEquals(0, emptyList.size);

        // Check list head pointers
        assertEquals(emptyList.tail, emptyList.head.next);
        assertEquals(null, emptyList.head.prev);

        // Check list tail pointers
        assertEquals(emptyList.head, emptyList.tail.prev);
        assertEquals(null, emptyList.tail.next);
    }

    /**
     * Test the size method with non-empty list
     */
    @Test
    public void testSize() {
        // We stored three elements in the intList, so this is the expected size
        int expectedSize = 3;

        // Check size of intList
        assertEquals(expectedSize, intList.size());

        // Make sure size member and size function match up
        assertEquals(intList.size, intList.size());
    }
}