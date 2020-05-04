package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class SubtractionExpression extends BinaryExpression {
	
	public SubtractionExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public SubtractionExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
