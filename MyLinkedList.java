
/**
 * Name: Caleb Davis
 * Email: cjd001@ucsd.edu
 * Sources used: None
 * 
 * This file contains the MyLinkedList class: a generic doubly linked
 * list class, self-containing all method implementations. MyLinkedList also 
 * contains the Node class for impelmenting the doubly linked list. 
 */

import java.util.AbstractList;

/**
 * MyLinkedList is a doubly linked list implementation using two sentinel nodes;
 * one for the head and one for the tail. This class includes an inner Node
 * class for implementation. Instance variables include the list size, the head
 * of the list, and the tail of the list.
 */
public class MyLinkedList<E> extends AbstractList<E> {
    // List size, sentinal head, sentinel tail
    int size;
    Node head;
    Node tail;

    // Constants for errors
    static final String INDEX_ERROR = "Index out of bounds.";
    static final String NULL_POINTER_ERROR = "Null data not valid.";

    /**
     * A Node class for holding linked list data. Includes getters and setters
     * for internal use. Instance variables include the generic data to be held
     * in each node, a reference to the next node, a reference to the previous
     * node.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * 
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * 
         * @param prev - New previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * 
         * @param next - New next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * 
         * @param element - New element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * 
         * @return The next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * 
         * @return The previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * 
         * @return This node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    /**
     * Default constructor for new lists. List begins as empty with head and
     * tail sentinel nodes pointing to each other.
     */
    public MyLinkedList() {
        // Set up head and tail sentinel nodes
        head = new Node(null);
        tail = new Node(null);

        head.setNext(tail);
        head.setPrev(null);

        tail.setNext(null);
        tail.setPrev(head);

        // Start size off at 0
        this.size = 0;
    }

    /**
     * Returns the number of nodes in the list
     * 
     * @return Number of nodes in the list
     */
    @Override
    public int size() {
        // Simply return the size member
        return this.size;
    }

    /**
     * Finds node at specified index and returns its data
     * 
     * @param index - Index corresponding to node data to be returned
     * @return Node data at index
     */
    @Override
    public E get(int index) {
        // Simply return data at Nth node using helper method
        return this.getNth(index).getElement();
    }

    /**
     * Adds a new node to the list with specified data at specified index
     * 
     * @param index - Index for new node
     * @param data  - Data for new node to hold
     */
    @Override
    public void add(int index, E data) {
        // Check for invalid index
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR);
        }

        // Check for invalid data
        if (data == null) {
            throw new NullPointerException(NULL_POINTER_ERROR);
        }

        // Create new node with new data
        Node newNode = new Node(data);

        // Create curr and prev nodes for newNode location
        Node curr, prev;

        // Check where our new is going to be inserted
        if (index == this.size) {
            // Doing an end insertion
            // Start curr at list tail
            curr = this.tail;

            // Start prev just before tail
            prev = curr.getPrev();
        }
        else {
            // Inserting somewhere else on the list
            // Start curr off using getNth helper
            curr = this.getNth(index);

            // Start prev off one before nth node
            prev = curr.getPrev();
        }

        // Relink nodes
        prev.setNext(newNode);

        newNode.setPrev(prev);
        newNode.setNext(curr);

        curr.setPrev(newNode);

        this.size++;
    }

    /**
     * Appends data to list and returns true once this is done
     * 
     * @param data - Data for new node to be added
     * @return True once new node is appended
     */
    public boolean add(E data) {
        // Check for invalid data
        if (data == null) {
            throw new NullPointerException(NULL_POINTER_ERROR);
        }

        // Boolean flag for return
        boolean nodeAdded = false;

        // Add new node to the end with specified data using other add method
        this.add(this.size, data);

        // Update boolean variable for node added
        nodeAdded = true;

        return nodeAdded;
    }

    /**
     * Sets the data in a node to a new value
     * 
     * @param index - Index for data to be changed
     * @param data  - Data for node to be changed to
     * @return Data removed from node
     */
    public E set(int index, E data) {
        // Check for invalid index
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR);
        }

        // Check for null data
        if (data == null) {
            throw new NullPointerException(NULL_POINTER_ERROR);
        }

        // Get node at index
        Node curr = this.getNth(index);
        // Store data to be removed
        E removedData = curr.getElement();
        // Set new data
        curr.setElement(data);

        // Return removed data
        return removedData;
    }

    /**
     * Removes a node from the list
     * 
     * @param index - Index of node to be removed
     * @return Data from removed node
     */
    public E remove(int index) {
        // Check for invalid index
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR);
        }

        // Store node to be removed
        Node remNode = this.getNth(index);

        // Store data to be removed
        E removedData = remNode.getElement();

        // Store next and prev from node to be removed
        Node prev = remNode.getPrev();
        Node next = remNode.getNext();

        // Remove node
        prev.setNext(next);
        next.setPrev(prev);

        // Update size
        this.size--;

        // Return data of removed node
        return removedData;
    }

    /**
     * Removed every node from the list
     */
    public void clear() {
        // Iterate while the list is not empty removing nodes
        while (!this.isEmpty()) {
            // Remove the first node
            this.remove(0);
        }
    }

    /**
     * Checks if list is empty
     * 
     * @return True if the list is empty, false if the list is not empty
     */
    public boolean isEmpty() {
        // If the node following head is tail, then we haven't added any
        // elements yet
        return head.getNext() == tail;
    }

    /**
     * Returns the node at specified index in the list
     * 
     * @param index - Index for node to be returned
     * @return Node at specified index argument
     */
    protected Node getNth(int index) {
        // Check for index out of bounds
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR);
        }

        // Begin curr at the list head.next (first node)
        Node curr = this.head.getNext();

        // Iterate over the list and progress curr until we hit desired index
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        // Return node that we landed on after reaching desired index
        return curr;
    }
}