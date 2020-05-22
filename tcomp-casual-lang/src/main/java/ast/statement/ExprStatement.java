package ast.statement;

import ast.Position;
import ast.expression.Expression;

public class ExprStatement implements Statement {

	private Expression value;
	private Position pos;	
	
	public ExprStatement(Expression value) {
		this(value, null);
	}
		
	public ExprStatement(Expression value, Position pos) {
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
