# tcomp_1920_project

 - Install Instructions -

1) Import as Maven Project

2) All the dependencies will be installed automatically

3) Run CasualC.java with a cas source file path as argument

If the source code within the file provided has any syntactic errors, red error messages will appear with the line number where the error is
with the name of the exception thrown as well

----------------------------------------------------

The project does not require the ANTLR 4 IDE extention for eclipse
But if this extension is installed, it can cause some conflits
because it will run the grammar file (Casual.g4) with an older version of ANTLR

If such thing happens, follow this solution:

1) Eclipse -> Window -> Preferences -> ANTLR 4 -> Tool -> Uncheck "Tool is activated"


----------------------------------------------------

--- Valid Example Files ---
valid_example1.cas
valid_example2.cas
valid_example3.cas
valid_example4.cas
valid_example5.cas
benchmark.cas
hello_world.cas

--- INvalid Example Files ---
invalid_example1.cas
invalid_example2.cas
invalid_example3.cas
invalid_example4.cas
invalid_example5.cas



Software

https://www.antlr.org/download.html

