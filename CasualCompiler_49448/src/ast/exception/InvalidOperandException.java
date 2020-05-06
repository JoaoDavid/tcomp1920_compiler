package ast.exception;

public class InvalidOperandException extends TypeCheckException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidOperandException() {
		super();
	}
	
	public InvalidOperandException(String message) {
		super(message);
	}

}
