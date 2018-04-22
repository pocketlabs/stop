grammar Stop;

// Rules

file: ( include | model )+;

include
    : 'include' '"' FILENAME '"'
    ;

model
   : START? MODEL_TYPE (throw_type)? block
   | STOP? MODEL_TYPE (throw_type)? block
   | ASYNC MODEL_TYPE (throw_type)? block_with_timeout
   | ASYNC MODEL_TYPE RETURN_OP return_type (throw_type)? return_block_with_timeout
   | MODEL_TYPE RETURN_OP return_type (throw_type)? return_block
   ;

block
   : '{' ( statement ';'? )* '}'
   ;

statement
   : enumeration | field | transition
   ;

block_with_timeout
   : '{' ( block_with_timeout_statement ';'? )* '}'
   ;

block_with_timeout_statement
   : enumeration | field | transition | timeout
   ;

return_block
   : '{' ( return_block_statement ';'? )* '}'
   ;

return_block_statement
   : enumeration | field
   ;

return_block_with_timeout
   : '{' ( return_block_with_timeout_statement ';'? )* '}'
   ;

return_block_with_timeout_statement
   : enumeration | field | timeout
   ;

scalar_type: 'double' | 'float' | 'int32' | 'int64' | 'uint32' | 'uint64' | 'sint32' | 'sint64'
	| 'fixed32' | 'fixed64' | 'sfixed32' | 'sfixed64' | 'bool' | 'string' | 'bytes'
	;

model_type
    : MODEL_TYPE
    ;

enum_type
    : MODEL_TYPE
    ;

enum_value
    : MODEL_TYPE
    ;

enumeration
    : 'enum' enum_type '{' ( enum_value )+ '}'
    ;

field
	: type ID (async_source)?
	| collection ID (async_source)?
	;

async_source
    : RETURN_OP MODEL_TYPE
    ;

type
    : scalar_type | model_type
    ;

collection
    : '[' type ']'
    ;

return_type
    : collection | type;

throw_type
    : 'throws' throw_parameter (',' throw_parameter)*
    ;

throw_parameter
    : MODEL_TYPE
    ;

timeout: 'timeout' NUMBER transition;

transition: TRANSITION_OP MODEL_TYPE;


// Tokens
ASYNC : 'async';

START : 'start';

STOP : 'stop';

TRANSITION_OP : '->';

RETURN_OP : '<-';

NUMBER
   : '-'? ( '.' DIGIT+ | DIGIT+ ( '.' DIGIT* )? )
   ;

MODEL_TYPE
   : UPPERCASE_LETTER  ( LETTER | DIGIT )*
   ;

ID
   : LOWERCASE_LETTER ( LETTER | DIGIT )*
   ;



FILENAME
    : (LETTER | DIGIT | '.' | '\\' | '/' | '-' | '_')+
    ;

fragment UPPERCASE_LETTER
   : [A-Z]
   ;

fragment LOWERCASE_LETTER
   : [a-z]
   ;

fragment LETTER
   : [a-zA-Z_]
   ;

fragment DIGIT
   : [0-9]
   ;

COMMENT
   : '/*' .*? '*/' -> skip
   ;

LINE_COMMENT
   : '//' .*? '\r'? '\n' -> skip
   ;

WS
   : [ \t\n\r]+ -> skip
   ;
