package ast.statement;

import java.util.List;

import ast.Position;
import ast.expression.Expression;

public class IfElseStatement extends IfStatement {
	
	private List<Statement> bodyElse;
	
	public IfElseStatement(Expression condition, List<Statement> bodyIf, List<Statement> bodyElse) {
		this(condition, bodyIf, bodyElse, null);
	}

	public IfElseStatement(Expression condition, List<Statement> bodyIf, List<Statement> bodyElse, Position pos) {
		super(condition, bodyIf, pos);
		this.bodyElse = bodyElse;
	}

	public List<Statement> getBodyElse() {
		return bodyElse;
	}
	
}
