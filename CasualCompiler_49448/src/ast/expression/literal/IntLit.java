package ast.expression.literal;

import ast.Position;

public class IntLit extends LiteralExpression {
	
	public IntLit(String value) {
		super(value, null);
	}	

	public IntLit(String value, Position pos) {
		super(value, pos);
	}

}

