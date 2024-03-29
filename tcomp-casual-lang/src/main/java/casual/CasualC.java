package casual;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.CasualFile;
import ast.DefDecl;
import ast.typecheck.ValidatorAST;
import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;
import casual.grammar.CasualParser.ProgramContext;
import codegen.Codegenator;
import visitor.CasualParseTreeVisitor;

public class CasualC {
	public static void main(String[] args) {
		if(args.length >= 1) {
			System.out.println("Compiling... ");
			HashMap<String,CasualFile> casTree = new HashMap<String,CasualFile>();
			CasualFile[] arrCas = new CasualFile[args.length];
			String outputName = "out";
			for (int i = 0; i < args.length; i++) {
				CharStream inputFromFile;
				File casFile;
				try {
					casFile = new File(args[i]);
					if(i == 0) {
						outputName = casFile.getName().replaceAll("\\.[^.]*$", "");
					}
					inputFromFile = CharStreams.fromFileName(casFile.getPath());
					System.out.println(args[i]);
				} catch (IOException e) {
					System.err.println("Source File " + args[i] + " not found");
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
				arrCas[i] = casualVisitor.visitCasualFile(tree);
				CasualFile temp = casTree.put(casFile.getName(), arrCas[i]);
				if (temp != null) {
					System.err.println("Duplicate Cas File " + args[i]);
					return;
				}
			}
			List<DefDecl> defDecls = new ArrayList<DefDecl>();
			for (int i = 0; i < arrCas.length; i++) {
				ValidatorAST validatorAST = new ValidatorAST(arrCas[i], casTree);
				try {
					validatorAST.validateAST();
					for (DefDecl curr : arrCas[i].getStatements()) {
						if (!defDecls.contains(curr)) {
							defDecls.add(curr);
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
					System.err.println(e.toString() + " when validating: " + args[i] + " AST");
					System.err.println("Syntactic Verification found an error");
					return;
				}
			}
			CasualFile resCas = new CasualFile(defDecls);

			
			try {
				//Codegenator codegen = new Codegenator(resCas, outputName, "C:" + File.separator + "Users"+ File.separator +"PC"+ File.separator +"Desktop"+ File.separator +"SharedFolder");
				Codegenator codegen = new Codegenator(resCas, outputName);
				codegen.generateLL();
			} catch (Exception e) {
				//e.printStackTrace();
				System.err.println(e.toString());
				System.err.println("Error generating LLVM file");
				return;
			}

			System.out.println("\nFinished Compilation Successfully");
		}else {
			System.err.println("Your args are not correct");
			System.err.println("Valid args: <sourceFile>");
			System.err.println("example: ..\\cas_files\\hello_world.cas");
		}
	}

}
