package ast.expression;

import ast.Node;
import ast.datatype.Type;

public abstract class Expression implements Node {

	private Type resType;
	
	public Type getResType() {
		return resType;
	}

	public void setResType(Type resType) {
		this.resType = resType;
	}
	
	public abstract String toString();
	
}
