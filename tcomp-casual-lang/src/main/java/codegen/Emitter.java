package codegen;

import java.util.List;
import java.util.Stack;

public class Emitter {
	
	private int counter;
	private Stack<ScopeLLVM> stack;

	public Emitter() {
		this.stack = new Stack<>();
		this.enterScope();
	}
	
	public int getCount() {
		return ++counter;
	}
	
	public void set(String casualVar, String llVar) {
		stack.peek().set(casualVar, llVar);
	}
	
	public String get(String casualVar) {
		List<ScopeLLVM> list = stack.subList(0, stack.size());
		for (int i = stack.size()-1; i > 0; i--) {
			if (list.get(i).contains(casualVar)) {
				return list.get(i).get(casualVar);
			}			
		}
		return null;
	}
	
	public void enterScope() {
		stack.push(new ScopeLLVM());
	}
	
	public void exitScope() {
		if (!stack.isEmpty()) {
			stack.pop();
		}	
	}
	
	/*public boolean hasVariableCurrScope(String varName) {
		if (!stack.isEmpty()) {
			return stack.peek().contains(varName);
		}
		return false;
	}
	
	public boolean hasBeenDeclared(String varName) {
		return this.get(varName) != null;
	}*/
	
	

}
