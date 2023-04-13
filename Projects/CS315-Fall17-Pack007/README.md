# Pack007

### What We Did So Far

While we were implementing the project, our primary aim was to provide the users easily
understandable syntax for propositional calculus. However, since we have not familiar with the
Lex and Yacc tool before, we encounter some undesirable issues. During the implementation of
the first part of the project we mastered Lex tool.

Our biggest challenge was to keep our grammar consistent and unambiguous. During the
implementation of project since there are numerous operations, primitive types and objects, our
lex file give several warnings even if the file compile smoothly. The main reason behind these facts
was the complexity of our grammar. Since we declared many rules for our language, we
experienced some problems because of the order of the statements. In order to handle it, we
made small changes on our grammar.

After making changes on our grammar, we spent the some time on solving reduce/reduce and
shift/reduce conflicts. In order to solve this issue, we emphasised on performing more tests and
we mostly traced the grammar step by step.

In addition to this, to give information to user about acceptance of the input, the parser prints out
a message. If the input is valid, the parser shows “Input program accepted.”. Otherwise, the
parser gives an error message like "*-syntax error”. And, * indicates the line number of source
code that contains the error.

### What is Pack007

Pack007 is a language providing a very easy syntax for propositional calculus. The main aim is to
have an easy to use syntax with a familiarity of the current languages to provide a smoother
adaptation phase for programmers. Pack007 includes all of the basic functionalities you look in a
programming language and it also has some specific parts to make programmers’ life for
propositional calculus.

### Team

Team Member: [Metehan Kaya](https://github.com/metehkaya)

Team Member: [Mehmet Taha Çetin](https://github.com/mtcetin)

Team Member: [Seda Gülkesen](https://github.com/sedagulkesen)
