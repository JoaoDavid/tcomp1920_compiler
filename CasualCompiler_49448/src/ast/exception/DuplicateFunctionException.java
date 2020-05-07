package ast.exception;

public class DuplicateFunctionException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateFunctionException() {
		super();
	}
	
	public DuplicateFunctionException(String message) {
		super(message);
	}

}
