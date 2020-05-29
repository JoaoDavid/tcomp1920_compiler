package codegen;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import ast.CasualFile;
import ast.DefDecl;
import ast.FunctionDefinition;
import ast.FunctionParameter;
import ast.Node;
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
import casual.Emitter;
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
			FunctionDefinition curr = (FunctionDefinition) n;
			pw.printf("define %s @%s (", ConfigLLVM.getLLVMType(curr.getReturnType()), curr.getFuncName());
			int c = 0;
			for (FunctionParameter currFuncParam : curr.getParameters()) {
				c++;
				writeStatements(currFuncParam, space);
				if(c != curr.getParameters().size() ) {
					pw.print(", ");
				}
			}
			pw.print(") {\n");
			for (Statement currStat : curr.getStatements()) {
				writeStatements(currStat, space + identation);
			}
			pw.println("\n}");
			//TODO

		} else if (n instanceof FunctionParameter) {
			FunctionParameter curr = (FunctionParameter) n;
			String var = getVarName(curr.getVarName());
			pw.printf("%s %s", ConfigLLVM.getLLVMType(curr.getDatatype()), var);
			//TODO

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
			sb.append(ConfigLLVM.getLLVMType(curr.getRetType()));
			sb.append(" ");
			if (!(curr.getRetType() instanceof VoidType)) {
				sb.append(visitExpression(curr.getValue()));
			}
			pw.print(sb.toString());
			//TODO

		} else if (n instanceof VarDeclarationStatement) {
			System.out.println("VarDeclarationStatement");
			VarDeclarationStatement curr = (VarDeclarationStatement) n;
			String var = getVarName(curr.getVarName());
			Type type = curr.getDatatype();
			pw.printf("%s%s = alloca %s%n", space, var, ConfigLLVM.getLLVMType(type));
			if(curr.getValue() != null) { //checks if its only declaration (without assigning values)
				if(type instanceof IntType) {
					String value = visitExpression(curr.getValue());
					pw.printf("%sstore i32 %s, i32* %s%n", space, value, var);//TODO alterar
					sb.append("%");
					sb.append(curr.getVarName());
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


	private String visitExpression(Expression expr) {
		if (expr instanceof BinaryExpression) {
			BinaryExpression binaryExpr = (BinaryExpression) expr;


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
			return lit.getValue();
		} else if (expr instanceof StringLit) {
			StringLit lit = (StringLit) expr;
			return lit.getValue();
		} else if (expr instanceof VarReferenceExpression) {
			VarReferenceExpression varExpr = (VarReferenceExpression) expr;

		}
		return null;	
	}

	private void writeBinaryExpression(BinaryExpression expr) {
		/*Type leftTy = writeExpression(expr.getLeft());
		Type rightTy = writeExpression(expr.getRight());*/
		if (expr instanceof AndExpression) {

		} else if (expr instanceof OrExpression) {

		} else if (expr instanceof EqualExpression) {

		} else if (expr instanceof NotEqualExpression) {

		} else if (expr instanceof GreaterOrEqualExpression) {

		} else if (expr instanceof GreaterExpression) {

		} else if (expr instanceof LessOrEqualExpression) {

		} else if (expr instanceof LessExpression) {

		} else if (expr instanceof SumExpression) {

		} else if (expr instanceof SubtractionExpression) {

		} else if (expr instanceof MultiplicationExpression) {

		} else if (expr instanceof DivisionExpression) {

		} else if (expr instanceof ModuloExpression) {

		} 
	}

	private String getVarName(String varName) {
		return "%" + varName + "_" + em.getCount();
	}

}
