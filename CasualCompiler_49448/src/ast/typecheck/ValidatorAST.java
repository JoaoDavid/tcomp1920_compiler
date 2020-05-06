package ast.typecheck;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.expression.binary.AndExpression;
import ast.statement.ExprStatement;
import ast.statement.IfElseStatement;
import ast.statement.IfStatement;
import ast.statement.ReturnStatement;
import ast.statement.VarAssignStatement;
import ast.statement.VarDeclarationStatement;
import ast.statement.WhileStatement;
import ast.expression.Expression;

public class ValidatorAST {
	
	private Context ctx;
	
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
			ctx.enterScope();
			FunctionDefinition curr = (FunctionDefinition) n;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				readFunctionSignature(currFuncParam);
			}
			
		
		} else if (n instanceof FunctionParameter) {
			
		}
	}
	
	public Node validate(Node n) throws Exception {
		readFunctionSignature(n);
		if (n instanceof CasualFile) {			
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				validate(currDefDecl);
			}
		} else if (n instanceof FunctionDeclaration) {
			
		} else if (n instanceof FunctionDefinition) {
			ctx.enterScope();
			FunctionDefinition curr = (FunctionDefinition) n;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				validate(currFuncParam);
			}
			
			ctx.exitScope();			
		} else if (n instanceof FunctionParameter) {
			
		} else if (n instanceof IfStatement) {
			ctx.enterScope();
			IfStatement curr = (IfStatement) n;
			Node res = validate(curr.getCondition());
			
			ctx.exitScope();
			
		} else if (n instanceof IfElseStatement) {
			ctx.enterScope();
			
			
			ctx.exitScope();
		} else if (n instanceof WhileStatement) {
			ctx.enterScope();
			
			
			ctx.exitScope();
		} else if (n instanceof ReturnStatement) {
			
		} else if (n instanceof VarDeclarationStatement) {
			
		} else if (n instanceof VarAssignStatement) {
			
		} else if (n instanceof ExprStatement) {
			
		} else if (n instanceof IfStatement) {
			
		} else if (n instanceof IfStatement) {
			
		} else if (n instanceof IfStatement) {
			
		} else if (n instanceof IfStatement) {
			
		}
		return null;
		
	}
	
	private boolean validBooleanExpr(Expression expr) {
		
		
		
		return false;
		
	}
	

}
