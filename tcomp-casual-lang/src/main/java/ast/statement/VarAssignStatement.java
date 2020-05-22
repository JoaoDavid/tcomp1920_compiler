package ast.statement;

import ast.Position;
import ast.expression.Expression;

public class VarAssignStatement implements Statement {
	
	private String varName;
	private Expression value;
	private Position pos;	
	
	public VarAssignStatement(String varName, Expression value) {
		this(varName, value, null);
	}
		
	public VarAssignStatement(String varName, Expression value, Position pos) {
		this.varName = varName;
		this.value = value;
		this.pos = pos;
	}

	public String getVarName() {
		return varName;
	}

	public Expression getValue() {
		return value;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

}
