package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class SumExpression extends BinaryExpression {
	
	public SumExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public SumExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
