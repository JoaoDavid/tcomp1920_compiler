package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;
import ast.expression.OperatorConstant;

public class ModuloExpression extends BinaryExpression {
	
	public ModuloExpression(Expression left, Expression right) {
		super(left, right);
	}

	public ModuloExpression(Expression left, Expression right, Position pos) {
		super(left, right, pos);
	}
	
	public String toString() {
		return left + OperatorConstant.MOD + right;
	}

}
