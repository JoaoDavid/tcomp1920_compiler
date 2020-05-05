package ast.statement;

import ast.Position;
import ast.expression.Expression;

public class ReturnStatement implements Statement {
	
	private Expression value;
	private Position pos;

	public ReturnStatement(Expression value) {
		this(value, null);
	}
	
	public ReturnStatement(Expression value, Position pos) {
		this.value = value;
		this.pos = pos;
	}	

	public Expression getValue() {
		return value;
	}

	@Override
	public Position getPosition() {
		return pos;
	}	
	
}
