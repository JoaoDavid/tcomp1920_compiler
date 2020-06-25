; ModuleID = 'egThree.c'
source_filename = "egThree.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [15 x i8] c"ola my friend\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @my_itoa(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca [20 x i8], align 16
  store i32 %0, i32* %2, align 4
  %4 = getelementptr inbounds [20 x i8], [20 x i8]* %3, i64 0, i64 0
  %5 = load i32, i32* %2, align 4
  %6 = call i32 (i8*, i8*, ...) @sprintf(i8* %4, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32 %5) #3
  %7 = getelementptr inbounds [20 x i8], [20 x i8]* %3, i64 0, i64 0
  ret i8* %7
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca float, align 4
  %4 = alloca i8*, align 8
  store i32 0, i32* %1, align 4
  %5 = call i32 @getInt()
  store i32 %5, i32* %2, align 4
  %6 = call float @getFloat()
  store float %6, float* %3, align 4
  %7 = call i8* @getString()
  store i8* %7, i8** %4, align 8
  %8 = load i8*, i8** %4, align 8
  %9 = call i32 @puts(i8* %8)
  ret i32 3
}

declare dso_local i32 @puts(i8*) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @test2() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 @getInt()
  %3 = call i32 @getInt()
  %4 = add nsw i32 %2, %3
  store i32 %4, i32* %1, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @test3() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 @getInt2(i32 4)
  %3 = call i32 @getInt()
  %4 = add nsw i32 %2, %3
  store i32 %4, i32* %1, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt2(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  %5 = call i32 @getInt2(i32 %4)
  store i32 %5, i32* %3, align 4
  %6 = load i32, i32* %2, align 4
  ret i32 %6
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  store i32 2, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  ret i32 %2
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float @getFloat() #0 {
  %1 = alloca float, align 4
  store float 2.000000e+00, float* %1, align 4
  %2 = load float, float* %1, align 4
  ret float %2
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @getString() #0 {
  %1 = alloca i8*, align 8
  store i8* getelementptr inbounds ([15 x i8], [15 x i8]* @.str.1, i64 0, i64 0), i8** %1, align 8
  %2 = load i8*, i8** %1, align 8
  ret i8* %2
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.1-++20200519095410+f79cd71e145-1~exp1~20200519200813.165 "}
