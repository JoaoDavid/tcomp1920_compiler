; ModuleID = 'egTwo.c'
source_filename = "egTwo.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [13 x i8] c"sumLitVar: \0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 2, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp ne i32 %4, 0
  %6 = xor i1 %5, true
  %7 = zext i1 %6 to i32
  store i32 %7, i32* %3, align 4
  %8 = call i32 @puts(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0))
  ret i32 3
}

declare dso_local i32 @puts(i8*) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @boolnot() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 2, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp ne i32 %3, 0
  %5 = xor i1 %4, true
  %6 = zext i1 %5 to i32
  store i32 %6, i32* %2, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float @retFloat() #0 {
  %1 = alloca float, align 4
  %2 = alloca float, align 4
  store float 2.000000e+00, float* %1, align 4
  %3 = load float, float* %1, align 4
  %4 = fneg float %3
  store float %4, float* %2, align 4
  ret float 3.500000e+00
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @sum(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  %10 = alloca float, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %11 = load i32, i32* %5, align 4
  %12 = load i32, i32* %6, align 4
  %13 = add nsw i32 %11, %12
  store i32 %13, i32* %9, align 4
  %14 = load float, float* %7, align 4
  %15 = load float, float* %8, align 4
  %16 = fadd float %14, %15
  store float %16, float* %10, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @justArgs(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @minus(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  %10 = alloca float, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %11 = load i32, i32* %5, align 4
  %12 = load i32, i32* %6, align 4
  %13 = sub nsw i32 %11, %12
  store i32 %13, i32* %9, align 4
  %14 = load float, float* %7, align 4
  %15 = load float, float* %8, align 4
  %16 = fsub float %14, %15
  store float %16, float* %10, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @mul(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  %10 = alloca float, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %11 = load i32, i32* %5, align 4
  %12 = load i32, i32* %6, align 4
  %13 = mul nsw i32 %11, %12
  store i32 %13, i32* %9, align 4
  %14 = load float, float* %7, align 4
  %15 = load float, float* %8, align 4
  %16 = fmul float %14, %15
  store float %16, float* %10, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @div(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  %10 = alloca float, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %11 = load i32, i32* %5, align 4
  %12 = load i32, i32* %6, align 4
  %13 = sdiv i32 %11, %12
  store i32 %13, i32* %9, align 4
  %14 = load float, float* %7, align 4
  %15 = load float, float* %8, align 4
  %16 = fdiv float %14, %15
  store float %16, float* %10, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @mod(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %10 = load i32, i32* %5, align 4
  %11 = load i32, i32* %6, align 4
  %12 = srem i32 %10, %11
  store i32 %12, i32* %9, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @less1(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sitofp i32 %10 to double
  %12 = fcmp olt double %11, 7.000000e+00
  %13 = zext i1 %12 to i32
  store i32 %13, i32* %9, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @less2(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  %10 = load float, float* %7, align 4
  %11 = load float, float* %8, align 4
  %12 = fcmp olt float %10, %11
  %13 = zext i1 %12 to i32
  store i32 %13, i32* %9, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @less3(i32 %0, i32 %1, float %2, float %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca float, align 4
  %8 = alloca float, align 4
  %9 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store float %2, float* %7, align 4
  store float %3, float* %8, align 4
  store i32 1, i32* %9, align 4
  ret void
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.1-++20200519095410+f79cd71e145-1~exp1~20200519200813.165 "}
