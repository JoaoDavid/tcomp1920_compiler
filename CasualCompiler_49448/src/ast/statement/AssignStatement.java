package ast.statement;

import ast.Position;
import ast.expression.Expression;

public class AssignStatement implements Statement {
	
	private String varName;
	private Expression value;
	private Position pos;	
	
	public AssignStatement(String varName, Expression value) {
		new AssignStatement(varName, value, null);
	}
		
	public AssignStatement(String varName, Expression value, Position pos) {
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
