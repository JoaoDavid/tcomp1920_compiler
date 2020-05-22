package ast.expression.literal;

import ast.Position;

public class FloatLit extends LiteralExpression {
	
	public FloatLit(String value) {
		super(value);
	}	

	public FloatLit(String value, Position pos) {
		super(value, pos);
	}

}

