package ast.typecheck;

import java.util.HashMap;

public class Scope {
	
	private HashMap<String, String> map;
	
	public Scope() {
		map = new HashMap<String, String>();
	}

	public void set(String varName, String datatype) {
		map.put(varName, datatype);
	}

	public boolean contains(String varName) {
		return map.containsKey(varName);
	}

	public String get(String varName) {
		return map.get(varName);
	}

}
