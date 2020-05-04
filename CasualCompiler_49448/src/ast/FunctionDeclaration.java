package ast;

import java.util.List;

import ast.statement.VarDeclarationStatement;

public class FunctionDeclaration implements DefDecl {
	
	private String funcName;
	private List<VarDeclarationStatement> parameters;
	private String returnType;
	private Position pos;
	
	public FunctionDeclaration(String funcName, List<VarDeclarationStatement> parameters, String returnType) {
		new FunctionDeclaration(funcName, parameters, returnType, null);
	}
	
	public FunctionDeclaration(String funcName, List<VarDeclarationStatement> parameters, String returnType,
			Position pos) {
		this.funcName = funcName;
		this.parameters = parameters;
		this.returnType = returnType;
		this.pos = pos;
	}

	public String getFuncName() {
		return funcName;
	}

	public List<VarDeclarationStatement> getParameters() {
		return parameters;
	}

	public String getReturnType() {
		return returnType;
	}	

	@Override
	public Position getPosition() {
		return pos;
	}	

}
