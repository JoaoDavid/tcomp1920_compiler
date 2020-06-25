#include <stdio.h>


int sumLitVar(int a);

int main() {
    puts("sumLitVar: \n");

    return 3;
}

int testif(int a, int b) {    
    if(a == b) {
	int a = 53;
	a = a + 3;
    }
    return a;
}

int testif2(int a) {   
    int b = 0; 
    if(a) {
	b = a;
    } else {
	b = 5;
    }
    b = b + 2;
    return b;
}

int testif3(int a) {   
    int b = 0; 
    if(a) {
	b = a;
        if(a) {
	   b = a;
        }   else {
	   b = 5;
        }
    } else {
	b = 5;
    }
    b = b + 2;
    return b;
}
