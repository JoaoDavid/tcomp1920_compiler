package codegen;

import ast.datatype.ArrayType;
import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;
import ast.expression.binary.BinaryExpression;
import ast.expression.binary.SumExpression;

public class ConfigLLVM {
	
	protected static final String INT_TYPE = "i32";
	protected static final String FLOAT_TYPE = "float";
	protected static final String BOOL_TYPE = "i1";
	protected static final String STRING_TYPE = "i8*";
	protected static final String VOID_TYPE = "void";
	
	protected static final String SUM_INT = "add";
	protected static final String SUM_FLOAT = "fadd";
	protected static final String SUB_INT = "sub";
	protected static final String SUB_FLOAT = "fsub";
	protected static final String MUL_INT = "mul";
	protected static final String MUL_FLOAT = "fmul";
	protected static final String DIV_INT = "sdiv";
	protected static final String DIV_FLOAT = "fdiv";
	protected static final String MOD_INT = "srem";
	
	protected static String getLLVMType(Type type) {
		if(type instanceof IntType) {
			return INT_TYPE;
		} else if(type instanceof FloatType) {
			return FLOAT_TYPE;
		} else if(type instanceof BoolType) {
			return BOOL_TYPE;
		} else if(type instanceof StringType) {
			return STRING_TYPE;
		} else if(type instanceof VoidType) {
			return VOID_TYPE;
		} else if(type instanceof ArrayType) {
			ArrayType arrType = (ArrayType) type;
			StringBuilder sb = new StringBuilder();
			sb.append(getLLVMType(arrType.getInside()));
			for (int i = 0; i < arrType.getNumNestedArr(); i++) {
				sb.append("*");
			}
			return sb.toString();
		}
		return null;
	}
	
}
