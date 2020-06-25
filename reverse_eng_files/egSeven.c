#include <stdio.h>
#include <stdlib.h>

void printInt(int i);
void printFloat(float f);
void printBool(int b);
void printString(char* str);

int main() {
	printInt(5);
	printFloat(4.8);
	printBool(1);
	printString("ola");
    return 3;
}

void printInt(int i) {
    printf("%d\n", i);
}

void printFloat(float f) {
    printf("%f\n", f);
}

void printBool(int b) {
    printf("%d\n", b);
}

void printString(char* str) {
    printf("%s\n", str);
}
