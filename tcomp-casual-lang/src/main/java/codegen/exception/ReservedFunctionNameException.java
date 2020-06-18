package codegen.exception;

public class ReservedFunctionNameException extends CompileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReservedFunctionNameException() {
		super();
	}
	
	public ReservedFunctionNameException(String message) {
		super(message);
	}

}
