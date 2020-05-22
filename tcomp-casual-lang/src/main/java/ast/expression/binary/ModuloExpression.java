package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class ModuloExpression extends BinaryExpression {
	
	public ModuloExpression(Expression left, Expression right) {
		super(left, right);
	}

	public ModuloExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}

}
