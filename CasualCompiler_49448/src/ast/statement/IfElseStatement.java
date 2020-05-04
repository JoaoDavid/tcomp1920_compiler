package ast.statement;

import java.util.List;

import ast.Position;
import ast.expression.Expression;

public class IfElseStatement extends IfStatement {
	
	private List<Statement> secondBody;
	
	public IfElseStatement(Expression condition, List<Statement> body) {
		super(condition, body);
	}

	public IfElseStatement(Expression condition, List<Statement> body, Position pos) {
		super(condition, body, pos);
	}

	public List<Statement> getSecondBody() {
		return secondBody;
	}
	
}
