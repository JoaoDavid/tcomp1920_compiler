package codegen;

import static codegen.llvm.Instruction.AND;
import static codegen.llvm.Instruction.CMP_FLOAT;
import static codegen.llvm.Instruction.CMP_INT;
import static codegen.llvm.Instruction.DIV_FLOAT;
import static codegen.llvm.Instruction.DIV_INT;
import static codegen.llvm.Instruction.EQUAL_FLOAT;
import static codegen.llvm.Instruction.EQUAL_INT;
import static codegen.llvm.Instruction.GREATER_EQUAL_FLOAT;
import static codegen.llvm.Instruction.GREATER_EQUAL_INT;
import static codegen.llvm.Instruction.GREATER_FLOAT;
import static codegen.llvm.Instruction.GREATER_INT;
import static codegen.llvm.Instruction.LESS_EQUAL_FLOAT;
import static codegen.llvm.Instruction.LESS_EQUAL_INT;
import static codegen.llvm.Instruction.LESS_FLOAT;
import static codegen.llvm.Instruction.LESS_INT;
import static codegen.llvm.Instruction.MOD_INT;
import static codegen.llvm.Instruction.MUL_FLOAT;
import static codegen.llvm.Instruction.MUL_INT;
import static codegen.llvm.Instruction.NOT_EQUAL_FLOAT;
import static codegen.llvm.Instruction.NOT_EQUAL_INT;
import static codegen.llvm.Instruction.OR;
import static codegen.llvm.Instruction.SUB_FLOAT;
import static codegen.llvm.Instruction.SUB_INT;
import static codegen.llvm.Instruction.SUM_FLOAT;
import static codegen.llvm.Instruction.SUM_INT;
import static codegen.llvm.Instruction.StringToLLVM;
import static codegen.llvm.Instruction.alloca;
import static codegen.llvm.Instruction.arithmetic;
import static codegen.llvm.Instruction.br;
import static codegen.llvm.Instruction.brUnconditional;
import static codegen.llvm.Instruction.call;
import static codegen.llvm.Instruction.callVoid;
import static codegen.llvm.Instruction.cmp;
import static codegen.llvm.Instruction.floatToLLVM;
import static codegen.llvm.Instruction.fneg;
import static codegen.llvm.Instruction.getElementPtrArr;
import static codegen.llvm.Instruction.getElementPtrStr;
import static codegen.llvm.Instruction.getLLVMType;
import static codegen.llvm.Instruction.globalStr;
import static codegen.llvm.Instruction.label;
import static codegen.llvm.Instruction.load;
import static codegen.llvm.Instruction.logic;
import static codegen.llvm.Instruction.ret;
import static codegen.llvm.Instruction.store;
import static codegen.llvm.Instruction.xor;
import static codegen.llvm.FunctionLib.STR_PRINT_FLOAT;
import static codegen.llvm.FunctionLib.STR_PRINT_INT;
import static codegen.llvm.FunctionLib.STR_PRINT_STRING;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import ast.expression.ArrayAccessFuncExpression;
import ast.expression.ArrayAccessVarExpression;
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
import codegen.exception.ReservedFunctionNameException;
import codegen.llvm.Instruction;
import codegen.llvm.FunctionLib;

public class Codegenator {

	//private final static String defaultPath = System.getProperty("user.home") + File.separator + "Desktop";
	private final static String SUFIX = ".ll";
	private final static String IDENTATION = "    ";
	private static final String RETURN_VAR = "$return";

	private String currRetLbl = null;


	private CasualFile root;
	private File file;
	private PrintWriter pw;
	private Emitter em;
	private List<String> stringGlobal;
	private FunctionLib funcLib;

	public Codegenator(CasualFile root, String llFile, String llPathFile) {
		this.file = new File(llPathFile + File.separator + llFile + SUFIX);
		this.root = root;
		this.em = new Emitter();
		this.stringGlobal = new ArrayList<String>();
		this.funcLib = new FunctionLib();
	}

	public Codegenator(CasualFile root, String llFile) {
		this.file = new File(llFile + SUFIX);
		this.root = root;
		this.em = new Emitter();
		this.stringGlobal = new ArrayList<String>();
		this.funcLib = new FunctionLib();
	}

	public void generateLL() throws CompileException {
		if(file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			this.pw = new PrintWriter(file);
		} catch (IOException e) {
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
			stringGlobal.add(STR_PRINT_INT);
			stringGlobal.add(STR_PRINT_FLOAT);
			//stringGlobal.add(STR_PRINT_BOOL);bool uses print int
			stringGlobal.add(STR_PRINT_STRING);
			CasualFile curr = (CasualFile) n;
			for (DefDecl currDefDecl : curr.getStatements()) {
				writeStatements(currDefDecl, space);
			}
			Iterator<String> decls = funcLib.getDecls();
			while (decls.hasNext()) {
				pw.write(decls.next());				
			}
		} else if (n instanceof FunctionDeclaration) {
			FunctionDeclaration curr = (FunctionDeclaration) n;
			funcLib.addDecl(curr.getFuncName());
		} else if (n instanceof FunctionDefinition) {
			FunctionDefinition curr = (FunctionDefinition) n;
			if(FunctionLib.isResLibFuncName(curr.getFuncName())) {
				throw new ReservedFunctionNameException(curr.getPosition().toString());
			}
			em.enterScope();
			currRetLbl = null;	
			pw.printf("define %s @%s (", getLLVMType(curr.getReturnType()), curr.getFuncName());
			int c = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				String llVar = getVarName(currFuncParam.getVarName());
				String newVar = getVarName(currFuncParam.getVarName());
				sb.append(alloca(IDENTATION, newVar, currFuncParam.getDatatype()));
				sb.append(store(IDENTATION, currFuncParam.getDatatype(), llVar, newVar));
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

			if (!(curr.getReturnType() instanceof VoidType)) {
				String llVar = getVarName(RETURN_VAR);
				em.set(RETURN_VAR, llVar);
				Type type = curr.getReturnType();
				pw.write(alloca(IDENTATION, llVar, type));
			}


			for (Statement currStat : curr.getStatements()) {
				writeStatements(currStat, space + IDENTATION);
			}

			if (curr.getReturnType() instanceof VoidType) {
				pw.write(ret(IDENTATION, curr.getReturnType(), ""));
			} else {
				String retLabel = currRetLbl;
				if (retLabel == null) {					
					String valueRet = em.get(RETURN_VAR);
					String loadRet = getVarName(RETURN_VAR + "_load");
					pw.write(load(IDENTATION, valueRet, curr.getReturnType(), loadRet));
					pw.write(ret(IDENTATION, curr.getReturnType(), loadRet));
				} else {
					pw.write(label(retLabel));
					String valueRet = em.get(RETURN_VAR);
					String loadRet = getVarName(RETURN_VAR + "_load");
					pw.write(load(IDENTATION, valueRet, curr.getReturnType(), loadRet));
					pw.write(ret(IDENTATION, curr.getReturnType(), loadRet));
				}
			}

			pw.println("\n}");
			em.exitScope();
		} else if (n instanceof FunctionDeclaration) {
			FunctionDeclaration curr = (FunctionDeclaration) n;	
			if(FunctionLib.isResLibFuncName(curr.getFuncName())) {
				return;
			}			
			pw.printf("declare %s @%s (", getLLVMType(curr.getReturnType()), curr.getFuncName());
			int c = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				//String llVar = getVarName(currFuncParam.getVarName());
				pw.printf("%s", getLLVMType(currFuncParam.getDatatype()));
				//--------------
				c++;	
				if(c != curr.getParameters().size()) {
					pw.print(", ");
				}
			}
			pw.print(")\n\n");
		} else if (n instanceof IfElseStatement) {
			IfElseStatement curr = (IfElseStatement) n;
			int num = em.getCount();
			//"\nthen_" + num + ":\n";
			String thenLabel = "then_" + num;
			String elseLabel = "else_" + num;
			String contLabel = "cont_" + num;
			String condVarResLLVM = visitExpression(curr.getCondition(), space);
			pw.write(br(space, condVarResLLVM, thenLabel, elseLabel));
			pw.write(label(thenLabel));
			em.enterScope();
			for (Statement currStat : curr.getBody()) {
				writeStatements(currStat, space);
			}
			em.exitScope();
			pw.write(brUnconditional(space, contLabel));

			//-------------Else--------------
			pw.write(label(elseLabel));
			em.enterScope();
			for (Statement currStat : curr.getBodyElse()) {
				writeStatements(currStat, space);
			}
			em.exitScope();
			//------------------------------
			/*pw.write(brUnconditional(space, contLabel));
			pw.write(label(contLabel));*/
			String retLabel = currRetLbl;
			if (retLabel != null) {
				pw.write(label(contLabel));
				pw.write(brUnconditional(space, retLabel));
			} else {
				pw.write(brUnconditional(space, contLabel));
				pw.write(label(contLabel));
			}
		} else if (n instanceof IfStatement) {
			IfStatement curr = (IfStatement) n;
			int num = em.getCount();
			//"\nthen_" + num + ":\n";
			String thenLabel = "then_" + num;
			String contLabel = "cont_" + num;
			String condVarResLLVM = visitExpression(curr.getCondition(), space);
			pw.write(Instruction.br(space, condVarResLLVM, thenLabel, contLabel));
			pw.write(label(thenLabel));
			em.enterScope();
			boolean hasRet = false;
			for (Statement currStat : curr.getBody()) {
				writeStatements(currStat, space);
				hasRet = currStat instanceof ReturnStatement;
			}
			em.exitScope();
			String retLabel = currRetLbl==null?"ret_" + num:currRetLbl;
			if (hasRet) {
				pw.write(brUnconditional(space, retLabel));
				currRetLbl = retLabel;
				pw.write(label(contLabel));
			} else {
				pw.write(brUnconditional(space, contLabel));
				pw.write(label(contLabel));
			}
		} else if (n instanceof WhileStatement) {
			WhileStatement curr = (WhileStatement) n;
			int num = em.getCount();
			String conditionLabel = "while_cond_" + num;
			String bodyLabel = "while_body_" + num;
			String contLabel = "while_cont_" + num;
			//condition
			pw.write(brUnconditional(space, conditionLabel));
			pw.write(String.format("%n%s:%n", conditionLabel));
			String condVarResLLVM = visitExpression(curr.getCondition(), space);
			pw.write(br(space, condVarResLLVM, bodyLabel, contLabel));
			//body
			pw.write(String.format("%n%s:%n", bodyLabel));
			em.enterScope();
			for (Statement currStat : curr.getBody()) {
				writeStatements(currStat, space);
			}
			em.exitScope();
			pw.write(brUnconditional(space, conditionLabel));
			//cont
			pw.write(String.format("%n%s:%n", contLabel));
		} else if (n instanceof ReturnStatement) {
			ReturnStatement curr = (ReturnStatement) n;
			if (!(curr.getRetType() instanceof VoidType)) {
				pw.write(store(space, curr.getRetType(), visitExpression(curr.getValue(), space), em.get(RETURN_VAR)));
			}
			String retLabel = currRetLbl;
			if (retLabel == null) {					

			} else {
				pw.write(brUnconditional(space, retLabel));
			}
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
					pw.write(store(space, type, value, llVar));
				}
			}
		} else if (n instanceof VarAssignArrayStatement) { //arr[x][y]...
			VarAssignArrayStatement currArr = (VarAssignArrayStatement) n;	
			String arrVar = getVarName("arr_access");
			String loadVar = getVarName("arr_load");
			int counter = currArr.getIndexes().size();
			ArrayType type2 = new ArrayType(counter, currArr.getDatatype());
			String varName = em.get(currArr.getVarName());			
			for (int i = 0; i < counter; i++) {
				arrVar = getVarName("arr_access");
				pw.write(load(space, varName, type2, loadVar));						
				ArrayType type1 = new ArrayType(type2.getNumNestedArr()-1, type2.getInside());	
				pw.write(getElementPtrArr(space, arrVar, type1, type2, loadVar, visitExpression(currArr.getIndexes().get(i), space)));
				loadVar = getVarName("arr_load");
				varName = arrVar;
				type2 = type1;				
			}
			Type type = currArr.getDatatype();
			String value = visitExpression(currArr.getValue(), space);
			pw.write(store(space, type, value, arrVar));
		} else if (n instanceof VarAssignStatement) {
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
				//never reached because it is treated in VarAssignArrayStatement
			}
		} else if (n instanceof ExprStatement) {
			ExprStatement curr = (ExprStatement) n;
			visitExpression(curr.getValue(), space);
		}
	}

	private String visitExpression(Expression expr, String space) {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;
			return writeBinaryExpression(binaryExpr, space);
		} else if (expr instanceof NotExpression) {
			NotExpression notExpr = (NotExpression) expr;
			String operand = visitExpression(notExpr.getValue(), space);
			Type argType = notExpr.getResType();
			//xor with true
			String notVar = getVarName("xor");
			pw.write(xor(space, notVar, argType, operand, "true"));
			return notVar;
		} else if (expr instanceof NegativeExpression) {
			NegativeExpression negExpr = (NegativeExpression) expr;
			String operand = visitExpression(negExpr.getValue(), space);
			String subVar = getVarName("neg");								
			Type argType = negExpr.getResType();
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, subVar, SUB_INT, expr.getResType(), "0", operand));
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
			if (funcInvExpr.getResType() instanceof VoidType) {
				pw.write(callVoid(space, funcInvExpr.getResType(), funcInvExpr.getFuncName(), sb.toString()));
			} else {
				pw.write(call(space, funcVar, funcInvExpr.getResType(), funcInvExpr.getFuncName(), sb.toString()));		
			}
			return funcVar;
		} else if (expr instanceof ArrayAccessFuncExpression) {
			ArrayAccessFuncExpression arrAcFuncExpr = (ArrayAccessFuncExpression) expr;
			String funcVar = getVarName("arr_acc_func_call");
			StringBuilder sb = new StringBuilder();
			int c = 0;
			for (Expression currExpr : arrAcFuncExpr.getArguments()) {
				sb.append(getLLVMType(currExpr.getResType()));
				sb.append(" ");
				sb.append(visitExpression(currExpr, space));
				//--------------
				c++;	
				if(c != arrAcFuncExpr.getArguments().size() ) {
					sb.append(", ");
				}
			}
			pw.write(call(space, funcVar, arrAcFuncExpr.getFuncResType(), arrAcFuncExpr.getVarName(), sb.toString()));		
			//array indexing; call saves return value in funcVar
			String auxVar = getVarName("arr_aux");
			pw.write(alloca(space, auxVar, arrAcFuncExpr.getFuncResType()));
			pw.write(store(space, arrAcFuncExpr.getFuncResType(), funcVar, auxVar));

			String arrVar = auxVar;
			String loadVar = getVarName("arr_load");
			int counter = arrAcFuncExpr.getIndexes().size();
			ArrayType type2 = (ArrayType) arrAcFuncExpr.getFuncResType();

			pw.write(load(space, auxVar, arrAcFuncExpr.getFuncResType(), loadVar));
			for (int i = 0; i < counter; i++) {						
				ArrayType type1 = new ArrayType(type2.getNumNestedArr()-1, type2.getInside());
				arrVar = getVarName("arr_access");	
				pw.write(getElementPtrArr(space, arrVar, type1, type2, loadVar, visitExpression(arrAcFuncExpr.getIndexes().get(i), space)));
				loadVar = getVarName("arr_load");
				pw.write(load(space, arrVar, type1, loadVar));
				type2 = type1;				
			}
			arrAcFuncExpr.setResType(type2);
			return loadVar;
		} else if (expr instanceof ArrayAccessVarExpression) {
			ArrayAccessVarExpression arrExpr = (ArrayAccessVarExpression) expr;
			String arrVar = getVarName("arr_access");
			String loadVar = getVarName("arr_load");
			int counter = arrExpr.getIndexes().size();
			ArrayType type2 = new ArrayType(counter, arrExpr.getResType());

			pw.write(load(space, em.get(arrExpr.getVarName()), type2, loadVar));
			for (int i = 0; i < counter; i++) {						
				ArrayType type1 = new ArrayType(type2.getNumNestedArr()-1, type2.getInside());
				arrVar = getVarName("arr_access");	
				pw.write(getElementPtrArr(space, arrVar, type1, type2, loadVar, visitExpression(arrExpr.getIndexes().get(i), space)));
				loadVar = getVarName("arr_load");
				pw.write(load(space, arrVar, type1, loadVar));
				type2 = type1;				
			}
			return loadVar;
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
			String strGlobalVar = getGlobalStrVarName();
			String stringLLVM = StringToLLVM(lit.getValue());
			int len = lit.getValue().replace("\\", "").length()-1;
			stringGlobal.add(globalStr(len, strGlobalVar, stringLLVM));
			return getElementPtrStr(len, strGlobalVar);
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;
			String loadVar = getVarName("var_ref");	
			//%b = load i32, i32* %p_a
			pw.print(load(space, em.get(varExpr.getVarName()), expr.getResType(), loadVar));
			return loadVar;
		}
		return null;	
	}



	private String writeBinaryExpression(BinaryExpression expr, String space) {
		Expression left = expr.getLeft();
		Expression right = expr.getRight();
		String leftLL = visitExpression(left, space);
		String rightLL = visitExpression(right, space);
		Type llvmResType = expr.getResType();
		if (expr instanceof AndExpression) {
			//<result> = and <ty> <op1>, <op2>
			String andVar = getVarName("and");
			Type argType = left.getResType();
			pw.write(logic(space, andVar, AND, argType, leftLL, rightLL));
			return andVar;
		} else if (expr instanceof OrExpression) {
			//<result> = or <ty> <op1>, <op2>
			String orVar = getVarName("or");
			Type argType = left.getResType();
			pw.write(logic(space, orVar, OR, argType, leftLL, rightLL));
			return orVar;
		} else if (expr instanceof EqualExpression) {
			//<result> = <fcmp/icmp> <cond> <ty> <op1>, <op2> 
			String lessVar = getVarName("cmp_eq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, EQUAL_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, EQUAL_FLOAT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof BoolType) {
				pw.write(cmp(space, lessVar, CMP_INT, EQUAL_INT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof NotEqualExpression) {
			String lessVar = getVarName("cmp_neq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, NOT_EQUAL_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, NOT_EQUAL_FLOAT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof BoolType) {
				pw.write(cmp(space, lessVar, CMP_INT, NOT_EQUAL_INT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof GreaterOrEqualExpression) {
			String lessVar = getVarName("cmp_geq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, GREATER_EQUAL_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, GREATER_EQUAL_FLOAT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof GreaterExpression) {
			String lessVar = getVarName("cmp_grt");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, GREATER_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, GREATER_FLOAT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof LessOrEqualExpression) {
			String lessVar = getVarName("cmp_leq");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, LESS_EQUAL_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, LESS_EQUAL_FLOAT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof LessExpression) {
			//icmp <comp> <tipo> op1, op2
			String lessVar = getVarName("cmp_less");
			Type argType = left.getResType();
			if(argType instanceof IntType) {
				pw.write(cmp(space, lessVar, CMP_INT, LESS_INT, left.getResType(), leftLL, rightLL));
			} else if(argType instanceof FloatType) {
				pw.write(cmp(space, lessVar, CMP_FLOAT, LESS_FLOAT, left.getResType(), leftLL, rightLL));
			}
			return lessVar;
		} else if (expr instanceof SumExpression) {
			String sumVar = getVarName("sum");								
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, sumVar, SUM_INT, llvmResType, leftLL, rightLL));
			} else if(expr.getResType() instanceof FloatType) {
				pw.write(arithmetic(space, sumVar, SUM_FLOAT, llvmResType, leftLL, rightLL));
			}
			return sumVar;
		} else if (expr instanceof SubtractionExpression) {
			String subVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, subVar, SUB_INT, llvmResType, leftLL, rightLL));
			} else if(expr.getResType() instanceof FloatType) {
				pw.write(arithmetic(space, subVar, SUB_FLOAT, llvmResType, leftLL, rightLL));
			}
			return subVar;
		} else if (expr instanceof MultiplicationExpression) {
			String mulVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, mulVar, MUL_INT, llvmResType, leftLL, rightLL));
			} else if(expr.getResType() instanceof FloatType) {
				pw.write(arithmetic(space, mulVar, MUL_FLOAT, llvmResType, leftLL, rightLL));
			}
			return mulVar;
		} else if (expr instanceof DivisionExpression) {
			String divVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, divVar, DIV_INT, llvmResType, leftLL, rightLL));
			} else if(expr.getResType() instanceof FloatType) {
				pw.write(arithmetic(space, divVar, DIV_FLOAT, llvmResType, leftLL, rightLL));
			}
			return divVar;
		} else if (expr instanceof ModuloExpression) {
			String modVar = getVarName("sub");								
			if(expr.getResType() instanceof IntType) {
				pw.write(arithmetic(space, modVar, MOD_INT, llvmResType, leftLL, rightLL));
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


}
