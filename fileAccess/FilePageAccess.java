package fileAccess;

import java.io.FileWriter;
import java.io.IOException;

/**this method keeps the variables of the buffer and have methods that help in the converting the 
 * the string to bytes and to write the binary file
 * @author Alexandra Pateraki
 */
public class FilePageAccess {
	String fileName;
	int pageSize = 128;
	int maxWord = 20;
	int defaultBytes = 8;
	int maxLine=4;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * constructor for FilePageAccess
	 * 
	 * @param fileName
	 * @param pageSize
	 */
	public FilePageAccess(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * given method which converts from ascii to binary
	 * 	 @param word
	 * @return the array of bytes
	 */
	public byte[] convertToBinary(String word) {
		java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxWord);
		bb.put(word.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
		return bb.array();
	}

/**
 *allocates the right memory and converts al the ascii characters to binary with 
 * the convertToBinary method, write the binary file  
 * @param word=the text to be writen
 * @param line
 * @param writer
 * @throws IOException
 */
	public void writeBinary(String word, int line, FileWriter writer) throws IOException {
		byte[] b = new byte[maxWord * defaultBytes];
		
			b = convertToBinary(word);
			for (int i = 0; i < maxWord ; i++) {
				String str=Integer.toBinaryString(b[i]);
				writer.write(str);
			}
			java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxLine);
			bb.putInt(line);
			Integer.toBinaryString(line);
			writer.write(line);
			writer.write("\n");		
	}
}
