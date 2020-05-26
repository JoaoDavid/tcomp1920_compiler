package ast.statement;

import ast.Position;
import ast.datatype.Type;
import ast.expression.Expression;

public class VarDeclarationStatement implements Statement {
	
	private String varName;
	private Type datatype;
	private Expression value;
	private Position pos;
	
	
	public VarDeclarationStatement(String varName, Type datatype, Expression value) {
		this(varName, datatype, value, null);
	}
		
	public VarDeclarationStatement(String varName, Type datatype, Expression value, Position pos) {
		this.varName = varName;
		this.datatype = datatype;
		this.value = value;
		this.pos = pos;
	}

	public String getVarName() {
		return varName;
	}
	
	public Type getDatatype() {
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
