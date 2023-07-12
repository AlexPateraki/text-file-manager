package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import commandsAsEnum.Commands;
import dLinkedList.DoublyLinkedList;
import dLinkedList.Node;
import fileAccess.FileAccess;
import random.RandomString;
import splitAndSort.ArrayTheWords;

/**
 * this class controls the user's option, check some unique cases and calls the
 * methods from other classes,prints menu
 * 
 * @author Alexandra Pateraki
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Commands userOption = Commands.addLnAfter;// the only way to initialize an enum, the name is default
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		FileAccess fa = new FileAccess();// access the file
		DoublyLinkedList dll = fa.findLines(args[0]);
		// i take args[0] inserting the text in the method and create the
		// doubly linked list and return it to main to work
		// with the commands
		printMenu();
		while (userOption.value != Commands.quit.toString()) {
			System.out.println("CMD>:");
			String userInput = reader.readLine();
			if (userInput == null) {
				continue;
			} else {
				try {
					userOption.value.equals(userInput);

				} catch (NumberFormatException ex) {
					userOption = null;
				}
			}

			Commands com = userOption.findTheEnum(userInput);
			Node node = dll.getDefaultNode();
			if (node == null)
				node = dll.getEnd();
			int toggle = dll.getToggle();
			switch (com) {
			case goFirst:
				node = dll.getStart();
				dll.setDefaultNode(node);
				System.out.println("-----I'm on the first line--------");
				break;
			case goLast:
				node = dll.getEnd();
				dll.setDefaultNode(node);
				System.out.println("-----I'm on the last line------");
				break;
			case goUp:
				if (node.getLinkPrev() != null)
					node = node.getLinkPrev();
				dll.setDefaultNode(node);
				System.out.println("-----I'm one line up------");
				break;
			case goDown:
				if (node.getLinkNext() != null)
					node = node.getLinkNext();
				dll.setDefaultNode(node);
				System.out.println("-----I'm one line down------");
				break;
			case addLnAfter:
				System.out.println("Please insert the data you want to insert after the current line:");
				char[] data = reader.readLine().toCharArray();
				int pos = 0;
				/// if the list is empty
				if (dll.numOfNodes == 0) {
					node = dll.insertAtStart(data);
					dll.setEnd(node);
					dll.setStart(node);
					node.setLinkNext(null);
					System.out.println("a");
				} // if the pointer not showing somewhere
				else if (node == null) {
					node = dll.getEnd();
					node = dll.insertAtEnd(data);
					dll.setEnd(node);
				} else if (node == dll.getEnd())// insert at the end of the linked list
				{
					node = dll.insertAtEnd(data);

				} else {
					node = dll.insertAtPos(data, pos);// insert elsewhere
					pos = dll.findNode(dll.getStart(), node);
				}
				dll.setDefaultNode(node);
				dll.setEnd(node);
				break;
			case addLnBfr:
				System.out.println("Please insert the data you want to insert before the current line:");
				char[] d = reader.readLine().toCharArray();
				int posNum = 0;
				posNum = dll.findNode(dll.getStart(), node);
				node = dll.insertAtPos(d, posNum);
				dll.setDefaultNode(node);
				dll.numOfNodes++;
				break;
			case del:
				if (dll.numOfNodes > 0) {
					if (dll.numOfNodes > 1) {// if not the last element of the DLL
						dll.setDefaultNode(dll.deleteNode(node));
						dll.setEnd(node);
					} else {// if the last element of the DLL
						dll.deleteNode(node);
						dll.setEnd(null);
					}
					dll.numOfNodes--;
				}
				// if the list is empty
				if (dll.numOfNodes <= 0)
					System.out.println("The list is empty. No elements!");
				break;
			case print:
				if (dll.toggleButton(toggle) == false)
					dll.display(dll.numOfNodes);
				else
					dll.displayWithNum();
				break;
			case toggle:
				toggle++;
				dll.setToggle(toggle);
				break;
			case printCur:
				if (node == null)
					node = dll.getEnd();
				pos = dll.findNode(dll.getStart(), node);
				System.out.print(pos + ")");
				System.out.println(node.getData());
				break;
			case quit:
				System.out.println("Exit without save!");

				return;
			case write:
				// https://howtodoinjava.com/java/io/how-to-create-a-new-file-in-java/
				try {
					dll.writeData(dll.getStart(), args[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				break;
			case exit:
				System.out.println("Exit with save.");
				return;
			case printLnNum:
				if (node == null)
					node = dll.getEnd();
				pos = dll.findNode(dll.getStart(), node);
				System.out.println("Im on line " + pos);
				break;
			case printAll:

				System.out.println("Number of lines:" + dll.numOfNodes);
				System.out.println("number of chars:" + dll.numOfChars());
				break;
			case printIndex:
				fa.callTheSorting();
				break;
			case printSerial:
				System.out.println("Insert the word for serial search");
				String str=reader.readLine();
				/**
				 * for random words generation
				 */
				//RandomString rs=new RandomString();
				//str=rs.getAlphaNumericString(fa.getMaxWord());
				System.out.println(fa.callSerSearch(str));

				break;
			case printBinary:
				break;
			case filePointer:
				System.out.println("Pointers file is created.Data pages:"+fa.callWriting());
				break;
			case def:
				System.out.println("Bad command!");
				break;
			default:
				break;
			}
		}

	}

	/**
	 * // print command menu
	 */
	private static void printMenu() {
		System.out.println("^: go to the first line");
		System.out.println("$: go to the last line");
		System.out.println("-: go up one line");
		System.out.println("+: go down one line");
		System.out.println("a: add new line after current line");
		System.out.println("t: add new line before current line");
		System.out.println("d: delete current line");
		System.out.println("l: print all lines");
		System.out.println("n: Toggle whether line numbers are displayed when printing all lines");
		System.out.println("p: print current line");
		System.out.println("q: quit without save");
		System.out.println("w: write file to disk");
		System.out.println("x: exit with save");
		System.out.println("=: print current line number");
		System.out.println("#: print number of lines and characters.");
		System.out.println("c: make .ndx and prints pages of buffers");
		System.out.println("v: print pointers file");
		System.out.println("s: print lines of word serial search");
		System.out.println("b: print lines of word binary search");
	}

}
