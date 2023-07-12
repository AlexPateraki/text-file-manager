package dLinkedList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class of dLinkedList package consists of two nodes showing the first and the
 * last Node, also saying the number of the nodes the list has.
 * reference:https://examples.javacodegeeks.com/doubly-linked-list-java-example/
 *
 * @author Alexandra Pateraki
 */
public class LinkedList {
	protected Node start;// head
	protected Node end;
	protected Node defaultNode;
	public int numOfNodes;
	public int toggle;

	/**
	 * empty constructor
	 */
	public LinkedList() {
		start = null;
		end = null;
		numOfNodes = 0;
	}

	/**
	 * showing when list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return start == null;
	}

	// setters and getters
	public int getSize() {
		return numOfNodes;
	}

	public int getToggle() {
		return toggle;
	}

	public void setToggle(int toggle) {
		this.toggle = toggle;
	}

	public Node getDefaultNode() {
		return defaultNode;
	}

	public void setDefaultNode(Node defaultNode) {
		this.defaultNode = defaultNode;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

	public void setSize(int size) {
		this.numOfNodes = size;
	}

	/**
	 * method that inserts at the beginning of the list the new node
	 * 
	 * @param inData=the string inserted from the file or by the user
	 * @return the node in which the data was inesrted
	 */
	public Node insertAtStart(char[] inData) {
		Node nptr = new Node(inData, null, null);
		if (start == null) {
			start = nptr;
			end = start;
		} else {
			start.setLinkPrev(nptr);
			nptr.setLinkNext(start);
			start = nptr;
		}
		numOfNodes++;
		setSize(numOfNodes);
		return nptr;
	}

	/**
	 * method that inserts at the end of the list the new node
	 * 
	 * @param inData=the
	 *            string inserted from the file or by the user
	 * @returnthe node in which the data was inesrted
	 */
	public Node insertAtEnd(char[] inData) {
		Node nptr = new Node(inData, null, null);
		nptr.size = 0;
		if (start == null) {
			start = nptr;
			end = start;
			nptr.size = 1;

		} else {
			nptr.setLinkPrev(end);
			end.setLinkNext(nptr);
			end = nptr;
			nptr.size++;
		}
		numOfNodes++;
		setSize(numOfNodes);
		return nptr;
	}

	/**
	 * inserting a node in specific position of the linked list
	 * 
	 * @param str=the
	 *            string inserted from the file or by the user
	 * @param pos
	 * @return that node
	 */
	public Node insertAtPos(char[] str, int pos) {
		Node nptr = new Node(str, null, null);
		if (pos == 1) {
			return insertAtStart(str);

		}
		Node ptr = start;
		int counter = getSize();
		for (int i = 2; i <= counter; i++) {
			if (i == pos) {
				Node tmp = ptr.getLinkNext();
				ptr.setLinkNext(nptr);
				nptr.setLinkPrev(ptr);
				nptr.setLinkNext(tmp);
				tmp.setLinkPrev(nptr);

			}
			ptr = ptr.getLinkNext();
		}
		counter++;
		return ptr;

	}

	/**
	 * method reference:
	 * https://www.geeksforgeeks.org/delete-a-node-in-a-doubly-linked-list/
	 * 
	 * @param del
	 *            = the node that i want to delete
	 * @return
	 */
	public Node deleteNode(Node del) {

		// Base case
		if (start == null || del == null) {
			return null;
		}

		// If node to be deleted is head node
		if (start == del) {
			start = del.next;
			end = null;
			return null;
		}

		// Change next only if node to be deleted
		// is NOT the last node
		if (del.next != null) {
			del.next.prev = del.prev;
			return del.prev;
		}

		// Change prev only if node to be deleted
		// is NOT the first node
		if (del.prev != null) {
			del.prev.next = del.next;
			return del.prev;

		}
		return del;

		// free the memory occupied by del

	}

	/**
	 * default: off=0, if i press toggle=1 so button=on, if i press again toggle=2
	 * etc
	 * 
	 * @param toggle=
	 *            how much times i had press the button
	 * @return boolean false if the button is off, or true if it's on
	 */
	public boolean toggleButton(int toggle) {
		// d
		if (toggle % 2 == 0) {
			return false;// button=off
		}
		return true;// button=on

	}

	/**
	 * with the arguments this method can find the position that it is
	 * 
	 * @param start=the
	 *            first node of the linked list
	 * @param node=current
	 *            node
	 * @return the position of the node reference:
	 *         https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/
	 */
	public int findNode(Node start, Node node) {
		int i = 1;
		// Base case
		if (start == null)
			return i;

		// If key is present in current node,
		while (start.data != node.data) {
			i++;
			start = start.next;
		}
		return i;

	}

	/**
	 *  a method to display the DoublyLinkedList if the toggle is off
	 * @param numOfNodes= the number of consisting nodes of the linked list
	 */
	public void display(int numOfNodes) {
		System.out.print("Doubly Linked List =\n");
		if (numOfNodes == 0) {
			System.out.print("empty\n");
			return;
		}
		if (start.getLinkNext() == null) {
			System.out.println(start.getData());
			return;
		}
		Node ptr = start;
		System.out.print(String.valueOf(start.getData()) + " \n ");
		ptr = start.getLinkNext();
		while (ptr.getLinkNext() != null) {
			System.out.print(String.valueOf(ptr.getData()) + " \n ");
			ptr = ptr.getLinkNext();
		}
		System.out.print(String.valueOf(ptr.getData()) + "\n");
	}
/**
 * 	  a method to display the DoublyLinkedList if the toggle is off
 *  @param numOfNodes= the number of consisting nodes of the linked list
 */
	public void displayWithNum() {
		System.out.print("Doubly Linked List =\n");
		if ((getSize() == 0) || (numOfNodes == 0)) {
			System.out.print("empty\n");
			return;
		}
		if (start.getLinkNext() == null) {
			System.out.println(start.getData());
			return;
		}
		Node ptr = start;
		int j = 1;
		ptr.size = j;
		System.out.print(ptr.size + ")" + String.valueOf(start.getData()) + " \n ");
		ptr = start.getLinkNext();
		j++;
		ptr.setSize(j);
		while (ptr.getLinkNext() != null) {
			System.out.print(j + ")" + String.valueOf(ptr.getData()) + " \n ");
			ptr = ptr.getLinkNext();
			j++;
			ptr.setSize(j);
		}
		System.out.print(j + ")" + String.valueOf(ptr.getData()) + "\n");
	}

	/** a method that counts the length of the node's data
	 returns the number of the whole linked list characters.
	 */
	public int numOfChars() {
		int i = 0;
		if (start == null) {
			return i;
		}
		while (start != null) {
			int j = start.data.length;
			start = start.next;
			i = i + j;
		}
		return i;
	}

	/**
	 * write the file method with the parameters
	 * 
	 * @param start
	 * @param str
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void writeData(Node start, String str) throws IOException, InterruptedException {
		FileWriter writer = new FileWriter(new File(str));
		char[] data = null;
		while (start != null) {
			data = start.data;
			start = start.next;
			writer.write(data);
			writer.write("\n");
		}
		writer.close();

	}
}
