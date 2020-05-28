package codegen.exception;

public abstract class CompileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompileException() {
		super();
	}
	
	public CompileException(String message) {
		super(message);
	}

}
