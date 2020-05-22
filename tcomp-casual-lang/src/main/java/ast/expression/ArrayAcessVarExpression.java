package ast.expression;

import java.util.List;

import ast.Position;

public class ArrayAcessVarExpression implements Expression {

	private String varName;
	private List<Expression> indexes;
	private Position pos;
	
	public ArrayAcessVarExpression(String varName, List<Expression> indexes) {
		this(varName, indexes, null);
	}
	
	public ArrayAcessVarExpression(String varName, List<Expression> indexes, Position pos) {
		this.varName = varName;
		this.indexes = indexes;
		this.pos = pos;
	}
	

	public String getVarName() {
		return varName;
	}

	public List<Expression> getIndexes() {
		return indexes;
	}

	@Override
	public Position getPosition() {
		return pos;
	}	
	
}
