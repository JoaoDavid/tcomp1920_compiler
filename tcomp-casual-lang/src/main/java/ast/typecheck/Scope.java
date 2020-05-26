package ast.typecheck;

import java.util.HashMap;

import ast.datatype.Type;

public class Scope {
	
	private HashMap<String, Type> map;
	
	public Scope() {
		map = new HashMap<String, Type>();
	}

	public void set(String varName, Type datatype) {
		map.put(varName, datatype);
	}

	public boolean contains(String varName) {
		return map.containsKey(varName);
	}

	public Type get(String varName) {
		return map.get(varName);
	}

}
