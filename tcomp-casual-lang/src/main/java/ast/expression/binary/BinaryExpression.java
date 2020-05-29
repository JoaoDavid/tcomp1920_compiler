package ast.expression.binary;

import ast.Position;
import ast.datatype.Type;
import ast.expression.Expression;

public abstract class BinaryExpression implements Expression {
	
	private Expression left;
	private Expression right;
	private Position pos;
	private Type resType;
	
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

	public Type getResType() {
		return resType;
	}

	public void setResType(Type resType) {
		this.resType = resType;
	}
	
	
}
