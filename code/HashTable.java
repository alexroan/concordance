import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * HashTable class holds a LinkedList of words from an index file
 * and a Hastable in which the keys are index words and values are
 * LineNumberLinkedLists of line numbers and content
 * 
 * @author alr16
 *
 */
@SuppressWarnings("rawtypes")
public class HashTable {
	
	private Hashtable<String, LineNumbersLinkedList> indexWords;
	private FileReader fR;
	private BufferedReader bR;
	private String book;
	private File f;
	private Scanner input;
	private LinkedList keyList;
	
	
	//CONSTRUCTORS
	/**
	 * Blank constructor creates new instances of Hashtable and
	 * LinkedList
	 */
	public HashTable(){
		this.indexWords = new Hashtable<String, LineNumbersLinkedList>();
		keyList = new LinkedList();
	}
	
	/**
	 * Takes an int size to set the size of the Hashtable
	 * @param size  int
	 */
	public HashTable(int size){
		this.indexWords = new Hashtable<String, LineNumbersLinkedList>(size);
		keyList = new LinkedList();
	}
	
	/**
	 * Takes an int to set size of Hashtable and String to set Book
	 * file path
	 * @param size  int
	 * @param book  String
	 */
	public HashTable(int size, String book){
		this.indexWords = new Hashtable<String, LineNumbersLinkedList>(size);		
		this.book = book;
		keyList = new LinkedList();
	}

	
	//METHODS
	/**
	 * Reads in an index file full of words that will be searched for and
	 * indexed in the specified book
	 * @param fileName  String
	 */
	public void readInIndexFile(String fileName){
		String line = "";
		try{
			fR = new FileReader(fileName);
			bR = new BufferedReader(fR);
			File f = new File(fileName);
	        Scanner input = new Scanner(f);
			while(input.hasNextLine()){
				line = bR.readLine();
				line = line.toLowerCase();
				this.addWord(line);
				line = input.nextLine();
			}
			fR.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Adds the word to the LinkedList of keys as a reference to
	 * the keys in the Hashtable. Then creates a new LineNumbersLinkedList
	 * and uses it to create a new entry in the Hashtable with the new word
	 * @param newWord  String
	 */
	@SuppressWarnings("unchecked")
	public void addWord(String newWord){
		this.keyList.add(newWord);
		LineNumbersLinkedList arrayOfLineNumbers = new LineNumbersLinkedList();
		this.indexWords.put(newWord, arrayOfLineNumbers);
	}
	
	/**
	 * For each line in the book, this alorithm splits the line and compares
	 * each word to every word in the LinkedList of keys. If a match is found,
	 * the Hashtable entry is extracted, it's LineNumbersLinkedList is edited
	 * to include the new line number and context and is fed back into the
	 * Hashtable.
	 */
	public void generateLineNumbers(){
		try {
			f = new File(this.book);
			input = new Scanner(f);
			String lineContent;
			String lineContentLowerCase;
			String[] splitLine;	
			int lineNumber = 0;
			LineNumbersLinkedList list;		
			while (input.hasNextLine()){
			lineNumber++;
				lineContent = input.nextLine();
				lineContentLowerCase = lineContent.toLowerCase();
				splitLine = lineContentLowerCase.split("([.,!?:;'\"-]|\\s)+");
				for (int wordInLine = 0; wordInLine<splitLine.length; wordInLine++){
					if (this.keyList.contains(splitLine[wordInLine])){
						list = this.indexWords.get(splitLine[wordInLine]);
						list.addToBack(lineNumber, lineContent);
						this.indexWords.put(splitLine[wordInLine], list);
					}
				}
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//GETTERS AND SETTERS
	/**
	 * Returns the entire Hashtable
	 * @return  Hashtable
	 */
	public Hashtable<String, LineNumbersLinkedList> getIndexWords() {
		return indexWords;
	}

	/**
	 * Sets the entire Hashtable
	 * @param indexWords  Hashtable
	 */
	public void setIndexWords(Hashtable<String, LineNumbersLinkedList> indexWords) {
		this.indexWords = indexWords;
	}

	/**
	 * Gets the current book path name
	 * @return  book String
	 */
	public String getBook() {
		return book;
	}

	/**
	 * Sets the book path name
	 * @param fileName  String
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * Returns every item in the Hashtable
	 * @return  String
	 */
	@SuppressWarnings("unchecked")
	public String toString(){
		Collections.sort(this.keyList);
		String returnable = "";
		for (int item = 0; item < this.keyList.size(); item++){
			LineNumbersLinkedList list = (LineNumbersLinkedList)this.indexWords.get(this.keyList.get(item));
			returnable = returnable + "'" + this.keyList.get(item) + "' appears in lines:\n" + list.toString() + "\n\n";
		}
		return returnable;
	}
	
	public void clearAll(){
		indexWords.clear();
		keyList.clear();
	}
}
