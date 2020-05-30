package codegen;

import static codegen.ConfigLLVM.getLLVMType;

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
	
	public static String StringToLLVM(String s) {
		String res = s.replace("\\n", "\\0A").replaceFirst(".$","") + "\\00\"";
		return res;
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
	
	protected static String writeArithmeticExpr(String space, String llVar, String op, String llvmType, String leftLL, String rightLL) {
		return String.format("%s%s = %s %s %s, %s%n", space, llVar, op, llvmType, leftLL, rightLL);
	}
	
	protected static String writeCompExpr(String space, String llVar, String cmp, String op, String llvmType, String leftLL, String rightLL) {
		//icmp <comp> <tipo> op1, op2
		return String.format("%s%s = %s %s %s %s, %s%n", space, llVar, cmp, op, llvmType, leftLL, rightLL);
	}
	
	protected static String writeLogicExpr(String space, String llVar, String cmp, String llvmType, String leftLL, String rightLL) {
		//<result> = or <ty> <op1>, <op2>
		return String.format("%s%s = %s %s %s, %s%n", space, llVar, cmp, llvmType, leftLL, rightLL);
	}

	protected static String alloca(String space, String llVar, Type type) {
		return String.format("%s%s = alloca %s%n", space, llVar, getLLVMType(type));
	}
	
	protected static String store(String space, Type type, String value, String llVar) {
		return String.format("%sstore %s %s, %s* %s%n", space, getLLVMType(type) , value, getLLVMType(type), llVar);
	}
	
	protected static String load(String space, String llvmVar, String llvmResType, String loadVar) {
		return String.format("%s%s = load %s, %s* %s%n", space, loadVar, llvmResType, llvmResType, llvmVar);
	}
	
	protected static String fneg(String space, String newVar, String llVar, Type type, String value) {
		//<result> = fneg <ty> <op1>
		return String.format("%s%s = fneg %s %s%n", space, newVar,getLLVMType(type) ,llVar);
	}
	
	protected static String xor(String space, String resVar, Type type, String op1, String op2) {
		//<result> = xor <ty> <op1>, <op2> 
		return String.format("%s%s = xor %s %s, %s%n", space, resVar, getLLVMType(type), op1, op2);
	}
	
	protected static String call(String space, String resVar, Type type, String funcName, String args) {
		//<result> = call <ty> @<funcName>(<ty_ar> <ar>, ...)
		return String.format("%s%s = call %s @%s(%s)%n", space, resVar, getLLVMType(type), funcName, args);
	}
	
	protected static String globalStr(int len, String strGlobalVar, String strLit) {
		//@.str = protected unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00"
		return String.format("%s = protected unnamed_addr constant [%d x i8] c%s%n", strGlobalVar, len, strLit);
	}
	
	protected static String getelementptr(int len, String strGlobalVar) {
		//getelementptr inbounds ([15 x i8], [15 x i8]* @.str.1, i64 0, i64 0)
		return String.format("getelementptr inbounds ([%d x i8], [%d x i8]* %s, i64 0, i64 0)", len, len, strGlobalVar);
	}
	
	protected static String br(String space, String condRes, String labelTrue, String labelFalse) {
		//br i1 <cond>, label <iftrue>, label <iffalse>
		//%sbr i1 %s, label %s, label %s
		return String.format("%sbr i1 %s, label %%%s, label %%%s%n", space, condRes, labelTrue, labelFalse);
	}

	protected static String br_unconditional(String space, String label) {
		//br label <dest>  
		//%sbr label %s
		return String.format("%sbr label %%%s%n", space, label);
	}
	
}
