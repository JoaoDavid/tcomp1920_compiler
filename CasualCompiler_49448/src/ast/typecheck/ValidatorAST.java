package ast.typecheck;

import java.util.List;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.exception.DuplicateVarAssignException;
import ast.exception.TypeCheckException;
import ast.exception.VarNotDeclaredException;
import ast.expression.binary.AndExpression;
import ast.statement.ExprStatement;
import ast.statement.IfElseStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.VarAssignArrayStatement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import ast.expression.Expression;

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
			//validate(curr);
		} else if (n instanceof IfStatement) {
			
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
	
	private boolean validBooleanExpr(Expression expr) {
		
		
		
		return false;
		
	}
	

}
