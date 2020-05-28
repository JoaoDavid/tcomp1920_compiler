package codegen;

import ast.datatype.BoolType;
import ast.datatype.FloatType;
import ast.datatype.IntType;
import ast.datatype.StringType;
import ast.datatype.Type;
import ast.datatype.VoidType;

public class ConfigLLVM {
	
	private static final String INT_TYPE = "i32";
	private static final String VOID_TYPE = "void";
	
	protected static String getLLVMType(Type type) {
		if(type instanceof IntType) {
			return INT_TYPE;
		} else if(type instanceof FloatType) {
			
		} else if(type instanceof BoolType) {
			
		} else if(type instanceof StringType) {
			
		} else if(type instanceof VoidType) {
			return VOID_TYPE;
		}
		return "";
	}

}
