// grammar

grammar CA;
 
// parser rules
  
casentence : cmd * ;

cmd : (template | usetemplate | import_ | link | unlink | set)? ';' ;

template : TEMPLATETOKEN templatename ;

templatename : STRING ;

usetemplate : USETOKEN (inputidentifier '=')? STRING ( '(' key '=' value (',' key '=' value)* ')' )? ;

key : STRING ;

value : STRING ;

import_ : IMPORTTOKEN alignmentprovider? filename ( '(' arg (',' arg)* ')' )? ;

filename :  STRING ;

alignmentprovider : STRING ;

arg : STRING ;

partitionpattern : STRING ;

link : LINKTOKEN LINKTYPE partitionpattern? ;
 
unlink : UNLINKTOKEN LINKTYPE partitionpattern? ;

set : SETTOKEN inputidentifier '=' STRING ;

inputidentifier : idPattern | elemntName? inputname idPattern?;

elemntName : STRING '@';

idPattern : '[' STRING ']' ;

//attributeconditions : '[' attributecondition (',' attributecondition )* ']' ;

//attributecondition : inputname '=' value attributeconditions? ;

inputname : STRING ;

// Lexer Rules

TEMPLATETOKEN : 'template';
IMPORTTOKEN : 'import';
PARTITIONTOKEN : 'partition';
LINKTOKEN : 'link';
UNLINKTOKEN : 'unlink';
SETTOKEN : 'set';
USETOKEN : 'use';
LINKTYPE : 'clock' | 'tree' | 'sitemodel';

STRING :
    [a-zA-Z0-9|#*%/.\-+_&:$]+  // these chars don't need quotes
    | '"' .*? '"'
    | '\'' .*? '\''
    ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;

COMMENT
    :   '/*' ().*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
;