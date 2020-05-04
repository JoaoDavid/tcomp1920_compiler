package ast;

import java.util.List;

import ast.statement.Statement;
import ast.statement.VarDeclarationStatement;

public class FunctionDefinition extends FunctionDeclaration {
	
	private List<Statement> statements;
	
	public FunctionDefinition(String funcName, List<VarDeclarationStatement> parameters, String returnType) {
		super(funcName, parameters, returnType);
	}
	
	public FunctionDefinition(String funcName, List<VarDeclarationStatement> parameters, String returnType,
			Position pos) {
		super(funcName, parameters, returnType, pos);
	}

	
	public List<Statement> getStatements() {
		return statements;
	}

}
