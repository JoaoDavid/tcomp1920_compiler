package casual;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;

public class CasualListener implements ParseTreeListener {

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		
		int ruleIndex = arg0.getRuleIndex();
		String ruleName = CasualParser.ruleNames[ruleIndex];
		
		System.out.println("Non-Terminal: " + arg0.getText());
		System.out.println("Type of Token:" + ruleName);
		System.out.println("....");
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		
		int index = arg0.getSymbol().getType();
		/*String type = CasualLexer.VOCABULARY.getDisplayName(index);
		
		System.out.println("Found literal:" + arg0.getText());
		System.out.println("Type:" + type);
		System.out.println("....");*/
	}

}
