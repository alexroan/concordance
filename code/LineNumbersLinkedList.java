/**
 * 
 * @author alr16
 * Linked list of LineNunberNodes
 */
public class LineNumbersLinkedList {

	private LineNumberNode first;
	private LineNumberNode last;
	private int length;
	
	//CONSTRUCTORS
	/**
	 * Blank constructor which initiates the linked list by creating a first
	 * and last node
	 */
	public LineNumbersLinkedList(){
		this.first = new LineNumberNode();
		this.last = new LineNumberNode();
		this.first.setNextNode(this.last);
		this.last.setPreviousNode(this.first);
		this.length = 0;
	}
	
	
	//METHODS
	/**
	 * Adds a new node to the front of the linked list with its line number
	 * @param lineNumber  int
	 * @return newNode  LineNumberNode
	 */
	public LineNumberNode addToFront(int lineNumber){
		LineNumberNode newNode = new LineNumberNode(lineNumber);
		newNode.setNextNode(this.first.getNextNode());
		newNode.getNextNode().setPreviousNode(newNode);
		this.first.setNextNode(newNode);
		newNode.setPreviousNode(this.first);		
		this.length++;	
		return this.first.getNextNode();
	}
	
	
	/**
	 * Adds a new node to the front of the linked list with its line number
	 * @param lineNumber  int
	 * @return newNode  LineNumberNode
	 */
	public LineNumberNode addToBack(int lineNumber){
		LineNumberNode newNode = new LineNumberNode(lineNumber);
		newNode.setPreviousNode(this.last.getPreviousNode());
		newNode.getPreviousNode().setNextNode(newNode);
		newNode.setNextNode(this.last);
		this.last.setPreviousNode(newNode);
		this.length++;
		return this.last.getPreviousNode();
	}
	
	
	/**
	 * Adds a new node to the front of the linked list with its line number
	 * and context
	 * @param lineNumber  int
	 * @param context  context
	 * @return newNode  LineNumberNode
	 */
	public LineNumberNode addToFront(int lineNumber, String context){
		LineNumberNode newNode = new LineNumberNode(lineNumber, context);
		newNode.setNextNode(this.first.getNextNode());
		newNode.getNextNode().setPreviousNode(newNode);
		this.first.setNextNode(newNode);
		newNode.setPreviousNode(this.first);		
		this.length++;	
		return this.first.getNextNode();
	}
	
	
	/**
	 * Adds a new node to the back of the linked list with its line number
	 * and context
	 * @param lineNumber  int
	 * @param context  String
	 * @return newNode LineNumberNode
	 */
	public LineNumberNode addToBack(int lineNumber, String context){
		LineNumberNode newNode = new LineNumberNode(lineNumber, context);
		newNode.setPreviousNode(this.last.getPreviousNode());
		newNode.getPreviousNode().setNextNode(newNode);
		newNode.setNextNode(this.last);
		this.last.setPreviousNode(newNode);
		this.length++;
		return this.last.getPreviousNode();
	}
	
	
	/**
	 * Removes an item from the front of the linked list
	 * @return lineNumber  int
	 * @throws CannotRemoveException
	 */
	public int removeFromFront() throws CannotRemoveException{
		LineNumberNode currentNode;
		int removedNumber;
		if (this.isEmpty())
			throw new CannotRemoveException("Cannot remove from front. List is empty");
		else{
			currentNode = this.first.getNextNode();
			this.first.setNextNode(currentNode.getNextNode());
			currentNode.getNextNode().setPreviousNode(this.first);
			currentNode.setPreviousNode(null);
			currentNode.setNextNode(null);
			removedNumber = currentNode.getLineNumber();
			this.length--;
		}
		return removedNumber;
	}
	
	
	/**
	 * Removes an item from the back of the linked list
	 * @return lineNumber  int
	 * @throws CannotRemoveException
	 */
	public int removeFromBack() throws CannotRemoveException{
		LineNumberNode currentNode;
		int removedNumber;
		if (this.isEmpty())
			throw new CannotRemoveException("Cannot remove from back. List is empty");
		else{
			currentNode = this.last.getPreviousNode();
			this.last.setPreviousNode(currentNode.getPreviousNode());
			currentNode.getPreviousNode().setNextNode(this.last);
			currentNode.setNextNode(null);
			currentNode.setPreviousNode(null);
			removedNumber = currentNode.getLineNumber();
			this.length--;
		}
		return removedNumber;
	}
	
	
	/**
	 * Returns true if the linked list was empty
	 * @return empty  boolean
	 */
	public boolean isEmpty(){
		boolean empty = false;
		if (this.first.getNextNode() == this.last){
			empty = true;
		}
		return empty;
	}
	
	
	/**
	 * Returns true if this LineNumbersLinkedList is equal to
	 * the inserted parameter
	 * @param otherList  LineNumbersLinkedList
	 * @return equals  bolean
	 */
	public boolean equals(LineNumbersLinkedList otherList){
		return (this == otherList);
	}
	
	//GETTERS AND SETTERS
	/**
	 * Gets the first node from the LineNumbersLinkedList
	 * @return frontNode  LineNumberNode
	 */
	public LineNumberNode getFront(){
		return this.first.getNextNode();
	}
	
	
	/**
	 * Gets the last node from the LineNumbersLinkedList
	 * @return backNode  LineNumberNode
	 */
	public LineNumberNode getBack(){
		return this.last.getPreviousNode();
	}
	
	
	/**
	 * Returns the length of the LineNumbersLinkedList
	 * @return length  int
	 */
	public int getLength(){
		return this.length;
	}
	
	
	/**
	 * Returns each nodes' lineNumber and context
	 * @return returnable  String
	 */
	public String toString(){
		String returnable = "";
		LineNumberNode currentNode = this.first.getNextNode();
		for (int i=0; i<this.length; i++){
			returnable = returnable + currentNode.getLineNumber() + ": '" + currentNode.getContext() + "'\n";
			currentNode = currentNode.getNextNode();
		}
		return returnable;
	}
}
