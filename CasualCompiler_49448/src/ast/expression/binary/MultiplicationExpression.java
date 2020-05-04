package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class MultiplicationExpression extends BinaryExpression {
	
	public MultiplicationExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public MultiplicationExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
