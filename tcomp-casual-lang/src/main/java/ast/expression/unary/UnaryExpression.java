package ast.expression.unary;

import ast.Position;
import ast.expression.Expression;

public abstract class UnaryExpression implements Expression {
	
	private Expression value;
	private Position pos;
	
	public UnaryExpression(Expression value) {
		this(value, null);
	}
	
	public UnaryExpression(Expression value, Position pos) {
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
