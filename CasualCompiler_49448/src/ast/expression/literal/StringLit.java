package ast.expression.literal;

import ast.Position;

public class StringLit extends LiteralExpression {
	
	public StringLit(String value) {
		super(value, null);
	}	

	public StringLit(String value, Position pos) {
		super(value, pos);
	}

}

