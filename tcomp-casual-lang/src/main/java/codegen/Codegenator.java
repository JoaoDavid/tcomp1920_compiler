package codegen;

import static codegen.ConfigLLVM.AND;
import static codegen.ConfigLLVM.CMP_FLOAT;
import static codegen.ConfigLLVM.CMP_INT;
import static codegen.ConfigLLVM.DIV_FLOAT;
import static codegen.ConfigLLVM.DIV_INT;
import static codegen.ConfigLLVM.EQUAL_FLOAT;
import static codegen.ConfigLLVM.EQUAL_INT;
import static codegen.ConfigLLVM.GREATER_EQUAL_FLOAT;
import static codegen.ConfigLLVM.GREATER_EQUAL_INT;
import static codegen.ConfigLLVM.GREATER_FLOAT;
import static codegen.ConfigLLVM.GREATER_INT;
import static codegen.ConfigLLVM.LESS_FLOAT;
import static codegen.ConfigLLVM.LESS_INT;
import static codegen.ConfigLLVM.MOD_INT;
import static codegen.ConfigLLVM.MUL_FLOAT;
import static codegen.ConfigLLVM.MUL_INT;
import static codegen.ConfigLLVM.NOT_EQUAL_FLOAT;
import static codegen.ConfigLLVM.NOT_EQUAL_INT;
import static codegen.ConfigLLVM.OR;
import static codegen.ConfigLLVM.SUB_FLOAT;
import static codegen.ConfigLLVM.SUB_INT;
import static codegen.ConfigLLVM.SUM_FLOAT;
import static codegen.ConfigLLVM.SUM_INT;
import static codegen.ConfigLLVM.floatToLLVM;
import static codegen.ConfigLLVM.getLLVMType;
import static codegen.ConfigLLVM.StringToLLVM;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDeclaration;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
import ast.datatype.ArrayType;
import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;
import ast.exception.FunctiontArgumentsException;
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
	private List<String> stringGlobal;
	
	public Codegenator(Node root, String fileName, String path) {
		file = new File(path + File.separator + fileName + sufix);
		this.root = root;
		this.em = new Emitter();
		this.stringGlobal = new ArrayList<String>();
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
		for (String string : stringGlobal) {
			pw.write(string);
		}
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
				String llVar = getVarName(currFuncParam.getVarName());
				String newVar = getVarName(currFuncParam.getVarName());
				sb.append(alloca(identation, newVar, currFuncParam.getDatatype()));
				sb.append(store(identation, currFuncParam.getDatatype(), llVar, newVar));
				em.set(currFuncParam.getVarName(), newVar);
				pw.printf("%s %s", getLLVMType(currFuncParam.getDatatype()), llVar);
				//--------------
				c++;	
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
			em.exitScope();
		} else if (n instanceof FunctionDeclaration) {			
			FunctionDeclaration curr = (FunctionDeclaration) n;	
			pw.printf("declare %s @%s (", getLLVMType(curr.getReturnType()), curr.getFuncName());
			int c = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				String llVar = getVarName(currFuncParam.getVarName());
				pw.printf("%s", getLLVMType(currFuncParam.getDatatype()));
				//--------------
				c++;	
				if(c != curr.getParameters().size()) {
					pw.print(", ");
				}
			}
			pw.print(")\n\n");
		} else if (n instanceof IfStatement) {

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
				if(type instanceof IntType) {					
					pw.write(store(space, type, value, llVar));
				} else if(type instanceof FloatType) {
					pw.write(store(space, type, value, llVar));
				} else if(type instanceof BoolType) {
					pw.write(store(space, type, value, llVar));
				} else if(type instanceof StringType) {
					pw.write(store(space, type, value, llVar));
				} else if(type instanceof ArrayType) {
					//TODO
				}
			}
		} else if (n instanceof VarAssignArrayStatement) {

			//TODO

		} else if (n instanceof VarAssignStatement) {
			//TODO falta em.set?
			VarAssignStatement curr = (VarAssignStatement) n;
			Type type = curr.getDatatype();
			String value = visitExpression(curr.getValue(), space);
			String llVar = em.get(curr.getVarName());
			pw.write(store(space, type, value, llVar));
			if(type instanceof IntType) {					
				pw.write(store(space, type, value, llVar));
			} else if(type instanceof FloatType) {
				pw.write(store(space, type, value, llVar));
			} else if(type instanceof BoolType) {
				pw.write(store(space, type, value, llVar));
			} else if(type instanceof StringType) {
				pw.write(store(space, type, value, llVar));
			} else if(type instanceof ArrayType) {
				//IMPOSSIBLE, its treated in VarAssignArrayStatement
			}
		} else if (n instanceof ExprStatement) {
			ExprStatement curr = (ExprStatement) n;
			visitExpression(curr.getValue(), space);
		}
	}

	

	


	private String visitExpression(Expression expr, String space) {
		String llvmResType = getLLVMType(expr.getResType());
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			return writeBinaryExpression(binaryExpr, space);
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;
			String operand = visitExpression(notExpr.getValue(), space);
			//first writes cmp ne
			String notVarAux = getVarName("cmp_ne_aux");
			Type argType = notExpr.getResType();
			writeCompExpr(space, notVarAux, CMP_INT, EQUAL_INT, getLLVMType(argType), operand, "0");
			//then xor with the result from cmp
			String notVarFinal = getVarName("xor");
			pw.write(xor(space, notVarFinal, argType, notVarAux, "true"));
			return notVarFinal;
		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;
			String operand = visitExpression(negExpr.getValue(), space);
			String subVar = getVarName("neg");								
			Type argType = negExpr.getResType();
			if(expr.getResType() instanceof IntType) {
				writeArithmeticExpr(space, subVar, SUB_INT, llvmResType, "0", operand);
			} else if(expr.getResType() instanceof FloatType) {
				pw.write(fneg(space, subVar, visitExpression(negExpr.getValue(), space), argType, operand));
			}
			return subVar;
		} else if (expr instanceof FunctionInvocationExpression) {
			FunctionInvocationExpression funcInvExpr = (FunctionInvocationExpression) expr;
			String funcVar = getVarName("func_call");
			StringBuilder sb = new StringBuilder();
			int c = 0;
			for (Expression currExpr : funcInvExpr.getArguments()) {
				sb.append(getLLVMType(currExpr.getResType()));
				sb.append(" ");
				sb.append(visitExpression(currExpr, space));
				//--------------
				c++;	
				if(c != funcInvExpr.getArguments().size() ) {
					sb.append(", ");
				}
			}
			pw.write(call(space, funcVar, funcInvExpr.getResType(), funcInvExpr.getFuncName(), sb.toString()));
			return funcVar;
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
			//@.str = private unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00"
			String strGlobalVar = getGlobalStrVarName();
			String stringLLVM = StringToLLVM(lit.getValue());
			System.out.println(lit.getValue() + ":" + lit.getValue().length());
			stringGlobal.add(globalStr(lit.getValue().length()-1, strGlobalVar, stringLLVM));
			return getelementptr(lit.getValue().length()-1, strGlobalVar);
			//return lit.getValue();
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;
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
			//<result> = and <ty> <op1>, <op2>
			String andVar = getVarName("and");
			Type argType = left.getResType();
			String llvmType = ConfigLLVM.getLLVMType(argType);
			writeLogicExpr(space, andVar, AND, llvmType, leftLL, rightLL);
			return andVar;
		} else if (expr instanceof OrExpression) {
			//<result> = or <ty> <op1>, <op2>
			String orVar = getVarName("or");
			Type argType = left.getResType();
			String llvmType = ConfigLLVM.getLLVMType(argType);
			writeLogicExpr(space, orVar, OR, llvmType, leftLL, rightLL);
			return orVar;
		} else if (expr instanceof EqualExpression) {
			//<result> = <fcmp/icmp> <cond> <ty> <op1>, <op2> 
			String lessVar = getVarName("cmp_eq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, EQUAL_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				writeCompExpr(space, lessVar, CMP_FLOAT, EQUAL_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof NotEqualExpression) {
			String lessVar = getVarName("cmp_neq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, NOT_EQUAL_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				writeCompExpr(space, lessVar, CMP_FLOAT, NOT_EQUAL_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof GreaterOrEqualExpression) {
			String lessVar = getVarName("cmp_geq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, GREATER_EQUAL_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				writeCompExpr(space, lessVar, CMP_FLOAT, GREATER_EQUAL_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof GreaterExpression) {
			String lessVar = getVarName("cmp_grt");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, GREATER_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				writeCompExpr(space, lessVar, CMP_FLOAT, GREATER_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof LessOrEqualExpression) {
			String lessVar = getVarName("cmp_leq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, LESS_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
				writeCompExpr(space, lessVar, CMP_FLOAT, LESS_FLOAT, getLLVMType(left.getResType()), leftLL, rightLL);
			}
			return lessVar;
		} else if (expr instanceof LessExpression) {
			//icmp <comp> <tipo> op1, op2
			String lessVar = getVarName("cmp_less");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				writeCompExpr(space, lessVar, CMP_INT, LESS_INT, getLLVMType(left.getResType()), leftLL, rightLL);
			} else if(argType instanceof FloatType) {
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
	
	private String getGlobalStrVarName() {
		return "@str_" + em.getCount();
	}
	
	private void writeArithmeticExpr(String space, String llVar, String op, String llvmType, String leftLL, String rightLL) {
		pw.printf("%s%s = %s %s %s, %s%n", space, llVar, op, llvmType, leftLL, rightLL);
	}
	
	private void writeCompExpr(String space, String llVar, String cmp, String op, String llvmType, String leftLL, String rightLL) {
		//icmp <comp> <tipo> op1, op2
		pw.printf("%s%s = %s %s %s %s, %s%n", space, llVar, cmp, op, llvmType, leftLL, rightLL);
	}
	
	private void writeLogicExpr(String space, String llVar, String cmp, String llvmType, String leftLL, String rightLL) {
		//<result> = or <ty> <op1>, <op2>
		pw.printf("%s%s = %s %s %s, %s%n", space, llVar, cmp, llvmType, leftLL, rightLL);
	}

	private String alloca(String space, String llVar, Type type) {
		return String.format("%s%s = alloca %s%n", space, llVar, getLLVMType(type));
	}
	
	private String store(String space, Type type, String value, String llVar) {
		return String.format("%sstore %s %s, %s* %s%n", space, getLLVMType(type) , value, getLLVMType(type), llVar);
	}
	
	private String fneg(String space, String newVar, String llVar, Type type, String value) {
		//<result> = fneg <ty> <op1>
		return String.format("%s%s = fneg %s %s%n", space, newVar,getLLVMType(type) ,llVar);
	}
	
	private String xor(String space, String resVar, Type type, String op1, String op2) {
		//<result> = xor <ty> <op1>, <op2> 
		return String.format("%s%s = xor %s %s, %s%n", space, resVar, getLLVMType(type), op1, op2);
	}
	
	private String call(String space, String resVar, Type type, String funcName, String args) {
		//<result> = call <ty> @<funcName>(<ty_ar> <ar>, ...)
		return String.format("%s%s = call %s @%s(%s)%n", space, resVar, getLLVMType(type), funcName, args);
	}
	
	private String globalStr(int len, String strGlobalVar, String strLit) {
		//@.str = private unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00"
		return String.format("%s = private unnamed_addr constant [%d x i8] c%s%n", strGlobalVar, len, strLit);
	}
	
	private String getelementptr(int len, String strGlobalVar) {
		//getelementptr inbounds ([15 x i8], [15 x i8]* @.str.1, i64 0, i64 0)
		return String.format("getelementptr inbounds ([%d x i8], [%d x i8]* %s, i64 0, i64 0)", len, len, strGlobalVar);
	}

}
