package ast.statement;

import java.util.List;

import ast.Position;
import ast.expression.Expression;

public class VarAssignArrayStatement extends VarAssignStatement {
	
	private List<Expression> indexes;
	
	public VarAssignArrayStatement(String varName, Expression value, List<Expression> indexes) {
		this(varName, value, indexes, null);
	}

	public VarAssignArrayStatement(String varName, Expression value, List<Expression> indexes, Position pos) {
		super(varName, value, pos);
		this.indexes = indexes;
	}

	public List<Expression> getIndexes() {
		return indexes;
	}

}
