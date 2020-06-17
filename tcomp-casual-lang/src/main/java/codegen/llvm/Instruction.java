package codegen.llvm;

import ast.datatype.ArrayType;
import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;

public class Instruction {
	
	public static final String INT_TYPE = "i32";
	public static final String FLOAT_TYPE = "float";
	public static final String BOOL_TYPE = "i1";
	public static final String STRING_TYPE = "i8*";
	public static final String VOID_TYPE = "void";
	
	public static final String AND = "and";
	public static final String OR = "or";
	
	public static final String SUM_INT = "add";
	public static final String SUM_FLOAT = "fadd";
	public static final String SUB_INT = "sub";
	public static final String SUB_FLOAT = "fsub";
	public static final String MUL_INT = "mul";
	public static final String MUL_FLOAT = "fmul";
	public static final String DIV_INT = "sdiv";
	public static final String DIV_FLOAT = "fdiv";
	public static final String MOD_INT = "srem";
	
	public static final String CMP_INT = "icmp";	
	public static final String EQUAL_INT = "eq";
	public static final String NOT_EQUAL_INT = "ne";
	public static final String GREATER_EQUAL_INT = "sge";
	public static final String GREATER_INT = "sgt";
	public static final String LESS_EQUAL_INT = "sle";
	public static final String LESS_INT = "slt";
	
	public static final String CMP_FLOAT = "fcmp";
	public static final String EQUAL_FLOAT = "oeq";
	public static final String NOT_EQUAL_FLOAT = "one";
	public static final String GREATER_EQUAL_FLOAT = "oge";
	public static final String GREATER_FLOAT = "ogt";
	public static final String LESS_EQUAL_FLOAT = "ole";
	public static final String LESS_FLOAT = "olt";
	
	public static String getLLVMType(Type type) {
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
	
	public static String arithmetic(String space, String llVar, String op, Type llvmType, String leftLL, String rightLL) {
		return String.format("%s%s = %s %s %s, %s%n", space, llVar, op, getLLVMType(llvmType), leftLL, rightLL);
	}
	
	public static String cmp(String space, String llVar, String cmp, String op, Type llvmType, String leftLL, String rightLL) {
		//icmp <comp> <tipo> op1, op2
		return String.format("%s%s = %s %s %s %s, %s%n", space, llVar, cmp, op, getLLVMType(llvmType), leftLL, rightLL);
	}
	
	public static String logic(String space, String llVar, String cmp, Type llvmType, String leftLL, String rightLL) {
		//<result> = or <ty> <op1>, <op2>
		return String.format("%s%s = %s %s %s, %s%n", space, llVar, cmp, getLLVMType(llvmType), leftLL, rightLL);
	}

	public static String alloca(String space, String llVar, Type type) {
		return String.format("%s%s = alloca %s%n", space, llVar, getLLVMType(type));
	}
	
	public static String store(String space, Type type, String value, String llVar) {
		return String.format("%sstore %s %s, %s* %s%n", space, getLLVMType(type) , value, getLLVMType(type), llVar);
	}
	
	public static String load(String space, String llvmVar, Type llvmResType, String loadVar) {
		return String.format("%s%s = load %s, %s* %s%n", space, loadVar, getLLVMType(llvmResType), getLLVMType(llvmResType), llvmVar);
	}
	
	public static String fneg(String space, String newVar, String llVar, Type type, String value) {
		//<result> = fneg <ty> <op1>
		return String.format("%s%s = fneg %s %s%n", space, newVar,getLLVMType(type) ,llVar);
	}
	
	public static String xor(String space, String resVar, Type type, String op1, String op2) {
		//<result> = xor <ty> <op1>, <op2> 
		return String.format("%s%s = xor %s %s, %s%n", space, resVar, getLLVMType(type), op1, op2);
	}
	
	public static String call(String space, String resVar, Type type, String funcName, String args) {
		//<result> = call <ty> @<funcName>(<ty_ar> <ar>, ...)
		return String.format("%s%s = call %s @%s(%s)%n", space, resVar, getLLVMType(type), funcName, args);
	}
	
	public static String callVoid(String space, Type type, String funcName, String args) {
		//<result> = call <ty> @<funcName>(<ty_ar> <ar>, ...)
		return String.format("%scall %s @%s(%s)%n", space, getLLVMType(type), funcName, args);
	}
	
	public static String globalStr(int len, String strGlobalVar, String strLit) {
		//@.str = public unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00"
		return String.format("%s = public unnamed_addr constant [%d x i8] c%s%n", strGlobalVar, len, strLit);
	}
	
	public static String getElementPtrStr(int len, String strGlobalVar) {
		//getelementptr inbounds ([15 x i8], [15 x i8]* @.str.1, i64 0, i64 0)
		return String.format("getelementptr inbounds ([%d x i8], [%d x i8]* %s, i64 0, i64 0)", len, len, strGlobalVar);
	}
	
	public static String getElementPtrArr(String space, String newVar, Type type1, Type type2, String var, String index) {
		//getelementptr inbounds i32, i32* %4, i64 2
		//getelementptr inbounds float, float* %4, i64 2
		//getelementptr inbounds i8*, i8** %4, i64 2
		//getelementptr inbounds %s, %s* %s
		return String.format("%s%s = getelementptr inbounds %s, %s %s, i32 %s%n", space, newVar, getLLVMType(type1), getLLVMType(type2), var, index);
	}
	
	public static String br(String space, String condRes, String labelTrue, String labelFalse) {
		//br i1 <cond>, label <iftrue>, label <iffalse>
		//%sbr i1 %s, label %s, label %s
		return String.format("%sbr i1 %s, label %%%s, label %%%s%n", space, condRes, labelTrue, labelFalse);
	}

	public static String brUnconditional(String space, String label) {
		//br label <dest>  
		//%sbr label %s
		return String.format("%sbr label %%%s%n", space, label);
	}
	
	public static String ret(String space, Type type, String value) {
		//ret <type> <value>
		return String.format("%sret %s %s%n", space, getLLVMType(type), value);
	}
	
	public static String label(String label) {
		//label:
		return String.format("%n%s:%n", label);
	}
	
}
