package ast.exception;

public class DuplicateVarAssignException extends TypeCheckException {

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
