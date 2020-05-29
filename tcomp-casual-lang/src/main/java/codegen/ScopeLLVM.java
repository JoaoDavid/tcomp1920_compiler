package codegen;

import java.util.HashMap;

public class ScopeLLVM {
	
	private HashMap<String, String> map;
	
	public ScopeLLVM() {
		map = new HashMap<String, String>();
	}

	public void set(String casualVar, String llVar) {
		map.put(casualVar, llVar);
	}

	public boolean contains(String casualVar) {
		return map.containsKey(casualVar);
	}

	public String get(String casualVar) {
		return map.get(casualVar);
	}

}
