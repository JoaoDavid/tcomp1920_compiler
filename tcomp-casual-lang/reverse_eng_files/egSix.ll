; ModuleID = 'egSix.c'
source_filename = "egSix.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = call i32 @puts(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0))
  call void @arrIndex2()
  ret i32 3
}

declare dso_local i32 @puts(i8*) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @assign0() #0 {
  %1 = alloca i32**, align 8
  %2 = call i32** @new_int_matrix(i32 3, i32 5)
  store i32** %2, i32*** %1, align 8
  %3 = load i32**, i32*** %1, align 8
  %4 = getelementptr inbounds i32*, i32** %3, i64 2
  %5 = load i32*, i32** %4, align 8
  %6 = getelementptr inbounds i32, i32* %5, i64 1
  store i32 4, i32* %6, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32** @new_int_matrix(i32 %0, i32 %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32**, align 8
  %6 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32 %1, i32* %4, align 4
  %7 = load i32, i32* %3, align 4
  %8 = sext i32 %7 to i64
  %9 = call noalias i8* @calloc(i64 %8, i64 8) #3
  %10 = bitcast i8* %9 to i32**
  store i32** %10, i32*** %5, align 8
  store i32 0, i32* %6, align 4
  br label %11

11:                                               ; preds = %22, %2
  %12 = load i32, i32* %6, align 4
  %13 = load i32, i32* %3, align 4
  %14 = icmp slt i32 %12, %13
  br i1 %14, label %15, label %25

15:                                               ; preds = %11
  %16 = load i32, i32* %4, align 4
  %17 = call i32* @new_int_array(i32 %16)
  %18 = load i32**, i32*** %5, align 8
  %19 = load i32, i32* %6, align 4
  %20 = sext i32 %19 to i64
  %21 = getelementptr inbounds i32*, i32** %18, i64 %20
  store i32* %17, i32** %21, align 8
  br label %22

22:                                               ; preds = %15
  %23 = load i32, i32* %6, align 4
  %24 = add nsw i32 %23, 1
  store i32 %24, i32* %6, align 4
  br label %11

25:                                               ; preds = %11
  %26 = load i32**, i32*** %5, align 8
  ret i32** %26
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @assign1() #0 {
  %1 = alloca i32*, align 8
  %2 = call i32* @new_int_array(i32 3)
  store i32* %2, i32** %1, align 8
  %3 = load i32*, i32** %1, align 8
  %4 = getelementptr inbounds i32, i32* %3, i64 2
  store i32 3, i32* %4, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32* @new_int_array(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3
  %6 = bitcast i8* %5 to i32*
  ret i32* %6
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @arrIndex1() #0 {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = call i32* @new_int_array(i32 3)
  store i32* %3, i32** %1, align 8
  %4 = load i32*, i32** %1, align 8
  %5 = getelementptr inbounds i32, i32* %4, i64 2
  %6 = load i32, i32* %5, align 4
  store i32 %6, i32* %2, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @arrIndex4() #0 {
  %1 = alloca i32**, align 8
  %2 = alloca i32, align 4
  %3 = call i32** @new_int_matrix(i32 3, i32 5)
  store i32** %3, i32*** %1, align 8
  %4 = load i32**, i32*** %1, align 8
  %5 = getelementptr inbounds i32*, i32** %4, i64 2
  %6 = load i32*, i32** %5, align 8
  %7 = getelementptr inbounds i32, i32* %6, i64 1
  %8 = load i32, i32* %7, align 4
  store i32 %8, i32* %2, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @arrIndex2() #0 {
  %1 = alloca float*, align 8
  %2 = alloca float, align 4
  %3 = call float* @new_float_array(i32 5)
  store float* %3, float** %1, align 8
  %4 = load float*, float** %1, align 8
  %5 = getelementptr inbounds float, float* %4, i64 2
  %6 = load float, float* %5, align 4
  store float %6, float* %2, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float* @new_float_array(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* @calloc(i64 %4, i64 4) #3
  %6 = bitcast i8* %5 to float*
  ret float* %6
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @arrIndex3() #0 {
  %1 = alloca i8**, align 8
  %2 = alloca i8*, align 8
  %3 = call i8** @new_string_array(i32 6)
  store i8** %3, i8*** %1, align 8
  %4 = load i8**, i8*** %1, align 8
  %5 = getelementptr inbounds i8*, i8** %4, i64 2
  %6 = load i8*, i8** %5, align 8
  store i8* %6, i8** %2, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8** @new_string_array(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* @calloc(i64 %4, i64 1) #3
  %6 = bitcast i8* %5 to i8**
  ret i8** %6
}

; Function Attrs: nounwind
declare dso_local noalias i8* @calloc(i64, i64) #2

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.1-++20200519095410+f79cd71e145-1~exp1~20200519200813.165 "}
