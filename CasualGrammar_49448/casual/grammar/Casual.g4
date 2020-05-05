grammar Casual;


@header{
	package casual.grammar;
}


// ------------------------- PROGRAM -------------------------

program: (func_decl | func_def)+ EOF ;


// ------------------------- KEYWORDS -------------------------

DECL   : 'decl';
DEF    : 'def';
IF     : 'if' ;
ELSE   : 'else' ;
WHILE  : 'while' ;
RETURN : 'return' ;


// ------------------------- STATEMENTS -------------------------

statement: if_stat 
		 | while_stat
		 | return_stat
		 | var_decl_stat 
		 | var_assign_stat
		 | expr_stat
		 ;
		 
expr_stat: expr SEMICOLON ;

return_stat : RETURN expr? SEMICOLON ;

datatype    : ID
            | L_SQR_BR datatype R_SQR_BR ;
             
var_decl_stat  : var_type (ASSIGN expr)? SEMICOLON ;
var_assign_stat  : (ID | arr_l_value) ASSIGN expr SEMICOLON ;            
             
var_type         : ID COLON datatype ;



if_stat: IF expr L_CRL_BR
				statement*
		 R_CRL_BR else_block? ;
else_block: ELSE L_CRL_BR
				statement*
		    R_CRL_BR;	
			
while_stat:	WHILE expr L_CRL_BR
				statement*
			R_CRL_BR ;


// ------------------------- FUNCTIONS -------------------------

func_args: ID L_RND_BR (var_type (COMMA var_type)*)? R_RND_BR COLON datatype ;
func_decl: DECL func_args;
func_def: DEF func_args L_CRL_BR
				statement*
		  R_CRL_BR ;
func_inv: ID L_RND_BR (expr (COMMA expr)*)? R_RND_BR ;


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
    |	L_RND_BR expr R_RND_BR
    ;
    
    
// ------------------------- ARRAYS -------------------------

arr_r_value : arr_l_value | (func_inv (L_SQR_BR expr R_SQR_BR)+) ;
arr_l_value : ID (L_SQR_BR expr R_SQR_BR)+ ;


// ------------------------- DATA TYPES -------------------------
BOOL    : 'true' 
        | 'false' ;
INT     : [0-9]('_'*[0-9])* ;
FLOAT   : [0-9]*'.'?[0-9]+ 
        | [0-9]+'.'?[0-9]*[eE][0-9]+;
STRING  : '"'(~[\\"]| '\\'[btnfr"'\\] | ' ')*'"' ;

        
// ------------------------- SEPARATORS ------------------------- 
//L->left, R->right
//RND->round, SQR->square, CRL->curly
//BR->bracket

L_RND_BR   : '(' ;
R_RND_BR   : ')' ;

L_SQR_BR   : '[' ;
R_SQR_BR   : ']' ;

L_CRL_BR   : '{' ;
R_CRL_BR   : '}' ;

SEMICOLON  : ';' ;
COLON      : ':' ;
COMMA      : ',' ;

// ------------------------- OPERATORS -------------------------

//Assignment
ASSIGN     :  '=';

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
			
unary_ope   : NOT 
            | MINUS;


// ------------------------- ARRAYS -------------------------

//Identifier
ID     : [a-zA-Z_][a-zA-Z0-9_]* ;

//whitespace insensitive
WS : [ \r\t\n]+ -> skip ;

//comment line
COMMENT : '#' ~( '\r' | '\n' )* -> skip ; 