package visitor;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.statement.ExprStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import casual.grammar.CasualParser.Expr_statContext;
import casual.grammar.CasualParser.Func_declContext;
import casual.grammar.CasualParser.Func_defContext;
import casual.grammar.CasualParser.If_statContext;
import casual.grammar.CasualParser.ProgramContext;
import casual.grammar.CasualParser.Return_statContext;
import casual.grammar.CasualParser.StatementContext;
import casual.grammar.CasualParser.Var_assign_statContext;
import casual.grammar.CasualParser.Var_decl_statContext;
import casual.grammar.CasualParser.Var_typeContext;
import casual.grammar.CasualParser.While_statContext;

public class CasualParseTreeVisitor {
	
	public CasualFile visitCasualFile(ProgramContext ctx) {
		List<DefDecl> statements = new ArrayList<>(ctx.getChildCount()-1);
		for (ParseTree currChildren : ctx.children) {
			if(currChildren instanceof Func_declContext) {
				FunctionDeclaration funcDecl = visitFunctionDeclaration((Func_declContext)currChildren);
				statements.add(funcDecl);
			}
			if(currChildren instanceof Func_defContext) {
				FunctionDeclaration funcDecl = visitFunctionDefinition((Func_defContext)currChildren);
				statements.add(funcDecl);
			}
		}		
		return new CasualFile(statements);
	}
	
	private FunctionDeclaration visitFunctionDeclaration(Func_declContext ctx) {
		String funcName = ctx.func_args().ID().getText();
		String retType = ctx.func_args().datatype().getText();
		List<FunctionParameter> parameters = new ArrayList<>();
		for (Var_typeContext currVarTypeCtx : ctx.func_args().var_type()) {
			parameters.add(visitFunctionParameter(currVarTypeCtx));
		}
		for (FunctionParameter curr : parameters) {
			System.out.println(curr.getVarName() +" --- " + curr.getDatatype());
		}
		return new FunctionDeclaration(funcName, parameters, retType);
	}
	
	private FunctionDefinition visitFunctionDefinition(Func_defContext ctx) {
		String funcName = ctx.func_args().ID().getText();
		String retType = ctx.func_args().datatype().getText();
		List<FunctionParameter> parameters = new ArrayList<>();		
		List<Statement> statements = new ArrayList<>();
		
		for (Var_typeContext currVarTypeCtx : ctx.func_args().var_type()) {
			parameters.add(visitFunctionParameter(currVarTypeCtx));
		}
		for (StatementContext currStatementCtx : ctx.statement()) {
			if(currStatementCtx.if_stat() != null) {
				statements.add(visitIfStatement(currStatementCtx.if_stat()));
			} else if(currStatementCtx.while_stat() != null) {
				statements.add(visitWhileStatement(currStatementCtx.while_stat()));
			} else if(currStatementCtx.return_stat() != null) {
				statements.add(visitReturnStatement(currStatementCtx.return_stat()));
			} else if(currStatementCtx.var_decl_stat() != null) {
				statements.add(visitVarDeclarationStatement(currStatementCtx.var_decl_stat()));
			} else if(currStatementCtx.var_assign_stat() != null) {
				statements.add(visitVarAssignStatement(currStatementCtx.var_assign_stat()));
			} else if(currStatementCtx.expr_stat() != null) {
				statements.add(visitExprStatement(currStatementCtx.expr_stat()));
			}
		}
		
		for (FunctionParameter curr : parameters) {
			System.out.println(curr.getVarName() +" --- " + curr.getDatatype());
		}
		return new FunctionDefinition(funcName, parameters, retType, null);
	}
	
	
	private FunctionParameter visitFunctionParameter(Var_typeContext ctx) {
		return new FunctionParameter(ctx.ID().getText(), ctx.datatype().getText());
	}
	
	private IfStatement visitIfStatement(If_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	
	private WhileStatement visitWhileStatement(While_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	
	private ReturnStatement visitReturnStatement(Return_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	
	private VarDeclarationStatement visitVarDeclarationStatement(Var_decl_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	
	private VarAssignStatement visitVarAssignStatement(Var_assign_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	
	private ExprStatement visitExprStatement(Expr_statContext ctx) {		
		System.out.println(ctx.getText());
		return null;
	}
	

}
