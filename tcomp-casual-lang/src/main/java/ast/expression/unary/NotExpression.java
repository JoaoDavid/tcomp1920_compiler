package ast.expression.unary;

import ast.Position;
import ast.expression.Expression;
import ast.expression.OperatorConstant;

public class NotExpression extends UnaryExpression {

	public NotExpression(Expression value) {
		super(value);
	}
	
	public NotExpression(Expression value, Position pos) {
		super(value, pos);
	}	
	
	public String toString() {
		return OperatorConstant.NOT + value;
	}

}
