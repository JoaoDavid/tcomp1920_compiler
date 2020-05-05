package visitor;

import org.antlr.v4.runtime.tree.ParseTree;

import casual.grammar.CasualBaseVisitor;
import casual.grammar.CasualParser;
import casual.grammar.CasualParser.Func_declContext;

public class MyCasualVisitor extends CasualBaseVisitor<Boolean> {
	
	

	@Override
	public Boolean visitProgram(CasualParser.ProgramContext ctx) {
		for (ParseTree parseTree : ctx.children) {
			/*System.out.println("child count " + parseTree.getChildCount());
			System.out.println(parseTree.toStringTree());*/
			//System.out.println(parseTree.getText());
		}
		for (Func_declContext curr : ctx.func_decl()) {
			System.out.println(curr);
		}
		return null;
		
	}
	
	
}
