package casual;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.Node;
import ast.exception.SyntacticException;
import ast.typecheck.ValidatorAST;
import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;
import casual.grammar.CasualParser.ProgramContext;
import codegen.Codegenator;
import visitor.CasualParseTreeVisitor;

public class CasualC {
	public static void main(String[] args) {
		if(args.length == 1 || args.length == 2) {
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
			Node ast = casualVisitor.visitCasualFile(tree);
			ValidatorAST validatorAST = new ValidatorAST();
			Codegenator codegen;
			if (args.length == 2) {
				codegen = new Codegenator(ast, "file", args[1]);
			} else {
				codegen = new Codegenator(ast,  args[0].replace(".cas", ""));
			}
			
			try {
				validatorAST.validateAST(ast);
				codegen.generateLL();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
				System.err.println("Syntactic Verification found an error");
				return;
			}
			System.out.println("\nFinished Execution Successfully");
		}else {
			System.out.println("Your args are not correct");
			System.out.println("Valid args: <sourceFile>");
			System.out.println("example: .\\cas_files\\hello_world.cas");
		}
	}

	public static void mainTest(String filePath) throws SyntacticException {
		CharStream inputFromFile;
		try {
			inputFromFile = CharStreams.fromFileName(filePath);
		} catch (IOException e) {
			System.out.println("Source File not found");
			return;
		}
		CasualLexer lexer = new CasualLexer(inputFromFile);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CasualParser parser = new CasualParser(tokens);

		parser.setBuildParseTree(true);
		ProgramContext tree = parser.program();			
		CasualParseTreeVisitor casualVisitor = new CasualParseTreeVisitor();
		Node ast = casualVisitor.visitCasualFile(tree);
		ValidatorAST validatorAST = new ValidatorAST();
		validatorAST.validateAST(ast);
	}

}
