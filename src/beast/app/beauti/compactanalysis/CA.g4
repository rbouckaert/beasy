// grammar

grammar CA;
 
// parser rules
  
casentence : cmd + ;

cmd : (template | import_ | use | set | link | unlink | rename | add | rm | taxonset )? ';' ;

template : TEMPLATETOKEN templatename ;

templatename : STRING ;

use : USETOKEN (inputidentifier '=')? STRING ( '(' key '=' value (',' key '=' value)* ')' )? ;

key : STRING | TAXONSETTOKEN;

value : STRING ;

import_ : IMPORTTOKEN alignmentprovider? filename ( '(' arg (',' arg)* ')' )? ;

add : ADDTOKEN STRING ( '(' argOrUse (',' argOrUse)* ')' )? ;

argOrUse : value | subtemplate ;

subtemplate : STRING '(' key '=' value (',' key '=' value)* ')';

filename :  STRING ;

alignmentprovider : STRING ;

arg : STRING ;

link : LINKTOKEN linktype partitionPattern? ;
linktype : LINKTYPE ;
 
unlink : UNLINKTOKEN linktype partitionPattern? ;

set : SETTOKEN inputidentifier '=' STRING ;

inputidentifier : idPattern | elemntName? inputname idPattern? | elemntName? inputname? partitionPattern;

elemntName : STRING '@';

idPattern : '[' STRING ']' ;

partitionPattern : '{' (STRING | LINKTYPE) (',' (STRING | LINKTYPE) ) * '}' ;

inputname : STRING ;

//rename : RENAMETOKEN LINKTYPE partitionPattern? newName ;
rename : RENAMETOKEN linktype oldName? '=' newName ;

// HACK: since 'tree' and 'clock' are popular names to switch to
// we allow STRINGs and LINKTYPE
oldName : STRING | LINKTYPE ;
newName : STRING | LINKTYPE ;

rm : RMTOKEN (inputidentifier | partitionPattern);

taxonset : TAXONSETTOKEN STRING '=' STRING + ;

// Lexer Rules
TEMPLATETOKEN : 'template' ;
IMPORTTOKEN : 'import' ;
LINKTOKEN : 'link' ;
UNLINKTOKEN : 'unlink' ;
SETTOKEN : 'set' ;
USETOKEN : 'use' ;
ADDTOKEN : 'add' ;
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