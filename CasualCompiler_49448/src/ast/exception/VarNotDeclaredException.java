package ast.exception;

public class VarNotDeclaredException extends TypeCheckException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VarNotDeclaredException() {
		super();
	}
	
	public VarNotDeclaredException(String message) {
		super(message);
	}

}
