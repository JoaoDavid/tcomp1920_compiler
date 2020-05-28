package codegen;

import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;

public class ConfigLLVM {
	
	private static final String INT_TYPE = "i32";
	private static final String FLOAT_TYPE = "float";
	private static final String BOOL_TYPE = "i1";
	private static final String STRING_TYPE = "i8*";
	private static final String VOID_TYPE = "void";
	
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
		}
		return null;
	}

}
