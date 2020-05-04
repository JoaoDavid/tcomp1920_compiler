package ast.expression.unary;

import ast.Position;
import ast.expression.Expression;

public class UnaryExpression implements Expression {
	
	private Expression value;
	private Position pos;
	
	public UnaryExpression(Expression value) {
		new UnaryExpression(value, null);
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
