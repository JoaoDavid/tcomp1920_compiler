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
		CodePointCharStream input = CharStreams.fromString("decl    max(ola:Int,  ola:Int):Int");
		//CharStream inputFromFile = CharStreams.fromFileName("source_code.cas");
		CharStream inputFromFile = CharStreams.fromFileName("benchmark.cas");
		
		//CasualLexer lexer = new CasualLexer(input);
		CasualLexer lexer = new CasualLexer(inputFromFile);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CasualParser parser = new CasualParser(tokens);
		
		parser.setBuildParseTree(true);
		parser.addParseListener(new CasualListener());
		ParseTree p = parser.prog();
		System.out.println(p.toStringTree(parser));
		System.out.println(p.getText());

	}

}
