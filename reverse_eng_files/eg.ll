; ModuleID = 'eg.c'
source_filename = "eg.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00", align 1
@.str.1 = private unnamed_addr constant [7 x i8] c"myStr\\\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"fe\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 2, i32* %2, align 4
  %3 = call i32 @puts(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0))
  ret i32 3
}

declare dso_local i32 @puts(i8*) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @strHandle() #0 {
  %1 = alloca i8*, align 8
  store i8* getelementptr inbounds ([7 x i8], [7 x i8]* @.str.1, i64 0, i64 0), i8** %1, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @strHandle2(i8* %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  store i8* %4, i8** %3, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float @retFloat() #0 {
  %1 = alloca float, align 4
  store float 2.000000e+00, float* %1, align 4
  ret float 3.500000e+00
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @sumVar(i32 %0, i32 %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32 %1, i32* %4, align 4
  %6 = load i32, i32* %3, align 4
  %7 = load i32, i32* %4, align 4
  %8 = add nsw i32 %6, %7
  store i32 %8, i32* %5, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @sumVarFloat(float %0, float %1) #0 {
  %3 = alloca float, align 4
  %4 = alloca float, align 4
  %5 = alloca float, align 4
  store float %0, float* %3, align 4
  store float %1, float* %4, align 4
  %6 = load float, float* %3, align 4
  %7 = load float, float* %4, align 4
  %8 = fadd float %6, %7
  store float %8, float* %5, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @sumLitVar(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  %5 = add nsw i32 1, %4
  store i32 %5, i32* %3, align 4
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @allTypes(i32 %0, float %1, i8* %2) #0 {
  %4 = alloca i32, align 4
  %5 = alloca float, align 4
  %6 = alloca i8*, align 8
  store i32 %0, i32* %4, align 4
  store float %1, float* %5, align 4
  store i8* %2, i8** %6, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @vetorfuncInt(i32* %0) #0 {
  %2 = alloca i32*, align 8
  store i32* %0, i32** %2, align 8
  %3 = load i32*, i32** %2, align 8
  %4 = getelementptr inbounds i32, i32* %3, i64 2
  store i32 4, i32* %4, align 4
  %5 = load i32*, i32** %2, align 8
  %6 = getelementptr inbounds i32, i32* %5, i64 11
  %7 = load i32, i32* %6, align 4
  ret i32 %7
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32* @matrixfuncInt(i32** %0) #0 {
  %2 = alloca i32**, align 8
  store i32** %0, i32*** %2, align 8
  %3 = load i32**, i32*** %2, align 8
  %4 = getelementptr inbounds i32*, i32** %3, i64 2
  %5 = load i32*, i32** %4, align 8
  %6 = getelementptr inbounds i32, i32* %5, i64 6
  store i32 4, i32* %6, align 4
  %7 = load i32**, i32*** %2, align 8
  %8 = getelementptr inbounds i32*, i32** %7, i64 9
  %9 = load i32*, i32** %8, align 8
  ret i32* %9
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float @vetorfuncFloat(float* %0) #0 {
  %2 = alloca float*, align 8
  store float* %0, float** %2, align 8
  %3 = load float*, float** %2, align 8
  %4 = getelementptr inbounds float, float* %3, i64 2
  store float 0x4012666660000000, float* %4, align 4
  %5 = load float*, float** %2, align 8
  %6 = getelementptr inbounds float, float* %5, i64 3
  %7 = load float, float* %6, align 4
  ret float %7
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float* @matrixfuncFloat(float** %0) #0 {
  %2 = alloca float**, align 8
  store float** %0, float*** %2, align 8
  %3 = load float**, float*** %2, align 8
  %4 = getelementptr inbounds float*, float** %3, i64 2
  %5 = load float*, float** %4, align 8
  %6 = getelementptr inbounds float, float* %5, i64 6
  store float 4.000000e+00, float* %6, align 4
  %7 = load float**, float*** %2, align 8
  %8 = getelementptr inbounds float*, float** %7, i64 6
  %9 = load float*, float** %8, align 8
  ret float* %9
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @vetorfuncString(i8** %0) #0 {
  %2 = alloca i8**, align 8
  store i8** %0, i8*** %2, align 8
  %3 = load i8**, i8*** %2, align 8
  %4 = getelementptr inbounds i8*, i8** %3, i64 2
  store i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i8** %4, align 8
  %5 = load i8**, i8*** %2, align 8
  %6 = getelementptr inbounds i8*, i8** %5, i64 4
  %7 = load i8*, i8** %6, align 8
  ret i8* %7
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.1-++20200519095410+f79cd71e145-1~exp1~20200519200813.165 "}
