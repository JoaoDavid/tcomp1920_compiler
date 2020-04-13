# tcomp_1920_project

Phase 1

1) Install ANTLR 4 IDE 0.3.6 from Eclipse Marketplace

2) Import projects CasualCompiler_49448 and CasualGrammar_49448

3) Go to CasualGrammar_49448's properties->ANTLR 4->Tool->Add ANTLR version 4.8

4) Go to CasualCompiler_49448's properties->Java Build Path->add antlr-4.8-complete.jar

5) Change Java library location from both project to your own location

6) Change CasualCompiler_49448's folder antlr4 location to "your_path"/tcomp_1920_project\CasualGrammar_49448\target\generated-sources\antlr4


7) Run Casual.g4 from CasualGrammar_49448

8) Run CasualC from CasualCompiler_49448 using source_file.cas as argument

If the source code within the file provided has any syntactic errors, red error messages will appear with the line number where the error is


Software

Download antlr-4.8-complete.jar at section: ANTLR tool and Java Target

https://www.antlr.org/download.html

