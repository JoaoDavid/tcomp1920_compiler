#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getInt();
int getInt2(int i);
float getFloat();
char* getString();

void test2();
void test3();

char* my_itoa(int number) {
   char str[20]; //create an empty string to store number
   sprintf(str, "%d", number); //make the number into string using sprintf function
   return str;
}

int main() {    
    int a = getInt();
    float b = getFloat();
    char* c = getString();
    puts(c);
    return 3;
}

void test2() {    
    int a = getInt() + getInt();
}

void test3() {    
    int a = getInt2(4) + getInt();
}

int getInt() {    
    int a = 2;
    return a;
}

int getInt2(int i) {
    int a = getInt2(i);
    return i;
}

float getFloat() {    
    float a = 2.0;
    return a;
}

char* getString() {    
    char* a = "ola my friend\n";
    return a;
}

