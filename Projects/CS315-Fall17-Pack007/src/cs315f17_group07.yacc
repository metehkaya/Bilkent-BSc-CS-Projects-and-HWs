%{
#include <stdio.h>
#include <stdlib.h>
#define YYDEBUG 1
int yylex(void);
void yyerror(char* s);
extern int yylineno;
%}

%token VARIABLE FINAL VALUENOT
%token MAIN RETURN COMMENT INITIALIZE
%token INTEGERVALUE DOUBLEVALUE STRINGVALUE BOOLEANVALUE
%token LIBRARY PREDICATE PACKAGE
%token LIBRARYNAME VMETHODNAME RMETHODNAME PREDICATENAME ARRAYNAME
%token INTEGERTYPE DOUBLETYPE STRINGTYPE BOOLEANTYPE ARRAYTYPE VOID
%token INPUT OUTPUT IF ELSE FOR WHILE
%token LP RP LB RB LS RS COMMA SEMICOLON
%token ISEQUAL NOTEQUAL LESS LESSOREQUAL GREATER GREATEROREQUAL
%token ASSIGNMENT PLUSASSIGNMENT MINUSASSIGNMENT MULTASSIGNMENT DIVASSIGNMENT MODASSIGNMENT
%token OR AND NOT IMPLY IFF
%%
start: library_list global_variable_list main_definition method_list { printf("Input program accepted.\n"); }

library_list:	library_definition
				| library_definition library_list ;

library_definition: LIBRARY LIBRARYNAME ;

global_variable_list:	
						| final_variable_declaration SEMICOLON global_variable_list
						| non_final_variable_declaration SEMICOLON global_variable_list ;

final_variable_declaration:	FINAL variable_type assign ;

non_final_variable_declaration: variable_type assign
								| complex_variable_declaration ;

complex_variable_declaration:	variable_type variable_list ;

variable_list:	VARIABLE COMMA variable_list
				| VARIABLE ;

method_list:	
				| void_method_definition method_list
				| returnable_method_definition method_list ;

void_method_definition:	VOID VMETHODNAME LP parameter_list RP LB void_method_body RB ;

returnable_method_definition:	variable_type RMETHODNAME LP parameter_list RP LB returnable_method_body RB ;

parameter_list:	
				| parameter_definition
				| parameter_definition COMMA parameter_list ;

parameter_definition:	variable_type VARIABLE ;

primitive_type:	INTEGERTYPE
				| DOUBLETYPE
				| STRINGTYPE
				| BOOLEANTYPE ;

variable_type:	primitive_type
				| PACKAGE ;

main_definition: MAIN LP RP LB void_method_body RB ;

void_method_body: 	
					| void_method_stmt void_method_body ;

void_method_stmt:	void_loop
					| void_conditional
					| common_method_stmt ;

returnable_method_body:	
						| returnable_method_stmt returnable_method_body ;

returnable_method_stmt:	returnable_loop
						| returnable_conditional
						| return_definition SEMICOLON
						| common_method_stmt ;

common_method_stmt:	COMMENT
					| assign SEMICOLON
					| array_assign SEMICOLON
					| void_method_call SEMICOLON
					| input_definition SEMICOLON
					| output_definition SEMICOLON
					| final_variable_declaration SEMICOLON
					| non_final_variable_declaration SEMICOLON
					| ARRAYTYPE ARRAYNAME ASSIGNMENT INITIALIZE SEMICOLON
					| PREDICATE VARIABLE ASSIGNMENT PREDICATENAME LP call_list RP SEMICOLON ;

array_assign:	array_value assignment_operation explicit_value
				| array_value assignment_operation VARIABLE ;

array_value:	ARRAYNAME LS explicit_value RS
				| ARRAYNAME LS VARIABLE RS ;

void_method_call:	VMETHODNAME LP RP
					| VMETHODNAME LP call_list RP ;

returnable_method_call:	RMETHODNAME LP RP
						| RMETHODNAME LP call_list RP ;

explicit_value:	INTEGERVALUE
				| DOUBLEVALUE
				| STRINGVALUE
				| BOOLEANVALUE ;

call_list:	VARIABLE COMMA call_list
			| explicit_value COMMA call_list
			| array_value COMMA call_list
			| VARIABLE
			| explicit_value
			| array_value ;

input_definition:	INPUT variable_list ;

output_definition:	OUTPUT call_list
					| OUTPUT returnable_method_call ;

return_definition:	RETURN VARIABLE
					| RETURN explicit_value ;

assign:	VARIABLE assignment_operation VARIABLE
		| VARIABLE assignment_operation explicit_value
		| VARIABLE assignment_operation returnable_method_call ;

assignment_operation: 	ASSIGNMENT
						| PLUSASSIGNMENT
						| MINUSASSIGNMENT
						| MULTASSIGNMENT
						| DIVASSIGNMENT
						| MODASSIGNMENT ;

connective_list:	connective_value connective_operation connective_list
					| connective_value ;

connective_operation:	AND
						| OR
						| IMPLY
						| IFF ;

connective_value:	VARIABLE
					| BOOLEANVALUE
					| VALUENOT ;

void_conditional:	void_if_conditional
					| void_if_else_conditional ;

void_if_conditional:	IF LP condition RP LB void_method_body RB ;

void_if_else_conditional:	IF LP condition RP LB void_method_body RB ELSE LB void_method_body RB ;

returnable_conditional:	returnable_if_conditional
						| returnable_if_else_conditional ;

returnable_if_conditional:	IF LP condition RP LB returnable_method_body RB ;

returnable_if_else_conditional:	IF LP condition RP LB returnable_method_body RB ELSE LB returnable_method_body RB ;

condition:	connective_list
			| VARIABLE compare_operation VARIABLE
			| VARIABLE compare_operation explicit_value
			| explicit_value compare_operation VARIABLE
			| explicit_value compare_operation explicit_value ;

compare_operation:	ISEQUAL
					| NOTEQUAL
					| LESS
					| LESSOREQUAL
					| GREATER
					| GREATEROREQUAL ;

void_loop: 	void_for
			| void_while ;

void_for: 	FOR LP assign SEMICOLON condition SEMICOLON assign RP LB void_method_body RB ;

void_while:	WHILE LP condition RP LB void_method_body RB ;

returnable_loop:	returnable_for
					| returnable_while ;

returnable_for:	FOR LP assign SEMICOLON condition SEMICOLON assign RP LB returnable_method_body RB ;

returnable_while:	WHILE LP condition RP LB returnable_method_body RB ;
%%
#include "lex.yy.c"
main() { yyparse(); }
void yyerror(char *s) { fprintf(stdout, "%d-%s\n", yylineno,s); }