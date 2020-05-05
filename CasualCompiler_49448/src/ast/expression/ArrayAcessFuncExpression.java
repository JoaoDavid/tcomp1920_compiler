package ast.expression;

import java.util.List;

import ast.Position;

public class ArrayAcessFuncExpression implements Expression {

	private String funcName;
	private List<Expression> funcParameters;
	private List<Expression> arrIndexes;
	private Position pos;
	
	public ArrayAcessFuncExpression(String funcName, List<Expression> funcParameters, List<Expression> arrIndexes) {
		this(funcName, funcParameters, arrIndexes, null);
	}
	
	public ArrayAcessFuncExpression(String funcName, List<Expression> funcParameters, List<Expression> arrIndexes,
			Position pos) {
		this.funcName = funcName;
		this.funcParameters = funcParameters;
		this.arrIndexes = arrIndexes;
		this.pos = pos;
	}

	public String getFuncName() {
		return funcName;
	}

	public List<Expression> getFuncParameters() {
		return funcParameters;
	}

	public List<Expression> getArrIndexes() {
		return arrIndexes;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
		
}
