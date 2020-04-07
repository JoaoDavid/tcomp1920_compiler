grammar Casual;


@header{
	package casual.grammar;
}

prog:	(expr NEWLINE)* ;

func_args: VAR '(' (var_decl (',' var_decl)*)?   ')' ':' (TYPE | 'Void') ;
func_decl: 'decl' func_args;
func_def: 'def' func_args '{' NEWLINE
				(statement* NEWLINE)*
			'}' ;
			
statement: if_stat | return_stat | var_decl_assign | var_assign;
return_stat: 'return' expr? ';' ;
if_stat: 'if' expr '{' NEWLINE
				(statement* NEWLINE)*
			'}' NEWLINE
			('else' '{'
				(statement* NEWLINE)*
			'}')? ;
			

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
    


var_decl:	VAR ':' TYPE ;
var_decl_assign:	var_decl '=' expr ;
var_assign: VAR '=' expr ;


NEWLINE : [\r\n]+ ;

//TYPES
TYPE	: 'Bool' | 'Int' | 'Float' | 'String' ;
BOOL    : 'true' | 'false' ;
INT     : [0-9]('_'*[0-9])* ;
FLOAT   : [0-9]?'.'?[0-9]+ ;
STRING  : '"'[a-z'\']*'"' ;


VAR     : [a-z_][a-z0-9_]* ;
COMMENT : '#'.* ;