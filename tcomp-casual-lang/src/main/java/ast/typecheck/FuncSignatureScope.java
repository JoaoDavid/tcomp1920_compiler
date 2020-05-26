package ast.typecheck;

import ast.datatype.Type;

public class FuncSignatureScope extends Scope {
	
	private String funcName;
	private Type retType;
	private Type[] datatypes;
	
	public FuncSignatureScope(String funcName, Type retType, Type[] datatypes) {
		this.funcName = funcName;
		this.retType = retType;
		this.datatypes = datatypes;
	}

	public String getFuncName() {
		return funcName;
	}

	public Type getRetType() {
		return retType;
	}
	
	public Type[] getDataTypes() {
		return datatypes;
	}

}
