package ast.exception;

public class MissingReturnStatementException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingReturnStatementException() {
		super();
	}
	
	public MissingReturnStatementException(String message) {
		super(message);
	}

}
