#include <stdio.h>


int main() {
    puts("sumLitVar: \n");

    return 3;
}

int testwhile(int a, int b) {    
    while(a == b) {
	int a = 53;
	a = a + 3;
    }
    return a;
}

int testhile2(int a) {   
    int b = 0; 
    while(a) {
	b = a;
    } 
    b = b + 2;
    return b;
}

int testwhile3(int a) {   
    int b = 0; 
    while(a) {
	b = a;
        if(a) {
	   b = a;
        }   else {
	   b = 5;
        }
    } 
    b = b + 2;
    return b;
}
