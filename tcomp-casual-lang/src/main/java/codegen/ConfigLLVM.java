package codegen;

import ast.datatype.ArrayType;
import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;

public class ConfigLLVM {
	
	protected static final String INT_TYPE = "i32";
	protected static final String FLOAT_TYPE = "float";
	protected static final String BOOL_TYPE = "i1";
	protected static final String STRING_TYPE = "i8*";
	protected static final String VOID_TYPE = "void";
	
	protected static final String AND = "and";
	protected static final String OR = "or";
	
	protected static final String SUM_INT = "add";
	protected static final String SUM_FLOAT = "fadd";
	protected static final String SUB_INT = "sub";
	protected static final String SUB_FLOAT = "fsub";
	protected static final String MUL_INT = "mul";
	protected static final String MUL_FLOAT = "fmul";
	protected static final String DIV_INT = "sdiv";
	protected static final String DIV_FLOAT = "fdiv";
	protected static final String MOD_INT = "srem";
	
	protected static final String CMP_INT = "icmp";	
	protected static final String EQUAL_INT = "eq";
	protected static final String NOT_EQUAL_INT = "ne";
	protected static final String GREATER_EQUAL_INT = "sge";
	protected static final String GREATER_INT = "sgt";
	protected static final String LESS_EQUAL_INT = "sle";
	protected static final String LESS_INT = "slt";
	
	protected static final String CMP_FLOAT = "fcmp";
	protected static final String EQUAL_FLOAT = "oeq";
	protected static final String NOT_EQUAL_FLOAT = "one";
	protected static final String GREATER_EQUAL_FLOAT = "oge";
	protected static final String GREATER_FLOAT = "ogt";
	protected static final String LESS_EQUAL_FLOAT = "ole";
	protected static final String LESS_FLOAT = "olt";
	
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
	
	public static String floatToLLVM(float f) {
		return "0x" + toHexString(Double.doubleToRawLongBits((double) f));
	}
	
	public static String doubleToLLVM(double d) {
		return "0x" + toHexString(Double.doubleToRawLongBits(d));
	}
	
	private static String toHexString(long l) {
		int count = (l == 0L) ? 1 : ((64 - Long.numberOfLeadingZeros(l)) + 3) / 4;
		StringBuilder buffer = new StringBuilder(count);
		long k = l;
		do {
			long t = k & 15L;
			if (t > 9) {
				t = t - 10 + 'A';
			} else {
				t += '0';
			}
			count -= 1;
			buffer.insert(0, (char) t);
			k = k >> 4;
		} while (count > 0);
		return buffer.toString();
	}	
	
}
