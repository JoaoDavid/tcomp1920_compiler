package ast.expression.literal;

import ast.Position;

public class BoolLit extends LiteralExpression {
	
	public BoolLit(String value) {
		super(value);
	}	

	public BoolLit(String value, Position pos) {
		super(value, pos);
	}

}

