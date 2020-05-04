package ast.statement;

import java.util.List;

import ast.Position;
import ast.expression.Expression;

public class IfStatement implements Statement {
	
	private Expression condition;
	private List<Statement> body;
	private Position pos;
	
	
	public IfStatement(Expression condition, List<Statement> body) {
		new WhileStatement(condition, body, null);
	}
		
	public IfStatement(Expression condition, List<Statement> body, Position pos) {
		this.condition = condition;
		this.body = body;
		this.pos = pos;
	}

	public Expression getCondition() {
		return condition;
	}

	public List<Statement> getBody() {
		return body;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
	
}
