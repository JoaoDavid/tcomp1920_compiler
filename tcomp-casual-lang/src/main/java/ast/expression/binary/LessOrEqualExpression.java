package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;
import ast.expression.OperatorConstant;

public class LessOrEqualExpression extends BinaryExpression {
	
	public LessOrEqualExpression(Expression left, Expression right) {
		super(left, right);
	}

	public LessOrEqualExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}
	
	public String toString() {
		return left + OperatorConstant.LESS_EQUAL + right;
	}

}
