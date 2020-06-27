Install Instructions
====================

CasualC script
--------------

This script accepts one or more casual source files, all will output a binary with the same name as the first casual file passed
For instance, casualc hello.cas math.cas util.cas will output the binary hello

Syntax Highlighter
------------------

1) Go to the extensions tab

2) Click on the three dots, Install from VSIX...

3) Select the casual-syntax-highlighter-0.0.1.vsix file in the run directory


Maven Project
-------------

1) Import as Maven Project

2) All the dependencies will be installed automatically

3) Run CasualC.java with one or more .cas source file paths as argument

CAUTION: If passing more than one source file, make sure the import are within the source files are correct, an exception may be raised if they are not

If the source code within the file provided has any syntactic errors, red error messages will appear with the line number where the error is
and the file where it happened, the name of the exception will be shown as well

----------------------------------------------------

The project does not require the ANTLR 4 IDE extention for eclipse
But if this extension is installed, it can cause some conflits
because it will run the grammar file (Casual.g4) with an older version of ANTLR

If such thing happens, follow this solution:

1) Eclipse -> Window -> Preferences -> ANTLR 4 -> Tool -> Uncheck "Tool is activated"

Example files
-------------

All valid example files are located in the ./cas_files directory
Some of those files have well known algorithms written in casual, such as fibonacci, quicksort and bubblesort.


The invalid eExample files are located int the ./cas_files/invalid_examples directory
This source files will raise an exception when passed to the compiler
The exception to be thrown is written in a comment file inside each file




Software

https://www.antlr.org/download.html

