package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class LessExpression extends BinaryExpression {
	
	public LessExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public LessExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
