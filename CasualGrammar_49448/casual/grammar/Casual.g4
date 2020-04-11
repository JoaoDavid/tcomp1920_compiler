grammar Casual;


@header{
	package casual.grammar;
}


// ------------------------- PROGRAM -------------------------

prog: (func_decl | func_def)+ EOF ;


// ------------------------- KEYWORDS -------------------------

K_DECL   : 'decl';
K_DEF    : 'def';
K_IF     : 'if' ;
K_ELSE   : 'else' ;
K_WHILE  : 'while' ;
K_RETURN : 'return' ;




         
// ------------------------- FUNCTIONS -------------------------

func_args: ID '(' (var_type (',' var_type)*)?   ')' ':' datatype ;
func_decl: K_DECL func_args;
func_def: K_DEF func_args '{'
				statement*
		  '}' ;
func_inv: ID '(' (expr (',' expr)*)?   ')' ;


// ------------------------- STATEMENTEMENTS -------------------------

statement: if_stat 
		 | while_stat
		 | return_stat
		 | var_decl_stat 
		 | var_assign_stat
		 | expr ';'
		 ;

return_stat: K_RETURN expr? ';' ;

datatype    : ID
             | '[' datatype ']' ;
             
var_decl_stat  : var_type ('=' expr)? ';' ;
var_assign_stat  : (ID | arr_l_value) '=' expr ';' ;            
             
var_type         : ID ':' datatype ;



if_stat: K_IF expr '{'
				statement*
		 '}' (K_ELSE '{'
				statement*
		  '}')? ;
			
while_stat:	K_WHILE expr '{'
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

arr_r_value : (ID | func_inv) ('[' expr ']')+ ;
arr_l_value  : ID ('[' expr ']')+ ;


// ------------------------- DATA TYPES -------------------------
BOOL    : 'true' | 'false' ;
INT     : '-'?[0-9]('_'*[0-9])* ;
FLOAT   : '-'?[0-9]*'.'?[0-9]+ ;
STRING  : '"'(~[\\"]| '\\'[btnfr"'\\] | ' ')*'"' ;
        

// ------------------------- OPERATORS -------------------------

//Arithmetic
PLUS       :  '+';
MINUS      :  '-';
TIMES      :  '*';
DIV        :  '/';
MOD        :  '%';

//Logical
AND        :  '&&';
OR         :  '||';
NOT        :  '!';

//Comparison
EQUAL      :  '==';
NOT_EQUAL  :  '!=';
GREATER_EQ :  '>=';
GREATER    :  '>';
LESS_EQ    :  '<=';
LESS       :  '<';

binary_ope  : AND 
            | OR
		 	| EQUAL
		 	| NOT_EQUAL
		 	| GREATER_EQ
		 	| GREATER
		 	| LESS_EQ
		 	| LESS
		 	| PLUS
		 	| MINUS
		 	| TIMES
		 	| DIV
		 	| MOD
			;
			
unary_ope   : NOT ;


// ------------------------- ARRAYS -------------------------

//Identifier
ID     : [a-zA-Z_][a-zA-Z0-9_]* ;

//whitespace insensitive
WS : [ \r\t\n]+ -> skip ;

//comment line
COMMENT : '#' ~( '\r' | '\n' )* -> skip ; 