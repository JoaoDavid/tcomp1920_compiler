package ast.exception;

public class FunctionNotDefinedException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FunctionNotDefinedException() {
		super();
	}
	
	public FunctionNotDefinedException(String message) {
		super(message);
	}

}
