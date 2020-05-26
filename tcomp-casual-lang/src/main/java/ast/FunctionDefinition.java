package ast;

import java.util.List;

import ast.datatype.Type;
import ast.statement.Statement;

public class FunctionDefinition extends FunctionDeclaration {
	
	private List<Statement> statements;
	
	public FunctionDefinition(String funcName, List<FunctionParameter> parameters, Type returnType, List<Statement> statements) {
		super(funcName, parameters, returnType);
		this.statements = statements;
	}
	
	public FunctionDefinition(String funcName, List<FunctionParameter> parameters, Type returnType, List<Statement> statements,
			Position pos) {
		super(funcName, parameters, returnType, pos);
		this.statements = statements;
	}

	
	public List<Statement> getStatements() {
		return statements;
	}

}
