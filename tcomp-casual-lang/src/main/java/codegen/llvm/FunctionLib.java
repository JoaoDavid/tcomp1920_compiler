package codegen.llvm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FunctionLib {

	public static final String RES_CALLOC = "calloc";
	public static final String RES_PRINTF = "printf";

	public static final String RES_NEW_INT_ARRAY = "new_int_array";	
	public static final String RES_NEW_FLOAT_ARRAY = "new_float_array";
	public static final String RES_NEW_BOOL_ARRAY = "new_bool_array";
	public static final String RES_NEW_STRING_ARRAY = "new_string_array";

	public static final String RES_NEW_INT_MATRIX = "new_int_matrix";
	public static final String RES_NEW_FLOAT_MATRIX = "new_float_matrix";
	public static final String RES_NEW_BOOL_MATRIX = "new_bool_matrix";
	public static final String RES_NEW_STRING_MATRIX = "new_string_matrix";

	public static final String RES_PRINT_INT = "printInt";
	public static final String RES_PRINT_FLOAT = "printFloat";
	public static final String RES_PRINT_BOOL = "printBool";
	public static final String RES_PRINT_STRING = "printString";

	public static final String STR_PRINT_INT = "@.str.1 = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\"\n";
	public static final String STR_PRINT_FLOAT = "@.str.2 = private unnamed_addr constant [4 x i8] c\"%f\\0A\\00\"\n";
	//public static final String STR_PRINT_BOOL = "@.str.1 = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\"\n";
	public static final String STR_PRINT_STRING = "@.str.3 = private unnamed_addr constant [4 x i8] c\"%s\\0A\\00\"\n";

	public static boolean isResLibFuncName(String funcName) {
		return RES_FUNCS.get(funcName) != null;
	}

	public static final String DECL_CALLOC = "declare dso_local noalias i8* @calloc(i64, i64) #2\n";
	public static final String DECL_PRINTF = "declare dso_local i32 @printf(i8*, ...) #1\n";

	public static final String DEF_NEW_INT_ARRAY = "define dso_local i32* @new_int_array(i32 %0) #0 {\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i32*\r\n" + 
			"  ret i32* %6\r\n" + 
			"}\n";

	public static final String DEF_NEW_INT_MATRIX = "define dso_local i32** @new_int_matrix(i32 %0, i32 %1) #0 {\r\n" + 
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

	public static final String DEF_NEW_FLOAT_ARRAY = "define dso_local float* @new_float_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to float*\r\n" + 
			"  ret float* %6\r\n" + 
			"}\n";

	public static final String DEF_NEW_FLOAT_MATRIX = "define dso_local float** @new_float_matrix(i32 %0, i32 %1) #0 {\r\n" + 
			"  %3 = alloca i32, align 4\r\n" + 
			"  %4 = alloca i32, align 4\r\n" + 
			"  %5 = alloca float**, align 8\r\n" + 
			"  %6 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %3, align 4\r\n" + 
			"  store i32 %1, i32* %4, align 4\r\n" + 
			"  %7 = load i32, i32* %3, align 4\r\n" + 
			"  %8 = sext i32 %7 to i64\r\n" + 
			"  %9 = call noalias i8* @calloc(i64 %8, i64 8) #3\r\n" + 
			"  %10 = bitcast i8* %9 to float**\r\n" + 
			"  store float** %10, float*** %5, align 8\r\n" + 
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
			"  %17 = call float* @new_float_array(i32 %16)\r\n" + 
			"  %18 = load float**, float*** %5, align 8\r\n" + 
			"  %19 = load i32, i32* %6, align 4\r\n" + 
			"  %20 = sext i32 %19 to i64\r\n" + 
			"  %21 = getelementptr inbounds float*, float** %18, i64 %20\r\n" + 
			"  store float* %17, float** %21, align 8\r\n" + 
			"  br label %22\r\n" + 
			"\r\n" + 
			"22:                                               ; preds = %15\r\n" + 
			"  %23 = load i32, i32* %6, align 4\r\n" + 
			"  %24 = add nsw i32 %23, 1\r\n" + 
			"  store i32 %24, i32* %6, align 4\r\n" + 
			"  br label %11\r\n" + 
			"\r\n" + 
			"25:                                               ; preds = %11\r\n" + 
			"  %26 = load float**, float*** %5, align 8\r\n" + 
			"  ret float** %26\r\n" + 
			"}\n";

	public static final String DEF_NEW_BOOL_ARRAY = "define dso_local i1* @new_bool_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i1*\r\n" + 
			"  ret i1* %6\r\n" + 
			"}\n";

	public static final String DEF_NEW_BOOL_MATRIX = "define dso_local i1** @new_bool_matrix(i32 %0, i32 %1) #0 {\r\n" + 
			"  %3 = alloca i32, align 4\r\n" + 
			"  %4 = alloca i32, align 4\r\n" + 
			"  %5 = alloca i1**, align 8\r\n" + 
			"  %6 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %3, align 4\r\n" + 
			"  store i32 %1, i32* %4, align 4\r\n" + 
			"  %7 = load i32, i32* %3, align 4\r\n" + 
			"  %8 = sext i32 %7 to i64\r\n" + 
			"  %9 = call noalias i8* @calloc(i64 %8, i64 8) #3\r\n" + 
			"  %10 = bitcast i8* %9 to i1**\r\n" + 
			"  store i1** %10, i1*** %5, align 8\r\n" + 
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
			"  %17 = call i1* @new_bool_array(i32 %16)\r\n" + 
			"  %18 = load i1**, i1*** %5, align 8\r\n" + 
			"  %19 = load i32, i32* %6, align 4\r\n" + 
			"  %20 = sext i32 %19 to i64\r\n" + 
			"  %21 = getelementptr inbounds i1*, i1** %18, i64 %20\r\n" + 
			"  store i1* %17, i1** %21, align 8\r\n" + 
			"  br label %22\r\n" + 
			"\r\n" + 
			"22:                                               ; preds = %15\r\n" + 
			"  %23 = load i32, i32* %6, align 4\r\n" + 
			"  %24 = add nsw i32 %23, 1\r\n" + 
			"  store i32 %24, i32* %6, align 4\r\n" + 
			"  br label %11\r\n" + 
			"\r\n" + 
			"25:                                               ; preds = %11\r\n" + 
			"  %26 = load i1**, i1*** %5, align 8\r\n" + 
			"  ret i1** %26\r\n" + 
			"}\n";

	public static final String DEF_NEW_STRING_ARRAY = "define dso_local i8** @new_string_array(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = sext i32 %3 to i64\r\n" + 
			"  %5 = call noalias i8* @calloc(i64 %4, i64 1) #3\r\n" + 
			"  %6 = bitcast i8* %5 to i8**\r\n" + 
			"  ret i8** %6\r\n" + 
			"}\n";

	public static final String DEF_NEW_STRING_MATRIX = "define dso_local i8*** @new_string_matrix(i32 %0, i32 %1) #0 {\r\n" + 
			"  %3 = alloca i32, align 4\r\n" + 
			"  %4 = alloca i32, align 4\r\n" + 
			"  %5 = alloca i8***, align 8\r\n" + 
			"  %6 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %3, align 4\r\n" + 
			"  store i32 %1, i32* %4, align 4\r\n" + 
			"  %7 = load i32, i32* %3, align 4\r\n" + 
			"  %8 = sext i32 %7 to i64\r\n" + 
			"  %9 = call noalias i8* @calloc(i64 %8, i64 8) #3\r\n" + 
			"  %10 = bitcast i8* %9 to i8***\r\n" + 
			"  store i8*** %10, i8**** %5, align 8\r\n" + 
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
			"  %17 = call i8** @new_string_array(i32 %16)\r\n" + 
			"  %18 = load i8***, i8**** %5, align 8\r\n" + 
			"  %19 = load i32, i32* %6, align 4\r\n" + 
			"  %20 = sext i32 %19 to i64\r\n" + 
			"  %21 = getelementptr inbounds i8**, i8*** %18, i64 %20\r\n" + 
			"  store i8** %17, i8*** %21, align 8\r\n" + 
			"  br label %22\r\n" + 
			"\r\n" + 
			"22:                                               ; preds = %15\r\n" + 
			"  %23 = load i32, i32* %6, align 4\r\n" + 
			"  %24 = add nsw i32 %23, 1\r\n" + 
			"  store i32 %24, i32* %6, align 4\r\n" + 
			"  br label %11\r\n" + 
			"\r\n" + 
			"25:                                               ; preds = %11\r\n" + 
			"  %26 = load i8***, i8**** %5, align 8\r\n" + 
			"  ret i8*** %26\r\n" + 
			"}\n";

	public static final String DEF_PRINT_INT = "define dso_local void @printInt(i32 %0) #0 {\r\n" + 
			"  %2 = alloca i32, align 4\r\n" + 
			"  store i32 %0, i32* %2, align 4\r\n" + 
			"  %3 = load i32, i32* %2, align 4\r\n" + 
			"  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i32 %3)\r\n" + 
			"  ret void\r\n" + 
			"}\n";

	public static final String DEF_PRINT_FLOAT = "define dso_local void @printFloat(float %0) #0 {\r\n" + 
			"  %2 = alloca float, align 4\r\n" + 
			"  store float %0, float* %2, align 4\r\n" + 
			"  %3 = load float, float* %2, align 4\r\n" + 
			"  %4 = fpext float %3 to double\r\n" + 
			"  %5 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2, i64 0, i64 0), double %4)\r\n" + 
			"  ret void\r\n" + 
			"}\n";

	public static final String DEF_PRINT_BOOL = "define dso_local void @printBool(i1 %0) #0 {\r\n" + 
			"  %2 = alloca i1, align 4\r\n" + 
			"  store i1 %0, i1* %2, align 4\r\n" + 
			"  %3 = load i1, i1* %2, align 4\r\n" + 
			"  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i1 %3)\r\n" + 
			"  ret void\r\n" + 
			"}\n";

	public static final String DEF_PRINT_STRING = "define dso_local void @printString(i8* %0) #0 {\r\n" + 
			"  %2 = alloca i8*, align 8\r\n" + 
			"  store i8* %0, i8** %2, align 8\r\n" + 
			"  %3 = load i8*, i8** %2, align 8\r\n" + 
			"  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i8* %3)\r\n" + 
			"  ret void\r\n" + 
			"}\n";

	private static final HashMap<String,String> RES_FUNCS = new HashMap<String,String>();
	static { 
		RES_FUNCS.put(RES_CALLOC, DECL_CALLOC);
		RES_FUNCS.put(RES_PRINTF, DECL_PRINTF);
		RES_FUNCS.put(RES_NEW_INT_ARRAY, DEF_NEW_INT_ARRAY);
		RES_FUNCS.put(RES_NEW_FLOAT_ARRAY, DEF_NEW_FLOAT_ARRAY);
		RES_FUNCS.put(RES_NEW_BOOL_ARRAY, DEF_NEW_BOOL_ARRAY);
		RES_FUNCS.put(RES_NEW_STRING_ARRAY, DEF_NEW_STRING_ARRAY);
		RES_FUNCS.put(RES_NEW_INT_MATRIX, DEF_NEW_INT_MATRIX);
		RES_FUNCS.put(RES_NEW_FLOAT_MATRIX, DEF_NEW_FLOAT_MATRIX);
		RES_FUNCS.put(RES_NEW_BOOL_MATRIX, DEF_NEW_BOOL_MATRIX);
		RES_FUNCS.put(RES_NEW_STRING_MATRIX, DEF_NEW_STRING_MATRIX);
		RES_FUNCS.put(RES_PRINT_INT, DEF_PRINT_INT);
		RES_FUNCS.put(RES_PRINT_FLOAT, DEF_PRINT_FLOAT);
		RES_FUNCS.put(RES_PRINT_BOOL, DEF_PRINT_BOOL);
		RES_FUNCS.put(RES_PRINT_STRING, DEF_PRINT_STRING);
	} 
	
	private Set<String> set; 
	
	public FunctionLib() {	
		set = new HashSet<String>();	
	}
	
	public Iterator<String> getDecls() {
		return set.iterator();
	}

	public void addDecl(String funcName) {
		set.add(RES_FUNCS.get(funcName));
		switch(funcName) {		
		case RES_NEW_INT_ARRAY:
			set.add(RES_FUNCS.get(RES_CALLOC));
			break; 
		case RES_NEW_FLOAT_ARRAY: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			break; 
		case RES_NEW_BOOL_ARRAY: 
			set.add(RES_FUNCS.get(RES_CALLOC)); 
			break; 
		case RES_NEW_STRING_ARRAY: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			break; 
		case RES_NEW_INT_MATRIX: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			set.add(RES_FUNCS.get(RES_NEW_INT_ARRAY));
			break; 
		case RES_NEW_FLOAT_MATRIX: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			set.add(RES_FUNCS.get(RES_NEW_FLOAT_ARRAY));
			break; 
		case RES_NEW_BOOL_MATRIX: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			set.add(RES_FUNCS.get(RES_NEW_BOOL_ARRAY));
			break; 
		case RES_NEW_STRING_MATRIX: 
			set.add(RES_FUNCS.get(RES_CALLOC));
			set.add(RES_FUNCS.get(RES_NEW_STRING_ARRAY));
			break; 
		case RES_PRINT_INT: 
			set.add(RES_FUNCS.get(RES_PRINTF));
			//set.add(STR_PRINT_INT);
			break; 
		case RES_PRINT_FLOAT: 
			set.add(RES_FUNCS.get(RES_PRINTF));
			//set.add(STR_PRINT_FLOAT);
			break; 
		case RES_PRINT_BOOL: 
			set.add(RES_FUNCS.get(RES_PRINTF));
			//set.add(STR_PRINT_INT);
			break; 
		case RES_PRINT_STRING: 
			set.add(RES_FUNCS.get(RES_PRINTF));
			//set.add(STR_PRINT_STRING);
			break; 
		default: 
			
		}
	}

}
