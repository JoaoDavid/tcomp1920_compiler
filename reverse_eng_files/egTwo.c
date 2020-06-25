#include <stdio.h>


int sumLitVar(int a);

int main() {    
    int a = 2;
    int b = !a;
    puts("sumLitVar: \n");

    return 3;
}

void boolnot() {    
    int a = 2;
    int b = !a;
}

float retFloat() {    
    float b = 2.0;
    float c = -b;
    return 3.5;
}

void sum(int a, int b, float c, float d) {    
    int e = a + b;
    float f = c + d;
}


void justArgs(int a, int b, float c, float d) {    

}

void minus(int a, int b, float c, float d) {    
    int e = a - b;
    float f = c - d;
}

void mul(int a, int b, float c, float d) {    
    int e = a * b;
    float f = c * d;
}

void div(int a, int b, float c, float d) {    
    int e = a / b;
    float f = c / d;
}

void mod(int a, int b, float c, float d) {    
    int e = a % b;
}

void less1(int a, int b, float c, float d) {    
    int e = a < 7.0;
}

void less2(int a, int b, float c, float d) { 
    int f = c < d;
}

void less3(int a, int b, float c, float d) {
    int g = 2.0 < 5.0;
}


