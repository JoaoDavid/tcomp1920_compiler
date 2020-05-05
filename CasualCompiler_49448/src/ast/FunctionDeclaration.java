package ast;

import java.util.List;

public class FunctionDeclaration implements DefDecl {
	
	private String funcName;
	private List<FunctionParameter> parameters;
	private String returnType;
	private Position pos;
	
	public FunctionDeclaration(String funcName, List<FunctionParameter> parameters, String returnType) {
		new FunctionDeclaration(funcName, parameters, returnType, null);
	}
	
	public FunctionDeclaration(String funcName, List<FunctionParameter> parameters, String returnType,
			Position pos) {
		this.funcName = funcName;
		this.parameters = parameters;
		this.returnType = returnType;
		this.pos = pos;
	}

	public String getFuncName() {
		return funcName;
	}

	public List<FunctionParameter> getParameters() {
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
