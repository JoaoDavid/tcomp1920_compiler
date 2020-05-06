package ast.typecheck;

import java.util.List;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.exception.DuplicateVarAssignException;
import ast.exception.InvalidOperandException;
import ast.exception.TypeCheckException;
import ast.exception.VarNotDeclaredException;
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
import ast.statement.ExprStatement;
import ast.statement.IfElseStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.VarAssignArrayStatement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import casual.grammar.CasualLexer;
import casual.grammar.CasualParser;

import static ast.expression.datatype.PrimitiveDataTypes.*;

public class ValidatorAST {

	private static String RETURN_KW = "$return";
	private Context ctx;
	private List<TypeCheckException> exceptions;

	public ValidatorAST() {
		ctx = new Context();
	}

	private void readFunctionSignature(Node n) {
		if (n instanceof CasualFile) {			
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				readFunctionSignature(currDefDecl);
			}
		} else if (n instanceof FunctionDeclaration) {

		} else if (n instanceof FunctionDefinition) {
			FunctionDefinition curr = (FunctionDefinition) n;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				readFunctionSignature(currFuncParam);
			}		
		} else if (n instanceof FunctionParameter) {
			FunctionParameter curr = (FunctionParameter) n;
			ctx.set(curr.getVarName(), curr.getDatatype());
		}
	}

	public Node validate(Node n) throws TypeCheckException {
		if (n instanceof CasualFile) {			
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				validate(currDefDecl);
			}
		} else if (n instanceof FunctionDefinition) {
			ctx.enterScope();
			FunctionDefinition curr = (FunctionDefinition) n;
			ctx.set(RETURN_KW, curr.getReturnType());	
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				validate(currFuncParam);
			}
			validBody(curr.getStatements());		
			ctx.exitScope();			
		} else if (n instanceof FunctionParameter) {
			FunctionParameter curr = (FunctionParameter) n;
			if (ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException();
			}
			ctx.set(curr.getVarName(), curr.getDatatype());
		} else if (n instanceof IfStatement) {
			ctx.enterScope();
			IfStatement curr = (IfStatement) n;
			validate(curr.getCondition());		
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof IfElseStatement) {
			ctx.enterScope();
			IfElseStatement curr = (IfElseStatement) n;
			validate(curr.getCondition());		
			validBody(curr.getBody());
			validBody(curr.getBodyElse());
			ctx.exitScope();
		} else if (n instanceof WhileStatement) {
			ctx.enterScope();
			WhileStatement curr = (WhileStatement) n;
			validate(curr.getCondition());		
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof ReturnStatement) {
			ReturnStatement curr = (ReturnStatement) n;
		} else if (n instanceof VarDeclarationStatement) {
			VarDeclarationStatement curr = (VarDeclarationStatement) n;
			validate(curr.getValue());
			if (ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException();
			}
			ctx.set(curr.getVarName(), curr.getDatatype());			
		} else if (n instanceof VarAssignStatement) {
			VarAssignStatement curr = (VarAssignStatement) n;			
			if (!ctx.hasBeenDeclared(curr.getVarName())) {
				throw new VarNotDeclaredException(curr.getVarName());
			}
			validate(curr.getValue());
		} else if (n instanceof VarAssignArrayStatement) {
			VarAssignArrayStatement curr = (VarAssignArrayStatement) n;
			if (!ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException();
			}
			validate(curr.getValue());
		} else if (n instanceof ExprStatement) {
			ExprStatement curr = (ExprStatement) n;
			validate(curr.getValue());
		} else if (n instanceof Expression) {
			Expression expr = (Expression) n;
			String res = validExpression(expr);
			System.out.println(res);
		} else if (n instanceof IfStatement) {

		} else if (n instanceof IfStatement) {

		}
		return null;

	}

	private void validBody(List<Statement> statements) throws TypeCheckException {
		for (Statement currStat : statements) {
			validate(currStat);
		}
	}

	private String validExpression(Expression expr) throws TypeCheckException {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			String leftTy = validExpression(binaryExpr.getLeft());
			String rightTy = validExpression(binaryExpr.getRight());
			if (expr instanceof AndExpression) {
				if (!leftTy.equals(BOOL) || !rightTy.equals(BOOL)) {
					throw new InvalidOperandException(leftTy + "&&" + rightTy);
				}
				return BOOL;
			} else if (expr instanceof OrExpression) {
				if (!leftTy.equals(BOOL) || !rightTy.equals(BOOL)) {
					throw new InvalidOperandException(leftTy + "||" + rightTy);
				}
				return BOOL;
			} else if (expr instanceof EqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(leftTy + "==" + rightTy);
				}
				return leftTy;
			} else if (expr instanceof NotEqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(leftTy + "!=" + rightTy);
				}
				return leftTy;
			} else if (expr instanceof GreaterOrEqualExpression || expr instanceof GreaterExpression
					|| expr instanceof LessOrEqualExpression || expr instanceof LessExpression) {
				if (!leftTy.equals(INT) || !rightTy.equals(INT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				} else if (!leftTy.equals(FLOAT) || !rightTy.equals(FLOAT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				}
				return leftTy;
			} else if (expr instanceof SumExpression) {
				if (!leftTy.equals(INT) || !rightTy.equals(INT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				} else if (!leftTy.equals(FLOAT) || !rightTy.equals(FLOAT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				} else if (!leftTy.equals(STRING) || !rightTy.equals(STRING)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				}
			} else if (expr instanceof SubtractionExpression || expr instanceof MultiplicationExpression
					|| expr instanceof DivisionExpression || expr instanceof ModuloExpression) {
				if (!leftTy.equals(INT) || !rightTy.equals(INT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				} else if (!leftTy.equals(FLOAT) || !rightTy.equals(FLOAT)) {
					throw new InvalidOperandException(leftTy + "operator" + rightTy);
				} 
				return leftTy;
			}
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;
			String val = validExpression(notExpr.getValue());
			if (!val.equals(BOOL)) {
				throw new InvalidOperandException("not");
			}
		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;
			String val = validExpression(negExpr.getValue());
			if (!val.equals(INT) || !val.equals(FLOAT)) {
				throw new InvalidOperandException("neg");
			}
		} else if (expr instanceof FunctionInvocationExpression) {

		} else if (expr instanceof ArrayAcessFuncExpression) {

		} else if (expr instanceof ArrayAcessVarExpression) {

		} else if (expr instanceof BoolLit) {
			return BOOL;
		} else if (expr instanceof IntLit) {
			return INT;
		} else if (expr instanceof FloatLit) {
			return FLOAT;
		} else if (expr instanceof StringLit) {
			return STRING;
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;
			String type = ctx.get(varExpr.getVarName());
			if (type == null) {
				throw new VarNotDeclaredException();
			}
			return type;
		}


		return null;		
	}

	private String equalOperandTypes(String leftTy, String rightTy, String type) throws TypeCheckException {
		if (!leftTy.equals(type) || !rightTy.equals(type)) {
			throw new InvalidOperandException(leftTy + " and " + rightTy + " must be of type " + type);
		}
		return leftTy;
	}

}
