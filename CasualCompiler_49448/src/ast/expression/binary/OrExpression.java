package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class OrExpression extends BinaryExpression {
	
	public OrExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public OrExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
