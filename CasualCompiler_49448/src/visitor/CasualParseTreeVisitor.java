package visitor;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Point;
import ast.Position;
import ast.expression.ArrayAcessFuncExpression;
import ast.expression.ArrayAcessVarExpression;
import ast.expression.Expression;
import ast.expression.FunctionInvocationExpression;
import ast.expression.VarReferenceExpression;
import ast.expression.binary.AndExpression;
import ast.expression.binary.BinaryExpression;
import ast.expression.binary.DivisionExpression;
import ast.expression.binary.EqualExpression;
import ast.expression.binary.GreaterExpression;
import ast.expression.binary.GreaterOrEqualExpression;
import ast.expression.binary.LessExpression;
import ast.expression.binary.LessOrEqualExpression;
import ast.expression.binary.ModuloExpression;
import ast.expression.binary.MultiplicationExpression;
import ast.expression.binary.NotEqualExpression;
import ast.expression.binary.OrExpression;
import ast.expression.binary.SubtractionExpression;
import ast.expression.binary.SumExpression;
import ast.expression.literal.BoolLit;
import ast.expression.literal.FloatLit;
import ast.expression.literal.IntLit;
import ast.expression.literal.StringLit;
import ast.expression.unary.NegativeExpression;
import ast.expression.unary.NotExpression;
import ast.expression.unary.UnaryExpression;
import ast.statement.ExprStatement;
import ast.statement.IfElseStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.VarAssignArrayStatement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import casual.grammar.CasualParser.Arr_l_valueContext;
import casual.grammar.CasualParser.Arr_r_valueContext;
import casual.grammar.CasualParser.ExprContext;
import casual.grammar.CasualParser.Expr_statContext;
import casual.grammar.CasualParser.Func_declContext;
import casual.grammar.CasualParser.Func_defContext;
import casual.grammar.CasualParser.Func_invContext;
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
		return new CasualFile(statements, new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
				new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
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
		return new FunctionDeclaration(funcName, parameters, retType,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
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
		return new FunctionDefinition(funcName, parameters, retType, statements,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
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
			return new IfElseStatement(expr, statementsIf, statementsElse,
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
		} else {
			return new IfStatement(expr, statementsIf,
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
		}
	}
	
	private WhileStatement visitWhileStatement(While_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		List<Statement> statements = new ArrayList<>(ctx.statement().size());
		for (StatementContext currStatementCtx : ctx.statement()) {
			statements.add(visitStatement(currStatementCtx));
		}
		return new WhileStatement(expr, statements,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}
	
	private ReturnStatement visitReturnStatement(Return_statContext ctx) {
		if (ctx.expr() == null) {
			return new ReturnStatement(
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine()))); //void return
		} else {
			Expression expr = visitExpression(ctx.expr());
			return new ReturnStatement(expr,
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
		}		
	}
	
	private VarDeclarationStatement visitVarDeclarationStatement(Var_decl_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		return new VarDeclarationStatement(ctx.var_type().ID().getText(), ctx.var_type().datatype().getText(), expr,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}
	
	private VarAssignStatement visitVarAssignStatement(Var_assign_statContext ctx) {		
		Expression expr = visitExpression(ctx.expr());
		if (ctx.ID() != null) {
			return new VarAssignStatement(ctx.ID().getText(), expr,
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
		} else if (ctx.arr_l_value() != null) {
			Expression value = visitExpression(ctx.expr());
			List<Expression> indexes = new ArrayList<>(ctx.arr_l_value().expr().size());
			for (ExprContext currIndex : ctx.arr_l_value().expr()) {
				indexes.add(visitExpression((currIndex)));
			}
			return new VarAssignArrayStatement(ctx.arr_l_value().ID().getText(), value, indexes,
					new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
							new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
		}
		return null;
	}
	
	private ExprStatement visitExprStatement(Expr_statContext ctx) {
		Expression expr = visitExpression(ctx.expr());
		return new ExprStatement(expr,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}
	
	private Expression visitExpression(ExprContext ctx) {
		Position pos = new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
				new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine()));
		System.out.println(ctx.getText());
		if(ctx.binary_ope() != null) {
			return visitBinaryExpression(ctx);
		} else if(ctx.unary_ope() != null) {
			return visitUnaryExpression(ctx);
		} else if(ctx.func_inv() != null) {
			return visitFunctionInvocation(ctx.func_inv());
		} else if(ctx.arr_r_value() != null) {
			if (ctx.arr_r_value().arr_l_value() != null) {
				return visitArrayAcessVarExpression(ctx.arr_r_value().arr_l_value());
			} else {
				return visitArrayAcessFuncExpression(ctx.arr_r_value());
			}
		} else if(ctx.arr_l_value() != null) {
			return visitArrayAcessVarExpression(ctx.arr_l_value());
		} else if(ctx.BOOL() != null) {
			return new BoolLit(ctx.BOOL().getText(), pos);
		}else if(ctx.INT() != null) {
			return new IntLit(ctx.INT().getText(), pos);
		}else if(ctx.FLOAT() != null) {
			return new FloatLit(ctx.FLOAT().getText(), pos);
		}else if(ctx.STRING() != null) {
			return new StringLit(ctx.STRING().getText(), pos);
		}else if(ctx.ID() != null) {
			return new VarReferenceExpression(ctx.ID().getText(), pos);
		}else if(ctx.L_RND_BR() != null) {
			return visitExpression(ctx.expr(0));
		}
		return null;
	}
	
	private BinaryExpression visitBinaryExpression(ExprContext ctx) {
		Position pos = new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
				new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine()));
		Expression left = visitExpression(ctx.expr(0));
		Expression right = visitExpression(ctx.expr(1));
		if(ctx.binary_ope().AND() != null) {
			return new AndExpression(left, right, pos);
		} else if(ctx.binary_ope().OR() != null) {
			return new OrExpression(left, right, pos);
		} else if(ctx.binary_ope().EQUAL() != null) {
			return new EqualExpression(left, right, pos);
		} else if(ctx.binary_ope().NOT_EQUAL() != null) {
			return new NotEqualExpression(left, right, pos);
		} else if(ctx.binary_ope().GREATER_EQ() != null) {
			return new GreaterOrEqualExpression(left, right, pos);
		} else if(ctx.binary_ope().GREATER() != null) {
			return new GreaterExpression(left, right, pos);
		} else if(ctx.binary_ope().LESS_EQ() != null) {
			return new LessOrEqualExpression(left, right, pos);
		} else if(ctx.binary_ope().LESS() != null) {
			return new LessExpression(left, right, pos);
		} else if(ctx.binary_ope().PLUS() != null) {
			return new SumExpression(left, right, pos);
		} else if(ctx.binary_ope().MINUS() != null) {
			return new SubtractionExpression(left, right, pos);
		} else if(ctx.binary_ope().TIMES() != null) {
			return new MultiplicationExpression(left, right, pos);
		} else if(ctx.binary_ope().DIV() != null) {
			return new DivisionExpression(left, right, pos);
		} else if(ctx.binary_ope().MOD() != null) {
			return new ModuloExpression(left, right, pos);
		}
		return null;
	}
	
	private UnaryExpression visitUnaryExpression(ExprContext ctx) {
		Position pos = new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
				new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine()));
		Expression expr = visitExpression(ctx.expr(0));
		if(ctx.unary_ope().NOT() != null) {
			return new NotExpression(expr, pos);
		} else if(ctx.unary_ope().MINUS() != null) {
			return new NegativeExpression(expr, pos);
		} 
		return null;
	}
	
	private FunctionInvocationExpression visitFunctionInvocation(Func_invContext ctx) {
		String funcName = ctx.ID().getText();
		List<Expression> arguments = new ArrayList<>(ctx.expr().size());
		
		for (ExprContext currExpr : ctx.expr()) {
			arguments.add(visitExpression(currExpr));
		}
		return new FunctionInvocationExpression(funcName, arguments,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}
	
	private ArrayAcessVarExpression visitArrayAcessVarExpression(Arr_l_valueContext ctx) {
		List<Expression> indexes = new ArrayList<>(ctx.expr().size());
		for (ExprContext currIndex : ctx.expr()) {
			indexes.add(visitExpression((currIndex)));
		}
		return new ArrayAcessVarExpression(ctx.ID().getText(),indexes,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}
	
	private ArrayAcessFuncExpression visitArrayAcessFuncExpression(Arr_r_valueContext ctx) {
		List<Expression> indexes = new ArrayList<>(ctx.expr().size());
		for (ExprContext currIndex : ctx.expr()) {
			indexes.add(visitExpression((currIndex)));
		}
		List<Expression> arguments = new ArrayList<>(ctx.func_inv().expr().size());
		for (ExprContext currArg : ctx.expr()) {
			arguments.add(visitExpression(currArg));
		}
		return new ArrayAcessFuncExpression(ctx.func_inv().ID().getText(), indexes, arguments,
				new Position(new Point(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()), 
						new Point(ctx.getStop().getLine(), ctx.getStop().getCharPositionInLine())));
	}

}
