package utilities;

public class LinkedList<ELEMENT> {

	// Data Members
	// ------------
	private Node<ELEMENT> front;
	private int size;

	// Constructor
	// -----------
	/**
	 * Creates an empty <i>LinkedList</i>
	 */
	public LinkedList() {
		front = null;
		size = 0;
	}
	
	public LinkedList(ELEMENT[] array) {
		setArray(array);
	}
	
	// Methods
	// -------
	/**
	 * gets the <b>number of elements</b> in <i>LinkedList</i>
	 * 
	 * @return <i>LinkedList</i>'s size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * used for determining whether <i>LinkedList</i> is empty
	 * 
	 * @return <i>true</i> if empty, <i>false</i> otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * inserts element to position index in <i>LinkedList</i> preconditions:
	 * element is of type ELEMENT index is > 0 and <= the current size
	 * 
	 * @param element
	 *            the element to insert
	 * @param index
	 *            the position to place element in <i>LinkedList</i>
	 */
	public void insert(ELEMENT element, int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException("List index out of bounds");
		}

		Node<ELEMENT> node = new Node<ELEMENT>(element);
		if (index == 0) {
			node.setNext(front);
			front = node;
		} else {
			int i = 1;
			Node<ELEMENT> previous = front;
			while (i < index) {
				previous = previous.getNext();
				i += 1;
			}
			node.setNext(previous.getNext());
			previous.setNext(node);
		}
		size += 1;
	}

	public void insertToFront(ELEMENT element) {
		insert(element, 0);
	}
	
	/**
	 * inserts an element to the end of <i>LinkedList</i>
	 * 
	 * @param element
	 *            the element to insert
	 */
	public void insertToBack(ELEMENT element) {
		insert(element, size);
	}

	/**
	 * gets the element at a given position of <i>LinkedList</i>
	 * 
	 * @param index
	 *            the position to look at
	 * @return the element at that position
	 */
	public ELEMENT elemAt(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("List index out of bounds");
		}
		int i = 0;
		Node<ELEMENT> current = front;
		while (i < index) {
			current = current.getNext();
			i++;
		}
		return current.getData();
	}

	/**
	 * obtains and removes the element at a given position
	 * 
	 * @param index
	 *            the position to pop the element from
	 * @return the element at position index
	 */
	public ELEMENT pop(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("List index out of bounds");
		}
		ELEMENT data = null;
		if (index == 0) {
			data = front.getData();
			front = front.getNext();
		} else {
			int i = 1;
			Node<ELEMENT> previous = front;
			Node<ELEMENT> current = front.getNext();
			while (i < index) {
				previous = current;
				current = current.getNext();
				i += 1;
			}
			data = current.getData();
			previous.setNext(current.getNext());
		}
		size -= 1;
		return data;
	}

	/**
	 * obtains a String representation of <i>LinkedList</i>
	 */
	public String toString() {
		String string = "";
		if (!isEmpty()) {
			int i = 1;
			Node<ELEMENT> current = front;
			string = current.getData().toString();
			while (i < size) {
				current = current.getNext();
				string += ", " + current.getData().toString();
				i += 1;
			}
		}
		string = "[" + string + "]";
		return string;
	}

	public void setArray(ELEMENT[] array) {
		size = array.length;
		front = null;
		if (size != 0) {
			front = new Node<ELEMENT>(array[0]);
			Node<ELEMENT> current = front;
			int i = 1;
			
			while (i < size) {
				current.setNext(new Node<ELEMENT>(array[i]));
				current = current.getNext();
				i += 1;
			}
		}
	}
	
	/**
	 * obtains an array form of <i>LinkedList</i>
	 * 
	 * @return the elements of <i>LinkedList</i> as an array of size
	 *         <i>getSize()</i>
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		Node<ELEMENT> current = front;
		int i = 0;
		while (i < size) {
			array[i] = current.getData();
			current = current.getNext();
			i += 1;
		}
		return array;
	}

	// Inner Class
	// ------------------------------------------------------------------------------------
	/**
	 * A node for holding the data pushed into and popped out of a
	 * <i>LinkedList</i> <br>
	 * and for linking the different data elements together <br>
	 * As with <i>LinkedList</i>, the <i>Node</i> class has since been enhanced
	 * by <br>
	 * gaining the ability to specify what class of Object is going in the Node <br>
	 * only <i>Node</i>s containing Objects belonging to the same class may be
	 * linked
	 * 
	 * @author Richard Douglas<br>
	 *         <b>ID:</b> 100913230 <br>
	 *         <b>Email:</b> doug3230@mylaurier.ca
	 */
	public class Node<TYPE> {
		// Data Members
		// ------------
		TYPE data;
		Node<TYPE> next;

		// Constructors
		// ------------
		/**
		 * creates an empty <i>Node</i> with no data and no "next" <i>Node</i>
		 */
		public Node() {
			data = null;
			next = null;
		}

		/**
		 * creates an empty <i>Node</i> with an Object as data
		 * 
		 * @param object
		 *            the Object to hold in the data field
		 */
		public Node(TYPE object) {
			data = object;
			next = null;
		}

		// Methods
		// -------
		/**
		 * obtains <i>Node</i>'s data
		 * 
		 * @return the Object kept in the data field
		 */
		public TYPE getData() {
			return data;
		}

		/**
		 * changes the Object held in <i>Node</i>'s data field
		 * 
		 * @param object
		 *            the Object to be used as data
		 */
		public void setData(TYPE object) {
			data = object;
		}

		/**
		 * obtains the "next" <i>Node</i> right after this one <br>
		 */
		public Node<TYPE> getNext() {
			return next;
		}

		/**
		 * changes the <i>Node</i> that is "next" after this one
		 * 
		 * @param node
		 *            the new "next" for <i>Node</i>
		 */
		public void setNext(Node<TYPE> node) {
			next = node;
		}
	}

	// ------------------------------------------------------------------------------------

	private static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.insertToBack(new Integer(1));
		list.insertToBack(new Integer(2));
		list.insert(new Integer(3), 1);
		list.insertToBack(new Integer(4));
		System.out.println(list);
		Integer[] newArray = {1,2,3,4,5,6,7,18};
		list.setArray(newArray);
		list.insert(new Integer(787),5);
		System.out.println(list.getSize());
		System.out.println(list);
	}
}
