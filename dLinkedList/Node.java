package dLinkedList;

import java.util.Arrays;

/**
 * class of package dLinkedList implements a node -
 * "showing" its previous and next Node -
 * having a data segment from a char array with max amount of characters initialiazed to 80.
 * and an integer naumberhaving the size of the Node
 * @author Alexandra Pateraki
 */
public class Node {
	protected final int max=80;
    protected char[] data;
    protected Node next, prev;
    protected int size;
 
   /**
    * empty constructor
    */
    public Node() {
        next = null;
        prev = null;
        data = null;
        size=0;
    }
    
    /**
     * full constructor
     * @param inData
     * @param n
     * @param p
     */
    public Node(char[] inData, Node n, Node p) {
        data = inData;
        next = n;
        prev = p;
    }
 //********* auto-generate getters and setters **************//
    public void setLinkNext(Node n) {
        next = n;
    }
 
    public void setLinkPrev(Node p) {
        prev = p;
    }
 
    public Node getLinkNext() {
        return next;
    }
 
    public Node getLinkPrev() {
        return prev;
    }
 
    public void setData(char[] d) {
        data = d;
    }
 
    public char[] getData() {
        return data;
    }
    
public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
    /**
     * checking max characters of the array 
     * @param line= the inserting line
     * @return the characters until max size
     */
    public char[] checkMaxChars(char[] line) {
    	char[] newArray;
    	if ((line.length) > max) {
    		newArray=Arrays.copyOfRange(line, 0, max);
    		return newArray;
    	}
    				return line;
    }
}
