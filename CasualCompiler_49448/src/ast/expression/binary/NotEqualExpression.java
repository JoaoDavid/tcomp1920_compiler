package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class NotEqualExpression extends BinaryExpression {
	
	public NotEqualExpression(Expression left, Expression right) {
		super(left, right);
	}

	public NotEqualExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
