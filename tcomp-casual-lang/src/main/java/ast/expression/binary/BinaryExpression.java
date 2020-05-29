package ast.expression.binary;

import ast.Position;
import ast.expression.Expression;

public abstract class BinaryExpression extends Expression {
	
	private Expression left;
	private Expression right;
	private Position pos;
	
	public BinaryExpression(Expression left, Expression right) {
		this(left, right, null);
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
