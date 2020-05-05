package ast.statement;

import ast.Position;
import ast.expression.Expression;

public class VarDeclarationStatement implements Statement {
	
	private String varName;
	private String datatype;
	private Expression value;
	private Position pos;
	
	
	public VarDeclarationStatement(String varName, String datatype, Expression value) {
		this(datatype, datatype, value, null);
	}
		
	public VarDeclarationStatement(String varName, String datatype, Expression value, Position pos) {
		this.varName = varName;
		this.datatype = datatype;
		this.value = value;
		this.pos = pos;
	}

	public String getVarName() {
		return varName;
	}
	
	public String getDatatype() {
		return datatype;
	}

	public Expression getValue() {
		return value;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

}
