/**
 * TODO: Add your file header
 * Name:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * 
 * 2-4 sentence file description here
 */

import java.util.AbstractList;

/** 
 * TODO: Add class header here 
 */
public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
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
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	/**
	 * Default constructor for new lists
	 */
	public MyLinkedList() {
		// Set up head and tail dummy nodes
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
	 * @return number of nodes in list
	 */
	@Override
	public int size() {
		// Simply return the size member
		return this.size;
	}

	/**
	 * Finds node at specified index and returns its data
	 * 
	 * @param index - index corresponding to node data to be returned
	 * @return node data at index parameter
	 */
	@Override
	public E get(int index) {
		// Simply return data at Nth node using helper method
		return this.getNth(index).getElement();
	}

	/**
	 * Adds a new node to the list with specified data at specified index
	 * 
	 * @param index - index to insert new node
	 * @param data - data for new node to hold
	 */
	@Override
	public void add(int index, E data) {
		// Check for invalid index
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		// Check for invalid data
		if (data == null) {
			throw new NullPointerException();
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
	 * @param data - data for new node to be added
	 * @return true once new node is appended
	 */
	public boolean add(E data) {
		// Check for invalid data
		if (data == null) {
			throw new NullPointerException();
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
	 * @param index - index for data to be changed
	 * @param data - data for node to be changed to
	 */
	public E set(int index, E data) {
		// Check for invalid index
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		// Check for null data
		if (data == null) {
			throw new NullPointerException();
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
	 * @param index - index of node to be removed
	 */
	public E remove(int index) {
		// Check for invalid index
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
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

		// Return data of removed node
		return removedData;
	}

	/**
	 * Clears the list
	 */
	public void clear() {
		// Iterate while the list is not empty removing nodes
		while (!this.isEmpty()) {
			// Remove the first node
			this.remove(0);
			
			// Update size 
			this.size--;
		}
	}

	/**
	 * Checks if list is empty
	 * 
	 * @return true if the list is empty, false if the list is not empty
	 */
	public boolean isEmpty() {
		// If the node following head is tail, then we haven't added any 
		// elements yet
		return head.getNext() == tail;
	}

	/**
	 * 
	 * @param index - index for node to be returned
	 * @return node at specified index argument
	 */
	protected Node getNth(int index) {
		// Check for index out of bounds
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
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