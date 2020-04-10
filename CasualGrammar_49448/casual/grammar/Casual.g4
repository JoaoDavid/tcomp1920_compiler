grammar Casual;


@header{
	package casual.grammar;
}


prog: (func_decl | func_def)+ EOF ;
         
// ------------------------- FUNCTIONS -------------------------

func_args: ID '(' (var_decl (',' var_decl)*)?   ')' ':' ID ;
func_decl: 'decl' func_args ';';
func_def: 'def' func_args '{'
				statement*
		  '}' ;
func_inv: ID '(' (expr (',' expr)*)?   ')' ;

// ------------------------- STATEMENTEMENTS -------------------------

statement: if_stat 
		 | while_stat
		 | return_stat 
		 | var_decl_assign_stat 
		 | var_assign_stat
		 | expr ';'
		 ;

return_stat: 'return' expr? ';' ;

var_decl         : ID ':' ID ;
var_decl_assign_stat  : var_decl '=' expr ';' ;
var_assign_stat  : (ID | arr_l_value) '=' expr ';' ;

if_stat: 'if' expr '{'
				statement*
		 '}'
			('else' '{'
				statement*
		  '}')? ;
			
while_stat:	'while' expr '{'
				statement*
			'}' ;



// ------------------------- EXPRESSIONS -------------------------
			
expr:	expr binary_ope expr
    |	unary_ope expr
    |   func_inv
    |   arr_r_value
    |   arr_l_value
    |	BOOL
    |	INT
    |	FLOAT
    |	STRING
    |	ID
    |	'(' expr ')'
    ;
    
// ------------------------- ARRAYS -------------------------

arr_r_value : (ID | func_inv) '[' expr ']' ;
arr_l_value  : ID '[' expr ']' ;


// ------------------------- DATA TYPES -------------------------
BOOL    : 'true' | 'false' ;
INT     : [0-9]('_'*[0-9])* ;
FLOAT   : [0-9]*'.'?[0-9]+ ;
STRING  : '"'(~[\\"]| '\\'[btnfr"'\\] | ' ')*'"' ;
        
   

// ------------------------- OPERATORS -------------------------
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

// ------------------------- ARRAYS -------------------------

//Identifier
ID     : [a-zA-Z_][a-zA-Z0-9_]* ;

//whitespace insensitive
WS : [ \r\t\n]+ -> skip ;

//comment line
COMMENT : '#' ~( '\r' | '\n' )* -> skip ; 