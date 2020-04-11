package casual;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;

public class CasualC {
	public static void main(String[] args) {
		if(args.length == 1) {
			CharStream inputFromFile;
			try {
				inputFromFile = CharStreams.fromFileName(args[0]);
			} catch (IOException e) {
				System.out.println("Source File not found");
				return;
			}
			CasualLexer lexer = new CasualLexer(inputFromFile);

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CasualParser parser = new CasualParser(tokens);

			parser.setBuildParseTree(true);
			parser.addParseListener(new CasualListener());
			ParseTree p = parser.prog();
			System.out.println(p.toStringTree(parser));
			System.out.println(p.getText());
		}else {
			System.out.println("Your args are not correct");
			System.out.println("Valid args: <sourceFile>");
			System.out.println("example: .\\files\\HelloWorld.cas");
		}




	}

}
