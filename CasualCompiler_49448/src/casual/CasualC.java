package casual;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;
import casual.grammar.CasualParser.ProgramContext;
import visitor.CasualParseTreeVisitor;

public class CasualC {
	public static void main(String[] args) {
		if(args.length == 1) {
			System.out.println("Analysing " + args[0]);
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
			//parser.addParseListener(new CasualListener()); //debug
			//parser.addErrorListener(new CasualErrorListener());
			ProgramContext tree = parser.program();			
			CasualParseTreeVisitor casualVisitor = new CasualParseTreeVisitor();
			System.out.println("num child nodes " + tree.getChildCount());
			casualVisitor.visitCasualFile(tree);
			System.out.println("\nFinished Execution");
		}else {
			System.out.println("Your args are not correct");
			System.out.println("Valid args: <sourceFile>");
			System.out.println("example: .\\files\\HelloWorld.cas");
		}
	}

}
