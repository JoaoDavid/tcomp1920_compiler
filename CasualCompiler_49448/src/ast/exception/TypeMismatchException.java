package ast.exception;

public class TypeMismatchException extends TypeCheckException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TypeMismatchException() {
		super();
	}
	
	public TypeMismatchException(String message) {
		super(message);
	}

}
