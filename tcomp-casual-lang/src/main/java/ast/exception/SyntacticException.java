package ast.exception;

public abstract class SyntacticException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SyntacticException() {
		super();
	}
	
	public SyntacticException(String message) {
		super(message);
	}

}
