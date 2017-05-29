// grammar

grammar CA;
 
// parser rules
  
casentence : cmd * ;

cmd : (template | usetemplate | import_ | link | unlink | set | rename | rm | taxonset)? ';' ;

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

inputidentifier : idPattern | elemntName? inputname idPattern? ;

elemntName : STRING '@';

idPattern : '[' STRING ']' ;

inputname : STRING ;

rename : RENAMETOKEN LINKTYPE oldName? '=' newName ;

// HACK: since 'tree' and 'clock' are popular names to switch to
// we allow STRINGs and LINKTYPE
oldName : STRING | LINKTYPE ;
newName : STRING | LINKTYPE ;

rm : RMTOKEN inputidentifier ;

taxonset : TAXONSETTOKEN STRING '=' STRING + ;

// Lexer Rules
TEMPLATETOKEN : 'template' ;
IMPORTTOKEN : 'import' ;
LINKTOKEN : 'link' ;
UNLINKTOKEN : 'unlink' ;
SETTOKEN : 'set' ;
USETOKEN : 'use' ;
RENAMETOKEN : 'rename' ;
RMTOKEN : 'rm' ;
TAXONSETTOKEN: 'taxonset' ;

LINKTYPE : 'clock' | 'tree' | 'sitemodel' ;

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