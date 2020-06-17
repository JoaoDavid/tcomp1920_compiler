package ast.exception;

public class MissingImportFileException extends SyntacticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingImportFileException() {
		super();
	}
	
	public MissingImportFileException(String message) {
		super(message);
	}

}
