package ast.expression;

import ast.Position;

public class VarReferenceExpression implements Expression {
	
	private String varName;
	private Position pos;
	
	public VarReferenceExpression(String varName) {
		this(varName, null);
	}
	
	public VarReferenceExpression(String varName, Position pos) {
		this.varName = varName;
		this.pos = pos;
	}

	
	public String getVarName() {
		return varName;
	}

	@Override
	public Position getPosition() {
		return pos;
	}	

}
