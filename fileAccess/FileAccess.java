package fileAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import dLinkedList.DoublyLinkedList;
import dLinkedList.Node;
import splitAndSort.ArrayTheWords;

/**
 * access of the file, read it string to string and check if the chars are in
 * the right size(until 80 chars per line), also if the string of the file is
 * more than the max chars then the method splits the string into two or more
 * strings. This class controls the spliting of the strings into words
 * 
 * @author Alexandra Pateraki
 */
public class FileAccess {
	int i = 80;
	private static DoublyLinkedList DLL = new DoublyLinkedList();
	ArrayTheWords arrayWords = new ArrayTheWords();
	int maxWord = 20;

	public int getMaxWord() {
		return maxWord;
	}

	public void setMaxWord(int maxWord) {
		this.maxWord = maxWord;
	}

	// coding from courses with some changes
	public DoublyLinkedList findLines(String filename) throws IOException {
		try {
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			char[] line = null;
			int line_count = 0;
			String stringLine;
			char a = ' ';// gap char
			// variables for the spliting of the words
			int byte_count;
			int total_byte_count = 0;
			int fromIndex = 0;
			int minWord = 5;
			// --------
			while ((stringLine = br.readLine()) != null) {
				line = stringLine.toCharArray();
				line_count++;
				char[] line_rest = line;
				char[] secondArray;
				secondArray = new Node().checkMaxChars(line_rest);
				DLL.makeDLL(secondArray, line_count);
				String[] tokens = stringLine.split(",\\s+|\\s*\\\"\\s*|\\s+|\\.\\s*|\\s*\\:\\s*");
				for (int i = 1; i <= tokens.length; i++) {
					byte_count = line_rest.toString().indexOf(tokens[i - 1]);
					fromIndex = 0;
					if (tokens[i - 1].length() != 0)
						fromIndex = fromIndex + byte_count + 1 + tokens[i - 1].length();
					if (fromIndex > minWord) {
						if (fromIndex > maxWord) {
							tokens[i - 1].substring(0, maxWord);
						}
						int gapCounter = maxWord - tokens[i - 1].length();

						String gap = new String();
						gap = padding(gapCounter, a);
						String split = tokens[i - 1].concat(gap);
						arrayWords.insertWordOnArray(split, line_count);

					}
				}
				total_byte_count += fromIndex;
				/**
				 * includes also coding for spliting the line if it has more than 80
				 * chars(extra,not asked from the exercise)
				 */
				int j = 1;
				int k = (i - 1) * j;
				int f = line_rest.length - k;
				while (f > 0) {
					line_count++;

					if (f >= i) {
						secondArray = Arrays.copyOfRange(line_rest, k, k + i);
					} else {

						Arrays.fill(secondArray, a);
						secondArray = Arrays.copyOfRange(line_rest, k, k + f);
					}
					DLL.makeDLL(secondArray, line_count);
					j++;
					k = (i - 1) * j;
					f = line_rest.length - k;
				}
			}
			br.close();
			return DLL;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("Read failed");
		}
		return null;
	}

	/// *******auto-generate getters and setters/////
	public static DoublyLinkedList getDLL() {
		return DLL;
	}

	public static void setDLL(DoublyLinkedList dLL) {
		DLL = dLL;
	}

	/**
	 * this method calls the sorting of the spliting the words
	 */
	public void callTheSorting() {
		arrayWords.sorting();
	}

	/**
	 * call the methods of the writing of the binary file.
	 * 
	 * @returns the data pages
	 * @throws IOException
	 */
	public int callWriting() throws IOException {
		return arrayWords.writeBinaryFile() / maxWord + 4;
	}

	/**
	 * 
	 * @param str=string
	 *            i want to search for
	 * @return a string saying the lines that the word occurs
	 */
	public String callSerSearch(String str) {
		return arrayWords.searchSerial(str);
	}

	/**
	 * this method generates a string with padding characters
	 * 
	 * @param repeat=how
	 *            many times inserts padding
	 * @param padChar=what
	 *            char to pad
	 * @return the padding as a string
	 * @throws IndexOutOfBoundsException
	 */
	private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		}
		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

}
