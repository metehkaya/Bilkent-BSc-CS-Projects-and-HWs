cs315f17_group07: y.tab.c lex.yy.c
	gcc -o parser y.tab.c
y.tab.c: cs315f17_group07.yacc lex.yy.c
	yacc cs315f17_group07.yacc
lex.yy.c: cs315f17_group07.lex
	lex cs315f17_group07.lex
clean:
	rm -f lex.yy.c y.tab.c
