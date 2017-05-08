// grammar

grammar CA;
 
// parser rules
  
casentence : ((template | subtemplate | import_ | /* partition |*/ link | unlink | set) SEMI)*;

// template
template : TEMPLATETOKEN templatename;

templatename : STRING;


// subtemplate
subtemplate : SUBTOKEN (idpattern EQ)? STRING ( OPENP key EQ value (COMMA key EQ value)* CLOSEP )?;

idpattern : STRING;

key : STRING;

value : STRING;

import_ : IMPORTTOKEN alignmentprovider? filename ( OPENP arg (COMMA arg)* CLOSEP )?;
//
filename :  STRING;
//
alignmentprovider : STRING;
//
arg : STRING;
//
//partition : PARTITIONTOKEN partitionpattern;
//
partitionpattern : STRING;

link : LINKTOKEN LINKTYPE partitionpattern?;
 
unlink : UNLINKTOKEN LINKTYPE partitionpattern?;

set : SETTOKEN STRING EQ STRING;


// Lexer Rules

// lexer grammar CALexer;

SEMI: ';' ;
COMMA: ',' ;
OPENP: '(' ;
CLOSEP: ')' ;
EQ: '=' ;

TEMPLATETOKEN : 'template';
IMPORTTOKEN : 'import';
PARTITIONTOKEN : 'partition';
LINKTOKEN : 'link';
UNLINKTOKEN : 'unlink';
SETTOKEN : 'set';
SUBTOKEN : 'sub';
LINKTYPE : 'clock' | 'tree' | 'site';

STRING :
    [a-zA-Z0-9|#*%/.\-+_&]+  // these chars don't need quotes
    | '"' .*? '"'
    | '\'' .*? '\''
    ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;