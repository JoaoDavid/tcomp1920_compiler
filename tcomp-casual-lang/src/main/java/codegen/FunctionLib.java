package codegen;

public class FunctionLib {
	
	protected static final String RES_CALLOC = "calloc";
	protected static final String RES_NEW_INT_ARRAY = "new_int_array";
	protected static final String RES_NEW_INT_MATRIX = "new_int_matrix";
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
	
	protected static final String DEF_NEW_INT_MATRIX = "define dso_local i32** @new_int_matrix(i32 %0, i32 %1) #0 {\r\n" + 
			"  %3 = alloca i32, align 4\r\n" + 
			"  %4 = alloca i32, align 4\r\n" + 
			"  %5 = alloca i32**, align 8\r\n" + 
			"  %6 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %3, align 4\r\n" + 
			"  store i32 %1, i32* %4, align 4\r\n" + 
			"  %7 = load i32, i32* %3, align 4\r\n" + 
			"  %8 = sext i32 %7 to i64\r\n" + 
			"  %9 = call noalias i8* @calloc(i64 %8, i64 8) #3\r\n" + 
			"  %10 = bitcast i8* %9 to i32**\r\n" + 
			"  store i32** %10, i32*** %5, align 8\r\n" + 
			"  store i32 0, i32* %6, align 4\r\n" + 
			"  br label %11\r\n" + 
			"\r\n" + 
			"11:                                               ; preds = %22, %2\r\n" + 
			"  %12 = load i32, i32* %6, align 4\r\n" + 
			"  %13 = load i32, i32* %3, align 4\r\n" + 
			"  %14 = icmp slt i32 %12, %13\r\n" + 
			"  br i1 %14, label %15, label %25\r\n" + 
			"\r\n" + 
			"15:                                               ; preds = %11\r\n" + 
			"  %16 = load i32, i32* %4, align 4\r\n" + 
			"  %17 = call i32* @new_int_array(i32 %16)\r\n" + 
			"  %18 = load i32**, i32*** %5, align 8\r\n" + 
			"  %19 = load i32, i32* %6, align 4\r\n" + 
			"  %20 = sext i32 %19 to i64\r\n" + 
			"  %21 = getelementptr inbounds i32*, i32** %18, i64 %20\r\n" + 
			"  store i32* %17, i32** %21, align 8\r\n" + 
			"  br label %22\r\n" + 
			"\r\n" + 
			"22:                                               ; preds = %15\r\n" + 
			"  %23 = load i32, i32* %6, align 4\r\n" + 
			"  %24 = add nsw i32 %23, 1\r\n" + 
			"  store i32 %24, i32* %6, align 4\r\n" + 
			"  br label %11\r\n" + 
			"\r\n" + 
			"25:                                               ; preds = %11\r\n" + 
			"  %26 = load i32**, i32*** %5, align 8\r\n" + 
			"  ret i32** %26\r\n" + 
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
