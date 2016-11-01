/**
 * 
 * @author alr16
 * Class containing a line number and context in a LineNumberLinkedList
 */
public class LineNumberNode {

	private int lineNumber;
	private String context;
	private LineNumberNode nextNode;
	private LineNumberNode previousNode;
	
	//CONSTRUCTORS
	/**
	 * Blank construcotr
	 */
	public LineNumberNode(){
	}
	
	/**
	 * Creates an instance of the lineNumber contained within this node
	 * @param newLineNumber int
	 */
	public LineNumberNode(int newLineNumber){
		this.lineNumber = newLineNumber;
	}
	
	/**
	 * Creates an instance of the lineNumber contained within this node and
	 * the pointers to the next and previous nodes
	 * @param newLineNumber  int
	 * @param newNextNode  LineNumberNode
	 * @param newPreviousNode  LineNumberNode
	 */
	public LineNumberNode(int newLineNumber,LineNumberNode newNextNode, LineNumberNode newPreviousNode){
		this.lineNumber = newLineNumber;
		this.nextNode = newNextNode;
		this.previousNode = newPreviousNode;
	}
	
	/**
	 * Creates a new instance of this lineNumber and context
	 * @param newLineNumber  int
	 * @param newContext  String
	 */
	public LineNumberNode(int newLineNumber, String newContext){
		this.lineNumber = newLineNumber;
		this.context = newContext;
	}
	
	/**
	 * Creates a new instance of this lineNumber, context, nextNode and previousNode
	 * @param newLineNumber  int
	 * @param newContext  String
	 * @param newNextNode  LineNumberNode
	 * @param newPreviousNode  LineNumberNode
	 */
	public LineNumberNode(int newLineNumber, String newContext, LineNumberNode newNextNode, LineNumberNode newPreviousNode){
		this.lineNumber = newLineNumber;
		this.context = newContext;
		this.nextNode = newNextNode;
		this.previousNode = newPreviousNode;
	}
	
	
	//METHODS
	//GETTERS AND SETTERS
	/**
	 * returns this lineNumber
	 * @return lineNumber  int
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * sets this lineNumber
	 * @param lineNumber  int
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * gets the next node
	 * @return nextNode  LineNumberNode
	 */
	public LineNumberNode getNextNode() {
		return nextNode;
	}

	/**
	 * sets the next node
	 * @param nextNode  LineNumberNode
	 */
	public void setNextNode(LineNumberNode nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * gets the previous node
	 * @return previousNode  LineNumberNode
	 */
	public LineNumberNode getPreviousNode() {
		return previousNode;
	}

	/**
	 * sets the previous node
	 * @param previousNode  LineNumberNode
	 */
	public void setPreviousNode(LineNumberNode previousNode) {
		this.previousNode = previousNode;
	}

	/**
	 * gets this context
	 * @return context  String
	 */
	public String getContext() {
		return context;
	}

	/**
	 * sets this context
	 * @param context  String
	 */
	public void setContext(String context) {
		this.context = context;
	}
}
