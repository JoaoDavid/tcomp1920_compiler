grammar Casual;


@header{
	package casual.grammar;
}
WS : [ \r\t\n]+ -> skip ; //whitespace insensitive

prog     : func_decl prog*
		 | func_def prog* 
		 | EOF
         ;
         
func_args: VAR '(' (var_decl (',' var_decl)*)?   ')' ':' (TYPE | 'Void') ;
func_decl: 'decl' func_args ';';
func_def: 'def' func_args '{'
				statement*
		  '}' ;


// ------------------------- STATEMENTEMENTS -------------------------

statement: if_stat 
		 | while_stat
		 | return_stat 
		 | var_decl_assign 
		 | var_assign_stat
		 ;

return_stat: 'return' expr? ';' ;

var_decl         : VAR ':' TYPE ;
var_decl_assign  : var_decl '=' expr ';' ;
var_assign_stat  : VAR '=' expr ';' ;

if_stat: 'if' expr '{'
				statement*
		 '}'
			('else' '{'
				statement*
		  '}')? ;
			
while_stat:	'while' expr '{'
				statement*
			'}' ;
			
// -------------------------------------------------------------------



// ------------------------- EXPRESSIONS -------------------------
			
expr:	expr binary_ope expr
    |	unary_ope expr
    |   func_inv
    |   index_access
    |	BOOL
    |	INT
    |	FLOAT
    |	STRING
    |	VAR
    |	'(' expr ')'
    ;
    
func_inv: VAR '(' (expr (',' expr)*)?   ')' ;

index_access: (VAR | func_inv) '[' expr ']' ;


//TYPES
TYPE	: 'Bool' | 'Int' | 'Float' | 'String' ;
BOOL    : 'true' | 'false' ;
INT     : [0-9]('_'*[0-9])* ;
FLOAT   : [0-9]?'.'?[0-9]+ ;
STRING  : '"'[A-z0-9\\]*'"' ;

// BINARY OPERATORS
binary_ope  : '&&' 
            | '||'
		 	| '=='
		 	| '!='
		 	| '>='
		 	| '>'
		 	| '<='
		 	| '<'
		 	| '+'
		 	| '-'
		 	| '*'
		 	| '/'
		 	| '%'
			;
			
unary_ope   : '!' ;


VAR     : [a-z_][a-z0-9_]* ;
COMMENT : '#' ~( '\r' | '\n' )* -> skip ;