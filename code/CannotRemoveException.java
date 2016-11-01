/**
 * 
 * @author alr16
 *
 */
@SuppressWarnings("serial")
public class CannotRemoveException extends Exception{

	/**
	 * This error occurs when an item cannot be removed from the
	 * LineNumbersLinkedList
	 * @param errorMessage  String
	 */
	public CannotRemoveException(String errorMessage){
		System.err.println(errorMessage);	
	}
}
