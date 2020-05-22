package ast.expression.unary;

import ast.Position;
import ast.expression.Expression;

public class NegativeExpression extends UnaryExpression {

	public NegativeExpression(Expression value) {
		super(value);
	}
	
	public NegativeExpression(Expression value, Position pos) {
		super(value, pos);
	}	

}
