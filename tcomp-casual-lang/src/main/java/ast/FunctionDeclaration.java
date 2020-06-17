package ast;

import java.util.List;

import ast.datatype.Type;

public class FunctionDeclaration extends DefDecl {
	
	public FunctionDeclaration(String funcName, List<FunctionParameter> parameters, Type returnType) {
		this(funcName, parameters, returnType, null);
	}
	
	public FunctionDeclaration(String funcName, List<FunctionParameter> parameters, Type returnType, Position pos) {
		super(funcName, parameters, returnType, pos);
	}

}
