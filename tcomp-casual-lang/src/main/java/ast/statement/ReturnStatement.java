package ast.statement;

import ast.Position;
import ast.datatype.Type;
import ast.expression.Expression;

public class ReturnStatement implements Statement {
	
	private Type retType;
	private Expression value;
	private Position pos;
	
	public ReturnStatement() {
		this(null, null);
	}
	
	public ReturnStatement(Position pos) {
		this(null, pos);
	}

	public ReturnStatement(Expression value) {
		this(value, null);
	}
	
	public ReturnStatement(Expression value, Position pos) {
		this.value = value;
		this.pos = pos;
	}	
	
	public void setRetType(Type retType) {
		this.retType = retType;
	}	

	public Type getRetType() {
		return retType;
	}	

	public Expression getValue() {
		return value;
	}

	@Override
	public Position getPosition() {
		return pos;
	}	
	
}
