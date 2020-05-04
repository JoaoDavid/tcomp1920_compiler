package ast.expression.unary;

import ast.Position;
import ast.expression.Expression;

public class NotExpression extends UnaryExpression {

	public NotExpression(Expression value) {
		super(value, null);
	}
	
	public NotExpression(Expression value, Position pos) {
		super(value, pos);
	}	

}
