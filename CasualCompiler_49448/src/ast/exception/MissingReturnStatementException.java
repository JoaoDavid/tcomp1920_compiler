package ast.exception;

public class MissingReturnStatementException extends TypeCheckException {

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
