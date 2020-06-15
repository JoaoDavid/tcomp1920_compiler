package ast.exception;

public class UnreachableStatementException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnreachableStatementException() {
		super();
	}
	
	public UnreachableStatementException(String message) {
		super(message);
	}

}
