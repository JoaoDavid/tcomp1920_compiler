#include <stdio.h>
#include <stdlib.h>

int* new_int_array(int size);
float* new_float_array(int size);
char** new_string_array(int size);
int** new_int_matrix(int size, int size2);
void arrIndex2();

int main() {
    puts("sumLitVar: \n");
    arrIndex2();
    return 3;
}
void assign0() {
    int** matrixInt = new_int_matrix(3, 5);
    matrixInt[2][1] = 4;
}

void assign1() {
    int* arrInt = new_int_array(3);
    arrInt[2] = 3;
}

void arrIndex1() {
    int* arrInt = new_int_array(3);
    int a = arrInt[2];
}

void arrIndex4() {
    int** matrixInt = new_int_matrix(3, 5);
    int a = matrixInt[2][1];
}

void arrIndex2() {
    float* arrFloat = new_float_array(5);
    float a = arrFloat[2];
}

void arrIndex3() {
    char** arrString = new_string_array(6);
    char* a = arrString[2];
}

int** new_int_matrix(int size, int size2) {
    int** m = (int**) calloc(size,sizeof(int*));
    for(int i = 0; i < size; i++) {
        m[i] = new_int_array(size2);
    }
    return m;
}

int* new_int_array(int size) {
    return (int*) calloc(size,sizeof(int));
}

float* new_float_array(int size) {
    return (float*) calloc(size,sizeof(float));
}

char** new_string_array(int size) {
    return (char**) calloc(size,sizeof(char));
}

