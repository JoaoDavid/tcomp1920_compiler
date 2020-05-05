package ast;

import java.util.List;

import ast.statement.Statement;

public class FunctionDefinition extends FunctionDeclaration {
	
	private List<Statement> statements;
	
	public FunctionDefinition(String funcName, List<FunctionParameter> parameters, String returnType) {
		super(funcName, parameters, returnType);
	}
	
	public FunctionDefinition(String funcName, List<FunctionParameter> parameters, String returnType,
			Position pos) {
		super(funcName, parameters, returnType, pos);
	}

	
	public List<Statement> getStatements() {
		return statements;
	}

}
