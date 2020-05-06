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
	
	public Scope get(String varName) throws Exception {
		List<Scope> list = stack.subList(0, stack.size());
		for (int i = stack.size()-1; i > 0; i--) {
			if (list.get(i).contains(varName)) {
				return list.get(i);
			}
			
		}
		throw new Exception("var " + varName + " not found");
	}
	
	public void enterScope() {
		stack.push(new Scope());
	}
	
	public void exitScope() throws Exception {
		if (!stack.isEmpty()) {
			stack.pop();
		} else {
			throw new Exception("no more scopes to pop");
		}		
	}
	
	public boolean hasVariable(String varName) {
		if (!stack.isEmpty()) {
			return stack.pop().contains(varName);
		}
		return false;
	}
}
