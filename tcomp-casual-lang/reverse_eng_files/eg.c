#include <stdio.h>

float retFloat();
void sumVar(int a, int b);
int sumLitVar(int a);
void allTypes(int a, float b, char* c);

int main() {    
    int a = 2;
    puts("sumLitVar: \n");

    return 3;
}

void strHandle() {    
    char* str = "myStr\\";
}

void strHandle2(char* strAr) {    
    char* str = strAr;
}

float retFloat() {    
    float b = 2.0;
    return 3.5;
}

void sumVar(int a, int b) {    
    int c = a + b;
}

void sumVarFloat(float a, float b) {    
    float c = a + b;
    return ;
}

int sumLitVar(int a) {    
    int c = 1 + a;
    return c;
}

void allTypes(int a, float b, char* c) {    

    return ;
}

int vetorfuncInt(int* vetor) {    
    vetor[2] = 4;

    return vetor[11];
}

int* matrixfuncInt(int** vetor) {    
    vetor[2][6] = 4;

    return vetor[9];
}

float vetorfuncFloat(float* vetor) {    
    vetor[2] = 4.6;

    return vetor[3];
}

float* matrixfuncFloat(float** vetor) {    
    vetor[2][6] = 4.0;

    return vetor[6];
}

char* vetorfuncString(char** vetor) {    
    vetor[2] = "fe";

    return vetor[4];
}
