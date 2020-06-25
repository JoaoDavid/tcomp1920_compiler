package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;
import ast.expression.OperatorConstant;

public class GreaterOrEqualExpression extends BinaryExpression {
	
	public GreaterOrEqualExpression(Expression left, Expression right) {
		super(left, right);
	}

	public GreaterOrEqualExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}
	
	public String toString() {
		return left + OperatorConstant.GREATER_EQUAL + right;
	}

}
