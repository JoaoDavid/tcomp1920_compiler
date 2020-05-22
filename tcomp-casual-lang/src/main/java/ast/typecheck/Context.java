package ast.typecheck;

import java.util.List;
import java.util.Stack;

public class Context {
	
	private Stack<Scope> stack;

	public Context() {
		this.stack = new Stack<>();
		this.enterScope();
	}
	
	public void set(String varName, String datatype) {
		stack.peek().set(varName, datatype);
	}
	
	public String get(String varName) {
		List<Scope> list = stack.subList(0, stack.size());
		for (int i = stack.size()-1; i > 0; i--) {
			if (list.get(i).contains(varName)) {
				return list.get(i).get(varName);
			}			
		}
		return null;
	}
	
	public void enterScope() {
		stack.push(new Scope());
	}
	
	public void exitScope() {
		if (!stack.isEmpty()) {
			stack.pop();
		}	
	}
	
	public boolean hasVariableCurrScope(String varName) {
		if (!stack.isEmpty()) {
			return stack.peek().contains(varName);
		}
		return false;
	}
	
	public boolean hasBeenDeclared(String varName) {
		return this.get(varName) != null;
	}
	
	
}
