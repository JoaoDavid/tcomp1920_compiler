package ast.expression.literal;

import ast.Position;
import ast.expression.Expression;

public class LiteralExpression implements Expression {
	
	private String value;
	private Position pos;
	
	public LiteralExpression(String value) {
		this(value, null);
	}	
	
	public LiteralExpression(String value, Position pos) {
		this.value = value;
		this.pos = pos;
	}
	

	public String getValue() {
		return value;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
	
}
