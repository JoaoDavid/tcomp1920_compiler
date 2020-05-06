package ast.typecheck;

public class FuncSignatureScope extends Scope {
	
	private String funcName;
	private String retType;
	
	public FuncSignatureScope(String funcName, String retType) {
		super();
		this.funcName = funcName;
		this.retType = retType;
	}

	public String getFuncName() {
		return funcName;
	}

	public String getRetType() {
		return retType;
	}	

}
