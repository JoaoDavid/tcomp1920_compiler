package ast.typecheck;

import static ast.expression.datatype.PrimitiveDataTypes.BOOL;
import static ast.expression.datatype.PrimitiveDataTypes.FLOAT;
import static ast.expression.datatype.PrimitiveDataTypes.INT;
import static ast.expression.datatype.PrimitiveDataTypes.STRING;

import java.util.List;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.exception.DuplicateVarAssignException;
import ast.exception.FunctiontArgumentsException;
import ast.exception.InvalidOperandException;
import ast.exception.MissingReturnStatementException;
import ast.exception.SyntacticException;
import ast.exception.TypeMismatchException;
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

public class ValidatorAST {

	private static final String RETURN_KW = "$return";
	private static final String VOID = "Void";
	private Context ctx;
	private FuncSignContext funcSignCtx;
	private List<SyntacticException> exceptions;
	/*
	 * TODO
	 replace all throw new Exception with exception.add(new Exception)
	 then implement a getter for this variable an print the list at the end
	 this way we can see all the errors without one error preventing us
	 from knowing what else is wrong
	 */

	public ValidatorAST() {
		ctx = new Context();
		funcSignCtx = new FuncSignContext();
	}

	public void validateAST(Node n) throws SyntacticException {
		validateFuncSign(n);
		validate(n);
	}

	private void validateFuncSign(Node n) throws SyntacticException {
		if (n instanceof CasualFile) {			
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				validateFuncSign(currDefDecl);
			}
		} else if (n instanceof FunctionDefinition) {
			FunctionDefinition curr = (FunctionDefinition) n;
			String[] datatypes = new String[curr.getParameters().size()];			
			int i = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				datatypes[i] = currFuncParam.getDatatype();
				i++;
			}
			funcSignCtx.newFunc(curr.getFuncName(), curr.getReturnType(), datatypes);
		} else if (n instanceof FunctionDeclaration) {			
			FunctionDeclaration curr = (FunctionDeclaration) n;
			String[] datatypes = new String[curr.getParameters().size()];			
			int i = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				datatypes[i] = currFuncParam.getDatatype();
				i++;
			}
			funcSignCtx.newFunc(curr.getFuncName(), curr.getReturnType(), datatypes);
		}
	}

	private void validate(Node n) throws SyntacticException {
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
			boolean hasSeenRet = false;
			for (Statement currStat : curr.getStatements()) {
				validate(currStat);
				if(currStat instanceof ReturnStatement) {
					hasSeenRet = true;
				}
			}
			if (!hasSeenRet && !curr.getReturnType().equals(VOID)) {
				throw new MissingReturnStatementException(curr.getPosition().toString());
			}
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
			if (!validExpression(curr.getCondition()).equals(BOOL)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof IfElseStatement) {
			ctx.enterScope();
			IfElseStatement curr = (IfElseStatement) n;
			if (!validExpression(curr.getCondition()).equals(BOOL)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}	
			validBody(curr.getBody());
			validBody(curr.getBodyElse());
			ctx.exitScope();
		} else if (n instanceof WhileStatement) {
			ctx.enterScope();
			WhileStatement curr = (WhileStatement) n;
			if (!validExpression(curr.getCondition()).equals(BOOL)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof ReturnStatement) {
			ReturnStatement curr = (ReturnStatement) n;
			String expectedRetType = ctx.get(RETURN_KW);
			if (curr.getValue() == null) { //no expr in return statement
				if (expectedRetType.equals(VOID)) {
					return;
				}
			} else {
				String actualRetType = validExpression(curr.getValue());
				if (expectedRetType.equals(actualRetType)) {
					return;
				}
			}
			throw new TypeMismatchException(curr.getPosition().toString());
		} else if (n instanceof VarDeclarationStatement) {
			VarDeclarationStatement curr = (VarDeclarationStatement) n;
			validExpression(curr.getValue());
			if (ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException();
			} else {
				ctx.set(curr.getVarName(), curr.getDatatype());
			}
			if(!validExpression(curr.getValue()).equals(ctx.get(curr.getVarName()))) {
				throw new TypeMismatchException(curr.getPosition().toString() + 
						validExpression(curr.getValue()) + ctx.get(curr.getVarName()));
			}
			ctx.set(curr.getVarName(), curr.getDatatype());			
		} else if (n instanceof VarAssignStatement) {
			VarAssignStatement curr = (VarAssignStatement) n;			
			if (!ctx.hasBeenDeclared(curr.getVarName())) {
				throw new VarNotDeclaredException(curr.getVarName());
			}
			if (n instanceof VarAssignArrayStatement) {
				VarAssignArrayStatement currArr = (VarAssignArrayStatement) n;	
				for (Expression currIndex : currArr.getIndexes()) {
					if(!validExpression(currIndex).equals(INT)) {
						throw new TypeMismatchException(currArr.getPosition().toString());
					}
				}				
				String type = ctx.get(currArr.getVarName());
				int indexCount = currArr.getIndexes().size();
				int count = 0;
				for (int i = 0; i < type.length(); i++) {
				    if (type.charAt(i) == '[') {
				        count++;
				    }
				}
				if (indexCount > count) {
					throw new TypeMismatchException(currArr.getPosition().toString());
				} else {
					if (!type.substring(indexCount, type.length()-indexCount).equals(validExpression(currArr.getValue()))) {
						throw new TypeMismatchException(currArr.getPosition().toString());
					}
				}				
			} else {
				if(!validExpression(curr.getValue()).equals(ctx.get(curr.getVarName()))) {
					throw new TypeMismatchException(curr.getPosition().toString());
				}
			}
		} else if (n instanceof ExprStatement) {
			ExprStatement curr = (ExprStatement) n;
			validExpression(curr.getValue());
		}
	}

	private void validBody(List<Statement> statements) throws SyntacticException {
		for (Statement currStat : statements) {
			validate(currStat);
		}
	}

	private String validExpression(Expression expr) throws SyntacticException {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			String leftTy = validExpression(binaryExpr.getLeft());
			String rightTy = validExpression(binaryExpr.getRight());
			if (expr instanceof AndExpression || expr instanceof OrExpression) {
				if (!leftTy.equals(BOOL) || !rightTy.equals(BOOL)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				return BOOL;
			} else if (expr instanceof EqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				return leftTy;
			} else if (expr instanceof NotEqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				return leftTy;
			} else if (expr instanceof GreaterOrEqualExpression || expr instanceof GreaterExpression
					|| expr instanceof LessOrEqualExpression || expr instanceof LessExpression) {
				if (leftTy.equals(rightTy) && (leftTy.equals(INT) || leftTy.equals(FLOAT))) {
					return leftTy;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof SumExpression) {
				if (leftTy.equals(rightTy) && (leftTy.equals(INT) || leftTy.equals(FLOAT)
						|| leftTy.equals(STRING))) {
					return leftTy;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof SubtractionExpression || expr instanceof MultiplicationExpression
					|| expr instanceof DivisionExpression) {
				if (leftTy.equals(rightTy) && (leftTy.equals(INT) || leftTy.equals(FLOAT))) {
					return leftTy;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof ModuloExpression) {
				if (!leftTy.equals(INT) || !rightTy.equals(INT)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				return INT;
			} 
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;
			String type = validExpression(notExpr.getValue());
			if (!type.equals(BOOL)) {
				throw new InvalidOperandException(expr.getPosition().toString());
			}
			return type;
		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;
			String type = validExpression(negExpr.getValue());
			if (!type.equals(INT) || !type.equals(FLOAT)) {
				throw new InvalidOperandException(expr.getPosition().toString());
			}
			return type;
		} else if (expr instanceof FunctionInvocationExpression) {
			FunctionInvocationExpression funcInvExpr = (FunctionInvocationExpression) expr;
			String[] datatypes = funcSignCtx.getDataTypes(funcInvExpr.getFuncName());
			if (datatypes.length != funcInvExpr.getArguments().size()) {
				throw new FunctiontArgumentsException(funcInvExpr.getPosition().toString());
			}
			int i = 0;
			for (Expression currExpr : funcInvExpr.getArguments()) {
				if(!validExpression(currExpr).equals(datatypes[i])){
					throw new FunctiontArgumentsException(funcInvExpr.getPosition().toString());
				}
				i++;
			}
			return funcSignCtx.getRetType(funcInvExpr.getFuncName());
		} else if (expr instanceof ArrayAcessFuncExpression) {
			ArrayAcessFuncExpression arrAcFuncExpr = (ArrayAcessFuncExpression) expr;
			String[] datatypes = funcSignCtx.getDataTypes(arrAcFuncExpr.getVarName());
			if (datatypes.length != arrAcFuncExpr.getArguments().size()) {
				throw new FunctiontArgumentsException(arrAcFuncExpr.getPosition().toString());
			}
			int i = 0;
			for (Expression currExpr : arrAcFuncExpr.getArguments()) {
				if(!validExpression(currExpr).equals(datatypes[i])){
					throw new FunctiontArgumentsException(arrAcFuncExpr.getPosition().toString());
				}
				i++;
			}
			String type = funcSignCtx.getRetType(arrAcFuncExpr.getVarName());
			for (Expression currIndex : arrAcFuncExpr.getIndexes()) {
				if(!validExpression(currIndex).equals(INT)) {
					throw new TypeMismatchException(expr.getPosition().toString());
				}
			}
			int indexCount = arrAcFuncExpr.getIndexes().size();
			int count = 0;
			for (int j = 0; j < type.length(); j++) {
			    if (type.charAt(j) == '[') {
			        count++;
			    }
			}
			if (indexCount > count) {
				throw new TypeMismatchException(arrAcFuncExpr.getPosition().toString());
			} else {
				return type.substring(indexCount, type.length()-indexCount);
			}
		} else if (expr instanceof ArrayAcessVarExpression) {
			ArrayAcessVarExpression arrExpr = (ArrayAcessVarExpression) expr;
			String type = ctx.get(arrExpr.getVarName());
			if (type == null) {
				throw new VarNotDeclaredException(arrExpr.getPosition().toString());
			}
			for (Expression currIndex : arrExpr.getIndexes()) {
				if(!validExpression(currIndex).equals(INT)) {
					throw new TypeMismatchException(expr.getPosition().toString());
				}
			}
			int indexCount = arrExpr.getIndexes().size();
			int count = 0;
			for (int i = 0; i < type.length(); i++) {
			    if (type.charAt(i) == '[') {
			        count++;
			    }
			}
			if (indexCount > count) {
				throw new TypeMismatchException(arrExpr.getPosition().toString());
			} else {
				return type.substring(indexCount, type.length()-indexCount);
			}
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
				throw new VarNotDeclaredException(varExpr.getPosition().toString());
			}
			return type;
		}
		return null;		
	}
	
}
