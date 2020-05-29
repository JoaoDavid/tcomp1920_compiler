package ast.expression.unary;

import ast.Position;
import ast.datatype.Type;
import ast.expression.Expression;

public abstract class UnaryExpression implements Expression {
	
	private Expression value;
	private Position pos;
	private Type resType;
	
	public UnaryExpression(Expression value) {
		this(value, null);
	}
	
	public UnaryExpression(Expression value, Position pos) {
		this.value = value;
		this.pos = pos;
	}
	
	
	public Expression getValue() {
		return value;
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
