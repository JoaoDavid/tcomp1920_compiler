package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.expression.Expression;
import ast.statement.ExprStatement;
import ast.statement.IfElseStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.VarAssignArrayStatement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import casual.grammar.CasualParser.Binary_opeContext;
import casual.grammar.CasualParser.ExprContext;
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
			statements.add(visitStatement(currStatementCtx));
		}
		return new FunctionDefinition(funcName, parameters, retType, null);
	}
	
	/**
	 * Visits the casual def/decl function's parameter context
	 * 
	 * @param ctx
	 * @return FunctionParameter Node
	 */
	private FunctionParameter visitFunctionParameter(Var_typeContext ctx) {
		return new FunctionParameter(ctx.ID().getText(), ctx.datatype().getText());
	}
	
	private Statement visitStatement(StatementContext ctx) {
		if(ctx.if_stat() != null) {
			return visitIfStatement(ctx.if_stat());
		} else if(ctx.while_stat() != null) {
			return visitWhileStatement(ctx.while_stat());
		} else if(ctx.return_stat() != null) {
			return visitReturnStatement(ctx.return_stat());
		} else if(ctx.var_decl_stat() != null) {
			return visitVarDeclarationStatement(ctx.var_decl_stat());
		} else if(ctx.var_assign_stat() != null) {
			return visitVarAssignStatement(ctx.var_assign_stat());
		} else if(ctx.expr_stat() != null) {
			return visitExprStatement(ctx.expr_stat());
		}
		return null;
	}
	
	private IfStatement visitIfStatement(If_statContext ctx) {	
		Expression expr = visitExpression(ctx.expr());
		List<Statement> statementsIf = new ArrayList<>(ctx.statement().size());
		for (StatementContext currStatementCtx : ctx.statement()) {
			statementsIf.add(visitStatement(currStatementCtx));
		}
		if (ctx.else_block() != null) { //has the else block
			List<Statement> statementsElse = new ArrayList<>(ctx.statement().size());
			for (StatementContext currStatementCtx : ctx.statement()) {
				statementsElse.add(visitStatement(currStatementCtx));
			}
			return new IfElseStatement(expr, statementsIf, statementsElse);
		} else {
			return new IfStatement(expr, statementsIf);
		}
	}
	
	private WhileStatement visitWhileStatement(While_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		List<Statement> statements = new ArrayList<>(ctx.statement().size());
		for (StatementContext currStatementCtx : ctx.statement()) {
			statements.add(visitStatement(currStatementCtx));
		}
		return new WhileStatement(expr, statements);
	}
	
	private ReturnStatement visitReturnStatement(Return_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		return new ReturnStatement(expr);
	}
	
	private VarDeclarationStatement visitVarDeclarationStatement(Var_decl_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		return new VarDeclarationStatement(ctx.var_type().ID().getText(), ctx.var_type().datatype().getText(), expr);
	}
	
	private VarAssignStatement visitVarAssignStatement(Var_assign_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		if (ctx.ID() != null) {
			return new VarAssignStatement(ctx.ID().getText(), expr);
		} else if (ctx.arr_l_value() != null) {
			Expression value = visitExpression(ctx.expr());
			List<Expression> indexes = new ArrayList<>(ctx.arr_l_value().expr().size());
			for (ExprContext currIndex : ctx.arr_l_value().expr()) {
				indexes.add(visitExpression((currIndex)));
			}
			return new VarAssignArrayStatement(ctx.arr_l_value().ID().getText(), value, indexes);
		}
		return null;
	}
	
	private ExprStatement visitExprStatement(Expr_statContext ctx) {
		Expression expr = visitExpression(ctx.expr());
		return new ExprStatement(expr);
	}
	
	private Expression visitExpression(ExprContext ctx) {
		Stack<ParseTree> stack = new Stack<ParseTree>();
		for (ParseTree curr : ctx.children) {
			if (curr instanceof Expr_statContext) {
				stack.push(curr);
			} else if(curr instanceof Binary_opeContext) {
				//return visitWhileStatement(ctx.while_stat());
			} 
		}		
		if(ctx.binary_ope() != null) {
			//return visitIfStatement(ctx.if_stat());
		} else if(ctx.unary_ope() != null) {
			//return visitWhileStatement(ctx.while_stat());
		} else if(ctx.func_inv() != null) {
			//return visitReturnStatement(ctx.return_stat());
		} else if(ctx.arr_r_value() != null) {
			//return visitVarDeclarationStatement(ctx.var_decl_stat());
		} else if(ctx.arr_l_value() != null) {
			//return visitVarAssignStatement(ctx.var_assign_stat());
		} else if(ctx.BOOL() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}else if(ctx.INT() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}else if(ctx.FLOAT() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}else if(ctx.STRING() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}else if(ctx.ID() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}else if(ctx.L_RND_BR() != null) {
			//return visitExprStatement(ctx.expr_stat());
		}
		return null;
	}

}
