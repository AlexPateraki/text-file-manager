package splitAndSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**creates an arrayList with the words
 * @author Alexandra Pateraki
*reference:https://stackoverflow.com/questions/2015538/arrays-with-different-datatypes-i-e-strings-and-integers-objectorientend
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import fileAccess.FilePageAccess;

/**
 * this class sorts the array of the words.The check of the words takes place in
 * the class FileAccess also it closes the writing of the pointer file
 * 
 * @author Alexandra Pateraki
 *
 */
public class ArrayTheWords {
	ArrayList<SplitingWords> arrayWords = new ArrayList<SplitingWords>();
	int accesses;

	// setters and getters*************
	public int getAccesses() {
		return accesses;
	}

	public void setAccesses(int accesses) {
		this.accesses = accesses;
	}

	public ArrayList<SplitingWords> getArrayWords() {
		return arrayWords;
	}

	public void setArrayWords(ArrayList<SplitingWords> arrayWords) {
		this.arrayWords = arrayWords;
	}

	///////// *************************************

	/**
	 * the method inserts the word in the array
	 * 
	 * @param str=the
	 *            word
	 * @param line=the
	 *            line where it occurs
	 */
	public void insertWordOnArray(String token, int line) {
		arrayWords.add(new SplitingWords(line, token));
		setArrayWords(arrayWords);
	}

	/**
	 * this method do sorting with the help of Collections.sort in a for loop
	 */
	public void sorting() {
		Collections.sort(arrayWords, new SortByData());
		for (int counter = 0; counter < arrayWords.size(); counter++) {
			System.out.print(arrayWords.get(counter).word);
			System.out.println(arrayWords.get(counter).numOfLine);
		}
		setArrayWords(arrayWords);

	}

	/**
	 * it controls the writing of the pointer file and closes the writer
	 * 
	 * @returns the buffer size
	 * @throws IOException
	 */
	public int writeBinaryFile() throws IOException {
		FilePageAccess fpa = new FilePageAccess("BinaryFile");
		FileWriter writer = new FileWriter(new File(fpa.getFileName()));

		int count = 0;
		ArrayList<SplitingWords> sw = new ArrayList<SplitingWords>();
		for (int counter = 0; counter < arrayWords.size(); counter++) {
			sw.add(new SplitingWords(arrayWords.get(counter).numOfLine, arrayWords.get(counter).word));
		}
		for (count = 0; count < arrayWords.size(); count++) {
			fpa.writeBinary(sw.get(count).word, sw.get(count).numOfLine, writer);
		}
		writer.close();
		return count;
	}
/**
 * this method searches for the parameter word all over the file serial, from the start, also counts how many accesses happens.
 * @param str=string that i want to search for
 * @return a string of the lines that have the word
 */
	public String searchSerial(String str) {
		ArrayList<SplitingWords> sw = new ArrayList<SplitingWords>();
		int found = 0;
		String str1 = "";
		int[] ret = new int[arrayWords.size()];
		Collections.sort(arrayWords, new SortByData());
		for (accesses = 0; accesses < arrayWords.size(); accesses++) {
			sw.add(new SplitingWords(arrayWords.get(accesses).numOfLine, arrayWords.get(accesses).word));
			if (arrayWords.get(accesses).word.contains(str)) {
				ret[found] = arrayWords.get(accesses).numOfLine;
				found++;
			} else if ((found > 0) && (str != arrayWords.get(accesses).word)) {
				int m = 0;
				while (m < found) {
					str1 =str1 + "    " + Integer.toString(ret[m]);
					m++;
				}
				accesses+=1;
				System.out.println("Disk accesses: "+accesses);
				return ("found on lines:"+str1);
			}
 

		}
		accesses+=1;
		System.out.println("Disk accesses:"+accesses);
		return "Not found";
	}


}
