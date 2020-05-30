package codegen;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static codegen.ConfigLLVM.*;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.Type;
import ast.datatype.VoidType;
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
import codegen.exception.CompileException;

public class Codegenator {

	private final static String defaultPath = System.getProperty("user.home") + File.separator + "Desktop";
	private final static String sufix = ".ll";
	private final static String identation = "    ";

	private Node root;
	private File file;
	private PrintWriter pw;
	private Emitter em;

	public Codegenator(Node root, String fileName, String path) {
		file = new File(path + File.separator + fileName + sufix);
		this.root = root;
		this.em = new Emitter();

	}

	public Codegenator(Node n, String fileName) {
		this(n, fileName, defaultPath);
	}

	public void generateLL() throws CompileException {
		if(file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			this.pw = new PrintWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStatements(root, "");
		this.pw.flush();
		this.pw.close();
	}

	private void writeStatements(Node n, String space) throws CompileException {
		StringBuilder sb = new StringBuilder();
		if (n instanceof CasualFile) {			
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				writeStatements(currDefDecl, space);
			}
		} else if (n instanceof FunctionDefinition) {
			em.enterScope();
			FunctionDefinition curr = (FunctionDefinition) n;
			pw.printf("define %s @%s (", getLLVMType(curr.getReturnType()), curr.getFuncName());
			int c = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				c++;
				
				//------------
				String llVar = getVarName(currFuncParam.getVarName());
				String newVar = getVarName(currFuncParam.getVarName());
				sb.append(alloca(identation, newVar, currFuncParam.getDatatype()));
				sb.append(store(identation, currFuncParam.getDatatype(), llVar, newVar));
				//writeStore(space, INT_TYPE, llVar, value);
				em.set(currFuncParam.getVarName(), newVar);
				pw.printf("%s %s", getLLVMType(currFuncParam.getDatatype()), llVar);
				//TODO
				//--------------
				if(c != curr.getParameters().size() ) {
					pw.print(", ");
				}
			}
			pw.print(") {\n");
			pw.write(sb.toString());
			for (Statement currStat : curr.getStatements()) {
				writeStatements(currStat, space + identation);
			}
			pw.println("\n}");
			//TODO
			em.exitScope();
		} /*else if (n instanceof FunctionParameter) {
			FunctionParameter curr = (FunctionParameter) n;
			String llVar = getVarName(curr.getVarName());
			em.set(curr.getVarName(), llVar);
			pw.printf("%s %s", getLLVMType(curr.getDatatype()), llVar);
			//TODO

		}*/ else if (n instanceof IfStatement) {

			//TODO

		} else if (n instanceof IfElseStatement) {

			//TODO

		} else if (n instanceof WhileStatement) {

			//TODO

		} else if (n instanceof ReturnStatement) {
			ReturnStatement curr = (ReturnStatement) n;
			sb.append(space);
			sb.append("ret ");
			sb.append(getLLVMType(curr.getRetType()));
			sb.append(" ");
			if (!(curr.getRetType() instanceof VoidType)) {
				sb.append(visitExpression(curr.getValue(), space));
			}
			pw.print(sb.toString());
			//TODO

		} else if (n instanceof VarDeclarationStatement) {
			VarDeclarationStatement curr = (VarDeclarationStatement) n;
			String llVar = getVarName(curr.getVarName());
			em.set(curr.getVarName(), llVar);
			Type type = curr.getDatatype();
			pw.write(alloca(space, llVar, type));
			if(curr.getValue() != null) { //checks if its only declaration (without assigning values)
				String value = visitExpression(curr.getValue(), space);
				System.out.println(value);
				if(type instanceof IntType) {					
					//pw.write(store(space, type, llVar, value));//TODO alterar
				} else if(type instanceof FloatType) {
					//pw.printf("%sstore float %s, float* %s%n", space, value, llVar);
					//pw.write(store(space, type, value, llVar));//TODO alterar

				} else if(type instanceof BoolType) {
					//pw.printf("%sstore float %s, float* %s%n", space, value, llVar);
					pw.write(store(space, type, value, llVar));//TODO alterar
				}
			}
			//TODO
		} else if (n instanceof VarAssignArrayStatement) {

			//TODO

		} else if (n instanceof VarAssignStatement) {

			//TODO

		} else if (n instanceof ExprStatement) {

			//TODO

		}
	}

	

	


	private String visitExpression(Expression expr, String space) {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			return writeBinaryExpression(binaryExpr, space);
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;

		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;

		} else if (expr instanceof FunctionInvocationExpression) {
			FunctionInvocationExpression funcInvExpr = (FunctionInvocationExpression) expr;

		} else if (expr instanceof ArrayAcessFuncExpression) {
			ArrayAcessFuncExpression arrAcFuncExpr = (ArrayAcessFuncExpression) expr;

		} else if (expr instanceof ArrayAcessVarExpression) {
			ArrayAcessVarExpression arrExpr = (ArrayAcessVarExpression) expr;

		} else if (expr instanceof BoolLit) {
			BoolLit lit = (BoolLit) expr;

			return lit.getValue();
		} else if (expr instanceof IntLit) {
			IntLit lit = (IntLit) expr;
			return lit.getValue().replace("_", "");
		} else if (expr instanceof FloatLit) {
			FloatLit lit = (FloatLit) expr;
			return floatToLLVM(Float.parseFloat(lit.getValue()));
		} else if (expr instanceof StringLit) {
			StringLit lit = (StringLit) expr;
			return lit.getValue();
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;
			String llvmResType = getLLVMType(expr.getResType());
			String loadVar = getVarName("load");	
			//%b = load i32, i32* %p_a
			pw.print(load(space, em.get(varExpr.getVarName()), llvmResType, loadVar));
			return loadVar;
		}
		return null;	
	}

	private String load(String space, String llvmVar, String llvmResType, String loadVar) {
		return String.format("%s%s = load %s, %s* %s%n", space, loadVar, llvmResType, llvmResType, llvmVar);
	}

	private String writeBinaryExpression(BinaryExpression expr, String space) {
		Expression left = expr.getLeft();
		Expression right = expr.getRight();
		String leftLL = visitExpression(left, space);
		String rightLL = visitExpression(right, space);
		String llvmResType = getLLVMType(expr.getResType());
		if (expr instanceof AndExpression) {

		} else if (expr instanceof OrExpression) {

		} else if (expr instanceof EqualExpression) {

		} else if (expr instanceof NotEqualExpression) {

		} else if (expr instanceof GreaterOrEqualExpression) {

		} else if (expr instanceof GreaterExpression) {

		} else if (expr instanceof LessOrEqualExpression) {

		} else if (expr instanceof LessExpression) {
			//icmp <comp> <tipo> op1, op2
			String lessVar = getVarName("less");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, LESS_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				//writeCompExpr(space, lessVar, CMP_FLOAT, LESS_FLOAT, getLLVMType(left.getResType())+"*", leftLL, rightLL);
				writeCompExpr(space, lessVar, CMP_FLOAT, LESS_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof SumExpression) {
			String sumVar = getVarName("sum");								
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, sumVar, SUM_INT, llvmResType, leftLL, rightLL);
			} else if(expr.getResType() instanceof FloatType) {
				writeArithmeticExpr(space, sumVar, SUM_FLOAT, llvmResType, leftLL, rightLL);
			}
			return sumVar;
		} else if (expr instanceof SubtractionExpression) {
			String subVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, subVar, SUB_INT, llvmResType, leftLL, rightLL);
			} else if(expr.getResType() instanceof FloatType) {
				writeArithmeticExpr(space, subVar, SUB_FLOAT, llvmResType, leftLL, rightLL);
			}
			return subVar;
		} else if (expr instanceof MultiplicationExpression) {
			String mulVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, mulVar, MUL_INT, llvmResType, leftLL, rightLL);
			} else if(expr.getResType() instanceof FloatType) {
				writeArithmeticExpr(space, mulVar, MUL_FLOAT, llvmResType, leftLL, rightLL);
			}
			return mulVar;
		} else if (expr instanceof DivisionExpression) {
			String divVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, divVar, DIV_INT, llvmResType, leftLL, rightLL);
			} else if(expr.getResType() instanceof FloatType) {
				writeArithmeticExpr(space, divVar, DIV_FLOAT, llvmResType, leftLL, rightLL);
			}
			return divVar;
		} else if (expr instanceof ModuloExpression) {
			String modVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, modVar, MOD_INT, llvmResType, leftLL, rightLL);
			}
			return modVar;
		} 
		return null;
	}

	private String getVarName(String varName) {
		return "%" + varName + "_" + em.getCount();
	}
	
	private void writeArithmeticExpr(String space, String llVar, String op, String llvmType, String leftLL, String rightLL) {
		pw.printf("%s%s = %s %s %s, %s%n", space, llVar, op, llvmType, leftLL, rightLL);
	}
	
	private void writeCompExpr(String space, String llVar, String cmp, String op, String llvmType, String leftLL, String rightLL) {
		//icmp <comp> <tipo> op1, op2
		pw.printf("%s%s = %s %s %s %s, %s%n", space, llVar, cmp, op, llvmType, leftLL, rightLL);
	}

	private String alloca(String space, String llVar, Type type) {
		return String.format("%s%s = alloca %s%n", space, llVar, getLLVMType(type));
	}
	
	private String store(String space, Type type, String value, String llVar) {
		return String.format("%sstore %s %s, %s* %s%n", space, getLLVMType(type) , value, getLLVMType(type), llVar);
	}

}
