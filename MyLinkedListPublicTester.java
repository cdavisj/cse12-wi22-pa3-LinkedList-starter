
/**
 * @author Qingyang Hu, Jianwei Gong
 * Date: 01/17/2022
 * Description: This is a public tester file for PA3. It contains test cases 
 * for the MyLinkedList and Node classes.
 */

import org.junit.*;

import static org.junit.Assert.*;

public class MyLinkedListPublicTester {
    private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = { "CSE 12", "Paul Cao", "Christine Alvarado" };

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and a
     * list with several entries (0,1,2)
     */
    @Before
    public void setUp() {
        emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
    }

    /**
     * One way to test the code is to manually populate the list to ensure the
     * internal state is correct. In this way, potential errors in add() won't
     * cause failures for other methods. However, for you own custom tester,
     * feel free to populate the list in whatever way you want.
     */
    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 = this.threeStringList.new Node(
                this.strData[0]);
        MyLinkedList<String>.Node node1 = this.threeStringList.new Node(
                this.strData[1]);
        MyLinkedList<String>.Node node2 = this.threeStringList.new Node(
                this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }

    // ------------ Node ---------------

    /** Test the node constructor */
    @Test
    public void testNodeConstructor() {
        // Create an outer class in order to instantiate the inner class
        MyLinkedList<String>.Node node = this.threeStringList.new Node(null);
        MyLinkedList<String>.Node firNode = this.threeStringList.new Node(
                "hello");
        MyLinkedList<String>.Node secNode = this.threeStringList.new Node(
                "hello2");
        // After a node is initialized, prev and next should points to null
        assertNull(node.next);
        assertNull(node.prev);
        assertNull(node.data);
        assertEquals("Check if the data is correctly assigned to node", "hello",
                firNode.data);
        assertEquals("Check if the data is correctly assigned to node",
                "hello2", secNode.data);
    }

    @Test
    public void testNodeSetPrev() {
        MyLinkedList<Integer>.Node node = this.emptyIntegerList.new Node(100);
        MyLinkedList<Integer>.Node prevNode = this.emptyIntegerList.new Node(0);
        node.setPrev(prevNode);
        assertEquals("The prev pointer should be updated", prevNode, node.prev);
    }

    @Test
    public void testNodeSetNext() {
        MyLinkedList<Integer>.Node node = this.emptyIntegerList.new Node(30);
        MyLinkedList<Integer>.Node nextNode = this.emptyIntegerList.new Node(
                -40);
        node.setNext(nextNode);
        assertEquals("The next pointer should be updated", nextNode, node.next);
    }

    @Test
    public void testNodeSetElement() {
        MyLinkedList<Integer>.Node intNode = this.emptyIntegerList.new Node(
                100);
        MyLinkedList<String>.Node strNode = this.threeStringList.new Node(
                "hello");

        intNode.setElement(5);
        strNode.setElement("cse12pa3");
        assertEquals("The data within node should be updated",
                Integer.valueOf(5), intNode.data);
        assertEquals("The data within node should be updated", "cse12pa3",
                strNode.data);
        strNode.setElement(null);
        assertNull(strNode.data);
    }

    @Test
    public void testNodeGetPrev() {
        MyLinkedList<Integer>.Node intNode = this.emptyIntegerList.new Node(
                100);
        assertNull("Method getPrev should return null for a single node",
                intNode.getPrev());

        MyLinkedList<String>.Node strNode = this.threeStringList.new Node(
                "hello");
        MyLinkedList<String>.Node prevNode = this.threeStringList.new Node(
                "previous hello!");
        strNode.prev = prevNode;
        assertEquals("Test getter method after reference is assgined", prevNode,
                strNode.getPrev());
    }

    @Test
    public void testNodeGetNext() {
        MyLinkedList<Integer>.Node intNode = this.emptyIntegerList.new Node(
                100);
        assertNull("Method getNext should return null for a single node",
                intNode.getNext());

        MyLinkedList<String>.Node curNode = this.threeStringList.new Node(
                "hello");
        MyLinkedList<String>.Node nextNode = this.threeStringList.new Node(
                "next hello!");
        curNode.next = nextNode;
        assertEquals("Test getter method after reference is assgined", nextNode,
                curNode.getNext());
    }

    @Test
    public void testNodeGetElement() {
        MyLinkedList<Integer>.Node node = this.emptyIntegerList.new Node(0);
        assertEquals("Test the data within node is initialized correctly",
                Integer.valueOf(0), node.getElement());
        node.data = -1;
        assertEquals("Data within node should be updated", Integer.valueOf(-1),
                node.getElement());
    }

    // ------------ MyLinkedList() -------------

    @Test
    public void testConstructor() {
        /*
         * assertSame is used when you want to check that if two objects refer
         * to the same object
         */
        assertSame("Data stored at dummy head should be null", null,
                this.emptyIntegerList.head.data);
        assertSame("Data stored at dummy tail should be null", null,
                this.emptyIntegerList.tail.data);
        assertSame("Head should points to next", this.emptyIntegerList.tail,
                this.emptyIntegerList.head.next);
        assertSame("Next should points to head", this.emptyIntegerList.head,
                this.emptyIntegerList.tail.prev);
    }

    // ------------ add(E) -----------------

    @Test
    public void testAddEmpty() {
        this.emptyIntegerList.add(12);
        assertEquals("New node should be accessible from head",
                Integer.valueOf(12), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail",
                Integer.valueOf(12), this.emptyIntegerList.tail.prev.data);
        assertEquals("Size of the LinkedList should be updated", 1,
                this.emptyIntegerList.size);
        assertSame("Make sure the referece from head and tail are the same",
                this.emptyIntegerList.head.next,
                this.emptyIntegerList.tail.prev);
    }

    @Test
    public void testAddNotEmpty() {
        this.populateLinkedList();

        // Obtain the reference to the node before tail
        MyLinkedList<String>.Node oldLastNode = this.threeStringList.tail.prev;
        this.threeStringList.add("Gary Gillespie");

        assertEquals("Tail node should point back to the new node",
                "Gary Gillespie", this.threeStringList.tail.prev.data);
        assertEquals("Previous last node should points to the new added node",
                "Gary Gillespie", oldLastNode.next.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
    }

    // ------------ add(int, E) -----------------

    @Test
    public void testAddToEmpty() {
        this.emptyIntegerList.add(0, 12);
        assertEquals("Check head reference is updated", Integer.valueOf(12),
                this.emptyIntegerList.head.next.data);
        assertEquals("Check tail reference is updated", Integer.valueOf(12),
                this.emptyIntegerList.tail.prev.data);
        assertEquals("Check size is updated", 1, this.emptyIntegerList.size);
    }

    @Test
    public void testAddToGreaterThanSize() {
        this.populateLinkedList();
        try {
            threeStringList.add(4, "Staff");
            fail();
        }
        catch (IndexOutOfBoundsException e) {
            // Exception is successfully caught. Passing the test case.
        }
    }

    // ------------ getNth(int) -----------------

    @Test
    public void testGetNth() {
        this.populateLinkedList();
        assertSame("Check the first element is returned correctly",
                this.threeStringList.head.next, this.threeStringList.getNth(0));
        assertSame("Check the second element is returned correctly",
                this.threeStringList.head.next.next,
                this.threeStringList.getNth(1));
    }

    // ------------ get(int) -----------------

    @Test
    public void testGet() {
        this.populateLinkedList();
        assertEquals("Get should return the correct data at index 0",
                this.strData[0], this.threeStringList.get(0));
        assertEquals("Get should return the correct data at index 1",
                this.strData[1], this.threeStringList.get(1));
        assertEquals("Get should return the correct data at index 2",
                this.strData[2], this.threeStringList.get(2));
    }

    // ------------ set(int, E) -----------------

    @Test
    public void testSetHead() {
        this.populateLinkedList();
        this.threeStringList.set(0, "CSE 100");
        assertEquals("Value at index 0 should be reset", "CSE 100",
                this.threeStringList.head.next.data);
    }

    @Test
    public void testSetNull() {
        this.populateLinkedList();
        try {
            threeStringList.set(0, null);
            fail();
        }
        catch (NullPointerException e) {
            // pass
        }
    }

    // ------------ remove() -----------------

    @Test
    public void testRemoveFirstElement() {
        this.populateLinkedList();
        MyLinkedList<String>.Node node1 = this.threeStringList.head.next.next;
        String removedData = this.threeStringList.remove(0);
        assertSame("Old node at position 0 should be removed", node1,
                this.threeStringList.head.next);
        assertSame("Old node at position 0 should be removed", node1.prev,
                this.threeStringList.head);
        assertEquals("Removed node should be returned Correctly", removedData,
                "CSE 12");
    }

    @Test
    public void testRemoveLastElement() {
        this.populateLinkedList();
        MyLinkedList<String>.Node node1 = this.threeStringList.tail.prev.prev;
        this.threeStringList.remove(2);
        // We can also check whether we update the prev and next correctly.
        assertSame("The new last node should points to tail", node1.next,
                this.threeStringList.tail);
        assertSame("The tail should points to the new last node", node1,
                this.threeStringList.tail.prev);
    }

    // ------------ clear() -----------------

    @Test
    public void testClearNonEmptyList() {
        this.populateLinkedList();
        threeStringList.clear();
        assertEquals("Size should be updated", 0, threeStringList.size());
        assertTrue("LinkedList should be empty", threeStringList.isEmpty());
    }

    // ------------ size() -----------------

    @Test
    public void testSize() {
        assertEquals("An empty list should have a size of 0", 0,
                emptyIntegerList.size());
    }

    // ------------ isEmpty() -----------------

    @Test
    public void testIsEmpty() {
        this.populateLinkedList();
        assertFalse("A list with 3 elements should not be empty",
                threeStringList.isEmpty());
    }
}