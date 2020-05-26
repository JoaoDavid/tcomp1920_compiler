package ast.typecheck;

import java.util.HashMap;

import ast.datatype.Type;
import ast.exception.DuplicateFunctionException;
import ast.exception.FunctionNotDefinedException;
import ast.exception.SyntacticException;

public class FuncSignContext {

	private HashMap<String, FuncSignatureScope> map;

	public FuncSignContext() {
		map = new HashMap<>();
	}

	public Type[] getDataTypes(String funcName) throws SyntacticException {
		FuncSignatureScope funcSign = map.get(funcName);
		if (funcSign == null) {
			throw new FunctionNotDefinedException(funcName);
		} else {
			return funcSign.getDataTypes();
		}
	}
	
	public Type getRetType(String funcName)  throws SyntacticException {
		FuncSignatureScope funcSign = map.get(funcName);
		if (funcSign == null) {
			throw new FunctionNotDefinedException(funcName);
		} else {
			return funcSign.getRetType();
		}
	}

	public void newFunc(String funcName, Type retType, Type[] datatypes) throws SyntacticException {
		if(map.containsKey(funcName)) {
			throw new DuplicateFunctionException(funcName);
		}
		map.put(funcName, new FuncSignatureScope(funcName, retType, datatypes));
	}


}
