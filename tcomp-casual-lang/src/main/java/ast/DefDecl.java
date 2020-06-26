package ast;

import java.util.List;

import ast.datatype.Type;

public abstract class DefDecl implements Node {
	
	private String funcName;
	private List<FunctionParameter> parameters;
	private Type returnType;
	private Position pos;
	
	public DefDecl(String funcName, List<FunctionParameter> parameters, Type returnType) {
		this(funcName, parameters, returnType, null);
	}
	
	public DefDecl(String funcName, List<FunctionParameter> parameters, Type returnType, Position pos) {
		this.funcName = funcName;
		this.parameters = parameters;
		this.returnType = returnType;
		this.pos = pos;
	}

	public String getFuncName() {
		return funcName;
	}

	public List<FunctionParameter> getParameters() {
		return parameters;
	}

	public Type getReturnType() {
		return returnType;
	}	

	public Position getPosition() {
		return pos;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DefDecl)) {
			return false;
		}
		DefDecl other = (DefDecl) obj;		
		return getFuncName().equals(other.getFuncName());
	}	

}
