package splitAndSort;
/**this class describes the tuples. It consists of the number of the line that its from, and of a string that is the word.
 * Also it has getters and setters, toString and a compareTo method which is overriding in the sortByData class
 * @author Alexandra Pateraki
 *
 */
public class SplitingWords  {
	int numOfLine;
	String word;
	
	public SplitingWords(int numOfLine, String token) {
		this.numOfLine = numOfLine;
		this.word = token;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	public int getLine() {
		return numOfLine;
	}
	public void setLine(int line) {
		this. numOfLine= line;
	}
	//comparing two tupples
	public int compareTo() {
		return 0;
	}

    // Used to print student details in main() 
    public String toString() 
    { 
        return this.word.toString() + " " + this.numOfLine ;
    } 
} 

