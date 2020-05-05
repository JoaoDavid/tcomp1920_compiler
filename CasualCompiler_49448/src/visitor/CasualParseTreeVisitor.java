package visitor;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionParameter;
import ast.statement.VarDeclarationStatement;
import casual.grammar.CasualParser.Func_declContext;
import casual.grammar.CasualParser.Func_defContext;
import casual.grammar.CasualParser.ProgramContext;
import casual.grammar.CasualParser.Var_typeContext;

public class CasualParseTreeVisitor {
	
	public CasualFile visitCasualFile(ProgramContext ctx) {
		List<DefDecl> statements = new ArrayList<>(ctx.getChildCount()-1);
		for (ParseTree currChildren : ctx.children) {
			if(currChildren instanceof Func_declContext) {
				System.out.println(currChildren.getText());
				FunctionDeclaration funcDecl = visitFunctionDeclaration((Func_declContext)currChildren);
				statements.add(funcDecl);
			}
			if(currChildren instanceof Func_defContext) {
				System.out.println(currChildren.getText());
			}

		}

		
		return new CasualFile(statements);
	}
	
	private FunctionDeclaration visitFunctionDeclaration(Func_declContext ctx) {
		String funcName = ctx.func_args().ID().getText();
		String retType = ctx.func_args().datatype().getText();
		List<FunctionParameter> parameters = new ArrayList<>();
		for (Var_typeContext currVarTypeCtx : ctx.func_args().var_type()) {
			System.out.println("egeg "  + currVarTypeCtx.getText());
			parameters.add(new FunctionParameter(currVarTypeCtx.ID().getText(), currVarTypeCtx.datatype().getText(), null));
		}
		for (VarDeclarationStatement curr : parameters) {
			System.out.println(curr.getVarName() +" --- " + curr.getDatatype());
		}
		System.out.println("funcName " + funcName);
		System.out.println("retType " + retType);
		return new FunctionDeclaration(funcName, parameters, retType);
	}
	
	
	private VarDeclarationStatement visitVarDeclaration() {
		return null;
	}
	

}
