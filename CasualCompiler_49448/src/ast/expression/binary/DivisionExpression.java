package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class DivisionExpression extends BinaryExpression {
	
	public DivisionExpression(Expression left, Expression right) {
		super(left, right, null);
	}

	public DivisionExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
