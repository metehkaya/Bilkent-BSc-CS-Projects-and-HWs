%{
	#include <stdlib.h>
	// #include "y.tab.h"
	void yyerror(char *);
%}
ALPHANUMERIC ({LOWERCASE}|{UPPERCASE}|{DIGIT})
DIGIT [0-9]
LOWERCASE [a-z]
UPPERCASE [A-Z]
VARIABLE {LOWERCASE}{ALPHANUMERIC}*
COMMENT [??]+.*
LIBRARY Library
LIBRARYNAME {UPPERCASE}+
ARRAYNAME \@{VARIABLE}
VMETHODNAME \${VARIABLE}
RMETHODNAME \£{VARIABLE}
PREDICATE Predicate
PREDICATENAME \€{VARIABLE}
INTEGERVALUE [-+]?{DIGIT}+
DOUBLEVALUE [-+]?{DIGIT}*(\.{DIGIT}+)
STRINGVALUE \"[^\"]*\"
BOOLEANVALUE True|False
VALUENOT {NOT}({VARIABLE}|{BOOLEANVALUE})
PACKAGE Package
INTEGERTYPE Integer
DOUBLETYPE Double
STRINGTYPE String
BOOLEANTYPE Boolean
ARRAYTYPE Array
VOID Void
INPUT Input
OUTPUT Output
IF If
ELSE Else
FOR For
WHILE While
FINAL Final
MAIN Main
RETURN Return
INIT Initialize
INITIALIZE {INIT}\(\)
LP \(
RP \)
LB \{
RB \}
LS \[
RS \]
COMMA \,
SEMICOLON \;
ASSIGNMENT \=
PLUSASSIGNMENT \+\=
MINUSASSIGNMENT \-\=
MULTASSIGNMENT \*\=
DIVASSIGNMENT \/\=
MODASSIGNMENT \%\=
ISEQUAL \=\=
NOTEQUAL \!\=
LESS \<
LESSOREQUAL \<\=
GREATER \>
GREATEROREQUAL \>\=
OR \|
AND \&
NOT \~
IMPLY \-\>
IFF \<\-\>
%option yylineno
%%
{VARIABLE} return (VARIABLE);
{INTEGERVALUE} return (INTEGERVALUE);
{DOUBLEVALUE} return (DOUBLEVALUE);
{STRINGVALUE} return (STRINGVALUE);
{BOOLEANVALUE} return (BOOLEANVALUE);
{VALUENOT} return (VALUENOT);
{COMMENT} return (COMMENT);
{LIBRARY} return (LIBRARY);
{LIBRARYNAME} return (LIBRARYNAME);
{ARRAYNAME} return (ARRAYNAME);
{VMETHODNAME} return (VMETHODNAME);
{RMETHODNAME} return (RMETHODNAME);
{PREDICATE} return (PREDICATE);
{PREDICATENAME} return (PREDICATENAME);
{PACKAGE} return (PACKAGE);
{INTEGERTYPE} return (INTEGERTYPE);
{DOUBLETYPE} return (DOUBLETYPE);
{STRINGTYPE} return (STRINGTYPE);
{BOOLEANTYPE} return (BOOLEANTYPE);
{ARRAYTYPE} return (ARRAYTYPE);
{VOID} return (VOID);
{INPUT} return (INPUT);
{OUTPUT} return (OUTPUT);
{IF} return (IF);
{ELSE} return (ELSE);
{FOR} return (FOR);
{WHILE} return (WHILE);
{FINAL} return (FINAL);
{MAIN} return (MAIN);
{RETURN} return (RETURN);
{INITIALIZE} return (INITIALIZE);
{LP} return (LP);
{RP} return (RP);
{LB} return (LB);
{RB} return (RB);
{LS} return (LS);
{RS} return (RS);
{COMMA} return (COMMA);
{SEMICOLON} return (SEMICOLON);
{ASSIGNMENT} return (ASSIGNMENT);
{PLUSASSIGNMENT} return (PLUSASSIGNMENT);
{MINUSASSIGNMENT} return (MINUSASSIGNMENT);
{MULTASSIGNMENT} return (MULTASSIGNMENT);
{DIVASSIGNMENT} return (DIVASSIGNMENT);
{MODASSIGNMENT} return (MODASSIGNMENT);
{ISEQUAL} return (ISEQUAL);
{NOTEQUAL} return (NOTEQUAL);
{LESS} return (LESS);
{LESSOREQUAL} return (LESSOREQUAL);
{GREATER} return (GREATER);
{GREATEROREQUAL} return (GREATEROREQUAL);
{OR} return (OR);
{AND} return (AND);
{NOT} return (NOT);
{IMPLY} return (IMPLY);
{IFF} return (IFF);
[ \t\n\r] ;
%%
int yywrap() { return 1; }