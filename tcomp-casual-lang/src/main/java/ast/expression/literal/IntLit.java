package ast.expression.literal;

import ast.Position;

public class IntLit extends LiteralExpression {
	
	public IntLit(String value) {
		super(value);
	}	

	public IntLit(String value, Position pos) {
		super(value, pos);
	}

}

