package ast.exception;

public class DuplicateVarAssignException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateVarAssignException() {
		super();
	}
	
	public DuplicateVarAssignException(String message) {
		super(message);
	}

}
