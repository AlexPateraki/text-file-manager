package splitAndSort;

import java.util.Comparator;
/**
 * this class implements the class "SplitingWords" and its comparing the words of the two tuples
 * @author Alexandra Pateraki
 *reference : https://www.geeksforgeeks.org/collections-sort-java-examples/
 */
public  class SortByData implements Comparator<SplitingWords>{

	@Override
	public int compare(SplitingWords o1, SplitingWords o2) {
		String word1=o1.word;
		String word2=o2.word;
		return word1.compareTo(word2);
	}




}
