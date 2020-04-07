package casual;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;

public class Main {
	public static void main(String[] args) throws IOException {
		CodePointCharStream input = CharStreams.fromString("declmax(ola:):Int");
		//CharStream input2 = CharStreams.fromFileName("source_code.csl");
		CasualLexer lexer = new CasualLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CasualParser parser = new CasualParser(tokens);
		
		parser.setBuildParseTree(true);
		parser.addParseListener(new CasualListener());
		ParseTree p = parser.expr();
		System.out.println(p.toStringTree(parser));
		System.out.println(p.getText());

	}

}
