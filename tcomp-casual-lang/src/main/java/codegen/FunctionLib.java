package codegen;

public class FunctionLib {
	
	protected static final String RES_CALLOC = "calloc";
	protected static final String RES_NEW_INT_ARRAY = "new_int_array";
	protected static final String RES_NEW_FLOAT_ARRAY = "new_float_array";
	protected static final String RES_NEW_BOOL_ARRAY = "new_bool_array";
	protected static final String RES_NEW_STRING_ARRAY = "new_string_array";
	
	protected static final String DECL_CALLOC = "declare dso_local noalias i8* @calloc(i64, i64) #2\n";

	protected static final String DEF_NEW_INT_ARRAY = "define dso_local i32* @new_int_array(i32 %0) #0 {\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i32*\r\n" + 
			"  ret i32* %6\r\n" + 
			"}\n";
	
	protected static final String DEF_NEW_FLOAT_ARRAY = "define dso_local float* @new_float_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to float*\r\n" + 
			"  ret float* %6\r\n" + 
			"}\n";
	
	protected static final String DEF_NEW_BOOL_ARRAY = "define dso_local i1* @new_bool_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i1*\r\n" + 
			"  ret i1* %6\r\n" + 
			"}\n";
	
	protected static final String DEF_NEW_STRING_ARRAY = "define dso_local i8** @new_string_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 1) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i8**\r\n" + 
			"  ret i8** %6\r\n" + 
			"}\n";
	
}
