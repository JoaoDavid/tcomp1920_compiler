package ast.typecheck;

import java.util.HashMap;

import ast.exception.DuplicateFunctionException;
import ast.exception.FunctionNotDefinedException;
import ast.exception.SyntacticException;

public class FuncSignContext {

	private HashMap<String, FuncSignatureScope> map;

	public FuncSignContext() {
		map = new HashMap<>();
	}

	public void set(String funcName, String varName, String datatype) throws SyntacticException {
		FuncSignatureScope funcSign = map.get(funcName);
		if (funcSign == null) {
			throw new FunctionNotDefinedException(funcName);
		} else {
			funcSign.set(varName, datatype);
		}
	}

	public String get(String funcName, String varName) throws SyntacticException {
		FuncSignatureScope funcSign = map.get(funcName);
		if (funcSign == null) {
			throw new FunctionNotDefinedException(funcName);
		} else {
			return funcSign.get(varName);
		}
	}

	public void newFunc(String funcName, String retType) throws SyntacticException {
		if(map.containsKey(funcName)) {
			throw new DuplicateFunctionException(funcName);
		}
	}


}
