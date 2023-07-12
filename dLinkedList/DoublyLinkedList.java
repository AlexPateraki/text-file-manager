package dLinkedList;
/**
 * doubly linked list is an extension of the linked list, consisting of the made 
 * list, setters and getters and the method you can make the doubly linked list
 * @author Alexandra Pateraki
 */
public class DoublyLinkedList extends LinkedList {
	LinkedList list = new LinkedList();

	public LinkedList getList() {
		return list;
	}

	public void setList(LinkedList list) {
		this.list = list;
	}
/**
 * constructs the DLL with inserting the nodes and call the right method
 * @param data
 * @param nodeNum
 */
	public void makeDLL(char[] data, int nodeNum) {
		if (nodeNum == 1) {
			list.insertAtStart(data);
			numOfNodes=list.getSize();
			start=list.getStart();
			end=list.getEnd();
		} else {
			list.insertAtEnd(data);
			numOfNodes=list.getSize();
			start=list.getStart();
			end=list.getEnd();
		}
		
	}
}
