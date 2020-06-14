package ast.expression;

import java.util.List;

import ast.Position;
import ast.datatype.Type;

public class ArrayAccessFuncExpression extends ArrayAccessVarExpression {

	private List<Expression> arguments;
	private Type funcResType;
	
	public ArrayAccessFuncExpression(String varName, List<Expression> indexes, List<Expression> arguments) {
		this(varName, indexes, arguments, null);
	}

	public ArrayAccessFuncExpression(String varName, List<Expression> indexes, List<Expression> arguments, Position pos) {
		super(varName, indexes, pos);
		this.arguments = arguments;
	}
	

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setFuncResType(Type type) {
		this.funcResType = type;		
	}

	public Type getFuncResType() {
		return funcResType;
	}	
			
}
