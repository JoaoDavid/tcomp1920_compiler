package ast.typecheck;


import java.util.List;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.Position;
import ast.datatype.ArrayType;
import ast.datatype.BoolType;
import ast.datatype.CustomType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;
import ast.exception.DuplicateVarAssignException;
import ast.exception.FunctiontArgumentsException;
import ast.exception.InvalidOperandException;
import ast.exception.InvalidTypeException;
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
	private Context ctx;
	private FuncSignContext funcSignCtx;

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
			Type[] datatypes = new Type[curr.getParameters().size()];			
			int i = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				validType(currFuncParam.getDatatype(), currFuncParam.getPosition());
				datatypes[i] = currFuncParam.getDatatype();
				i++;
			}			
			funcSignCtx.newFunc(curr.getFuncName(), curr.getReturnType(), datatypes);
		} else if (n instanceof FunctionDeclaration) {			
			FunctionDeclaration curr = (FunctionDeclaration) n;
			Type[] datatypes = new Type[curr.getParameters().size()];			
			int i = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				validType(currFuncParam.getDatatype(), currFuncParam.getPosition());
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
			if (!hasSeenRet && !(curr.getReturnType() instanceof VoidType)) {
				throw new MissingReturnStatementException(curr.getPosition().toString());
			}
			ctx.exitScope();			
		} else if (n instanceof FunctionParameter) {
			FunctionParameter curr = (FunctionParameter) n;
			if (ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException(curr.getPosition().toString());
			}
			ctx.set(curr.getVarName(), curr.getDatatype());
		} else if (n instanceof IfStatement) {
			ctx.enterScope();
			IfStatement curr = (IfStatement) n;
			if (!(validExpression(curr.getCondition()) instanceof BoolType)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof IfElseStatement) {
			ctx.enterScope();
			IfElseStatement curr = (IfElseStatement) n;
			if (!(validExpression(curr.getCondition()) instanceof BoolType)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}	
			validBody(curr.getBody());
			validBody(curr.getBodyElse());
			ctx.exitScope();
		} else if (n instanceof WhileStatement) {
			ctx.enterScope();
			WhileStatement curr = (WhileStatement) n;
			if (!(validExpression(curr.getCondition()) instanceof BoolType)) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}
			validBody(curr.getBody());
			ctx.exitScope();
		} else if (n instanceof ReturnStatement) {
			ReturnStatement curr = (ReturnStatement) n;
			Type expectedRetType = ctx.get(RETURN_KW);
			if (curr.getValue() == null) { //no expr in return statement
				if (expectedRetType instanceof VoidType) {
					curr.setRetType(new VoidType());
					return;
				}
			} else {
				Type actualRetType = validExpression(curr.getValue());
				if (expectedRetType.equals(actualRetType)) {
					curr.setRetType(actualRetType);
					return;
				}
			}
			throw new TypeMismatchException(curr.getPosition().toString());
		} else if (n instanceof VarDeclarationStatement) {
			VarDeclarationStatement curr = (VarDeclarationStatement) n;
			validExpression(curr.getValue());
			System.out.println(curr.getVarName());
			if (ctx.hasBeenDeclared(curr.getVarName())) {
				throw new DuplicateVarAssignException(curr.getPosition().toString());
			} else {
				validType(curr.getDatatype(), curr.getPosition());
				ctx.set(curr.getVarName(), curr.getDatatype());
			}
			if (curr.getValue() != null) {
				if(!validExpression(curr.getValue()).equals(ctx.get(curr.getVarName()))) {
					throw new TypeMismatchException(curr.getPosition().toString());
				}
			}

			ctx.set(curr.getVarName(), curr.getDatatype());			
		} else if (n instanceof VarAssignArrayStatement) {
			System.out.println("yey2");
			VarAssignArrayStatement currArr = (VarAssignArrayStatement) n;	
			for (Expression currIndex : currArr.getIndexes()) {
				if(!(validExpression(currIndex) instanceof IntType)) {
					throw new TypeMismatchException(currArr.getPosition().toString());
				}
			}		
			System.out.println("yey3");
			Type type = ctx.get(currArr.getVarName());
			if (type instanceof ArrayType) {
				ArrayType arrType = (ArrayType) type;
				int indexCount = currArr.getIndexes().size();
				int count = arrType.getNumNestedArr();
				int res = count - indexCount;
				if (indexCount > count) {
					throw new TypeMismatchException(currArr.getPosition().toString());
				} else {
					Type indexedType = null;
					if (res == 0) {							
						indexedType = arrType.getInside();
					} else {
						indexedType = new ArrayType(res, arrType.getInside());
					}
					if (!indexedType.equals(validExpression(currArr.getValue()))) {
						throw new TypeMismatchException(currArr.getPosition().toString());
					}
					currArr.setDatatype(indexedType);
				}
			} else {
				throw new TypeMismatchException(currArr.getPosition().toString());
			}
		} else if (n instanceof VarAssignStatement) {
			System.out.println("yey1");
			VarAssignStatement curr = (VarAssignStatement) n;			
			if (!ctx.hasBeenDeclared(curr.getVarName())) {
				throw new VarNotDeclaredException(curr.getPosition().toString());
			}
			Type type = ctx.get(curr.getVarName());
			if(!type.equals(validExpression(curr.getValue()))) {
				throw new TypeMismatchException(curr.getPosition().toString());
			}
			curr.setDatatype(type);
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

	private void validType(Type type, Position pos) throws InvalidTypeException {
		if(type instanceof CustomType || type instanceof VoidType) {
			throw new InvalidTypeException(pos.toString());
		}else if (type instanceof ArrayType){
			ArrayType arrType = (ArrayType) type;
			validType(arrType.getInside(), pos);
		}
	}


	private Type validExpression(Expression expr) throws SyntacticException {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			Type leftTy = validExpression(binaryExpr.getLeft());
			Type rightTy = validExpression(binaryExpr.getRight());
			if (expr instanceof AndExpression || expr instanceof OrExpression) {
				if (!(leftTy instanceof BoolType) || !(rightTy instanceof BoolType)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				Type resType = new BoolType();
				binaryExpr.setResType(resType);
				return resType;
			} else if (expr instanceof EqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				Type resType = new BoolType();
				binaryExpr.setResType(resType);
				return resType;
			} else if (expr instanceof NotEqualExpression) {
				if (!leftTy.equals(rightTy)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				Type resType = new BoolType();
				binaryExpr.setResType(resType);
				return resType;
			} else if (expr instanceof GreaterOrEqualExpression || expr instanceof GreaterExpression
					|| expr instanceof LessOrEqualExpression || expr instanceof LessExpression) {
				if (leftTy.equals(rightTy) && (leftTy instanceof IntType || leftTy instanceof FloatType)) {
					Type resType = new BoolType();
					binaryExpr.setResType(resType);
					return resType;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof SumExpression) {
				if (leftTy.equals(rightTy) && (leftTy instanceof IntType || leftTy instanceof FloatType
						|| leftTy instanceof StringType)) {
					binaryExpr.setResType(leftTy);
					return leftTy;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof SubtractionExpression || expr instanceof MultiplicationExpression
					|| expr instanceof DivisionExpression) {
				if (leftTy.equals(rightTy) && (leftTy instanceof IntType || leftTy instanceof FloatType)) {
					binaryExpr.setResType(leftTy);
					return leftTy;
				}
				throw new InvalidOperandException(expr.getPosition().toString());
			} else if (expr instanceof ModuloExpression) {
				if (!(leftTy instanceof IntType) || !(rightTy instanceof IntType)) {
					throw new InvalidOperandException(expr.getPosition().toString());
				}
				binaryExpr.setResType(leftTy);
				return leftTy;
			} 
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;
			Type type = validExpression(notExpr.getValue());
			if (!(type instanceof BoolType)) {
				throw new InvalidOperandException(expr.getPosition().toString());
			}
			notExpr.setResType(type);
			return type;
		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;
			Type type = validExpression(negExpr.getValue());
			if(type instanceof IntType || type instanceof FloatType) {
				negExpr.setResType(type);
				return type;
			} else {
				throw new InvalidOperandException(expr.getPosition().toString());
			}
		} else if (expr instanceof FunctionInvocationExpression) {
			FunctionInvocationExpression funcInvExpr = (FunctionInvocationExpression) expr;
			Type[] datatypes = funcSignCtx.getDataTypes(funcInvExpr.getFuncName());
			if (datatypes.length != funcInvExpr.getArguments().size()) {
				throw new FunctiontArgumentsException(funcInvExpr.getPosition().toString());
			}
			int i = 0;
			for (Expression currExpr : funcInvExpr.getArguments()) {
				if(!validExpression(currExpr).equals(datatypes[i])){
					System.out.println("fhdhah");
					System.out.println(currExpr.toString());
					throw new FunctiontArgumentsException(funcInvExpr.getPosition().toString());
				}
				i++;
			}
			funcInvExpr.setResType(funcSignCtx.getRetType(funcInvExpr.getFuncName()));
			return funcInvExpr.getResType();
		} else if (expr instanceof ArrayAcessFuncExpression) {
			ArrayAcessFuncExpression arrAcFuncExpr = (ArrayAcessFuncExpression) expr;
			Type[] datatypes = funcSignCtx.getDataTypes(arrAcFuncExpr.getVarName());
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
			Type type = funcSignCtx.getRetType(arrAcFuncExpr.getVarName());
			for (Expression currIndex : arrAcFuncExpr.getIndexes()) {
				if(!(validExpression(currIndex) instanceof IntType)) {
					throw new TypeMismatchException(expr.getPosition().toString());
				}
			}
			System.out.println("wew2");
			if (type instanceof ArrayType) {
				ArrayType arrType = (ArrayType) type;
				int indexCount = arrAcFuncExpr.getIndexes().size();
				int count = arrType.getNumNestedArr();
				int res = count - indexCount;
				if (indexCount <= count) {
					if (res == 0) {
						arrAcFuncExpr.setResType(arrType.getInside());
						return arrType.getInside();
					} else {
						arrAcFuncExpr.setResType(new ArrayType(res, arrType.getInside()));
						return arrAcFuncExpr.getResType();
					}
				}
			}
			throw new TypeMismatchException(arrAcFuncExpr.getPosition().toString());
		} else if (expr instanceof ArrayAcessVarExpression) {
			ArrayAcessVarExpression arrExpr = (ArrayAcessVarExpression) expr;
			Type type = ctx.get(arrExpr.getVarName());
			if (type == null) {
				throw new VarNotDeclaredException(arrExpr.getPosition().toString());
			}
			for (Expression currIndex : arrExpr.getIndexes()) {
				if(!(validExpression(currIndex) instanceof IntType)) {
					throw new TypeMismatchException(expr.getPosition().toString());
				}
			}
			System.out.println("wew");
			if (type instanceof ArrayType) {
				ArrayType arrType = (ArrayType) type;
				int indexCount = arrExpr.getIndexes().size();
				int count = arrType.getNumNestedArr();
				int res = count - indexCount;
				if (indexCount <= count) {
					if (res == 0) {
						arrExpr.setResType(arrType.getInside());
						return arrType.getInside();
					} else {
						arrExpr.setResType(new ArrayType(res, arrType.getInside()));
						return arrExpr.getResType();
					}
				}
			}
			throw new TypeMismatchException(arrExpr.getPosition().toString());
		} else if (expr instanceof BoolLit) {
			expr.setResType(new BoolType());
			return expr.getResType();
		} else if (expr instanceof IntLit) {
			expr.setResType(new IntType());
			return expr.getResType();
		} else if (expr instanceof FloatLit) {
			expr.setResType(new FloatType());
			return expr.getResType();
		} else if (expr instanceof StringLit) {
			expr.setResType(new StringType());
			return expr.getResType();
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;
			Type type = ctx.get(varExpr.getVarName());
			if (type == null) {
				throw new VarNotDeclaredException(varExpr.getPosition().toString());
			}
			varExpr.setResType(type);
			return type;
		}
		return null;		
	}

}
