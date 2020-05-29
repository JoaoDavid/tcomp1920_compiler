package ast.expression;

import java.util.List;

import ast.Position;

public class FunctionInvocationExpression extends Expression {

	private String funcName;
	private List<Expression> arguments;
	private Position pos;
	
	
	public FunctionInvocationExpression(String funcName, List<Expression> arguments) {
		this(funcName, arguments, null);
	}
	
	public FunctionInvocationExpression(String funcName, List<Expression> arguments, Position pos) {
		this.funcName = funcName;
		this.arguments = arguments;
		this.pos = pos;
	}
	

	public String getFuncName() {
		return funcName;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	@Override
	public Position getPosition() {
		return pos;
	}	
	
}
