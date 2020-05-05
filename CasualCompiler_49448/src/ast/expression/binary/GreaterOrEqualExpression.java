package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class GreaterOrEqualExpression extends BinaryExpression {
	
	public GreaterOrEqualExpression(Expression left, Expression right) {
		super(left, right);
	}

	public GreaterOrEqualExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
