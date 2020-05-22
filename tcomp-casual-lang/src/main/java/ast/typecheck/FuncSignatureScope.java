package ast.typecheck;

public class FuncSignatureScope extends Scope {
	
	private String funcName;
	private String retType;
	private String[] datatypes;
	
	public FuncSignatureScope(String funcName, String retType, String[] datatypes) {
		this.funcName = funcName;
		this.retType = retType;
		this.datatypes = datatypes;
	}

	public String getFuncName() {
		return funcName;
	}

	public String getRetType() {
		return retType;
	}
	
	public String[] getDataTypes() {
		return datatypes;
	}

}
