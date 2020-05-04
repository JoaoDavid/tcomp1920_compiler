package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public class BinaryExpression implements Expression {
	
	private Expression left;
	private Expression right;
	private Position pos;
	
	public BinaryExpression(Expression left, Expression right) {
		new BinaryExpression(left, right, null);
	}
	
	public BinaryExpression(Expression left, Expression right, Position pos) {
		this.left = left;
		this.right = right;
		this.pos = pos;
	}
	
	
	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
	
	
	
}
