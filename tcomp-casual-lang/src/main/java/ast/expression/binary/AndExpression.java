package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class AndExpression extends BinaryExpression {
	
	public AndExpression(Expression left, Expression right) {
		super(left, right);
	}

	public AndExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
