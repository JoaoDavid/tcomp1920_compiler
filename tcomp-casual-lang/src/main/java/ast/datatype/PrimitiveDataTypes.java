package ast.datatype;

public class PrimitiveDataTypes {
	
	public static final String BOOL = "Bool";
	
	public static final String INT = "Int";
	
	public static final String FLOAT = "Float";
	
	public static final String STRING = "String";
	
	public static final String VOID = "Void";
	
	public static Type parseStringType(String type) {
		if(type.equals(BOOL)) {
			return new BoolType();
		} else if(type.equals(INT)){
			return new IntType();
		} else if(type.equals(FLOAT)){
			return new FloatType();
		} else if(type.equals(STRING)){
			return new StringType();
		} else if(type.equals(VOID)){
			return new VoidType();
		} else {
			return new CustomType(type);
		}
	}

}
