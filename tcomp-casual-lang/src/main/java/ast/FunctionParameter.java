package ast;

import ast.datatype.Type;

public class FunctionParameter implements Node {
	
	private String varName;
	private Type datatype;
	private Position pos;
	
	public FunctionParameter(String varName, Type datatype) {
		this(varName, datatype, null);
	}
	
	public FunctionParameter(String varName, Type datatype, Position pos) {
		this.varName = varName;
		this.datatype = datatype;
		this.pos = pos;
	}
		

	public String getVarName() {
		return varName;
	}

	public Type getDatatype() {
		return datatype;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
	
	
	

}
