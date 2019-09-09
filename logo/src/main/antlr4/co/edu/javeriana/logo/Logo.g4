grammar Logo;


@parser::members {

private Turtle turtle;

public LogoParser(TokenStream input, Turtle turtle) {
    this(input);
    this.turtle = turtle;
}

}


program:
	sentence* 
;

sentence: move | rotate | set_color;

move: move_forw | move_back;

move_forw: MOVE_FORW NUMBER;
move_back: MOVE_BACK NUMBER;

rotate: rot_l | rot_r;

rot_l: ROT_L NUMBER;
rot_r: ROT_R NUMBER;

set_color: SET_COLOR NUMBER COMMA  NUMBER COMMA NUMBER COMMA NUMBER;


LET: 'let';
PRINTLN: 'println';
READ: 'read';

MOVE_FORW: 'move_forw';
MOVE_BACK: 'move_back';
ROT_L: 'rot_l';
ROT_R: 'rot_r';
SET_COLOR: 'set_color';


PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

AND: '&&';
OR: '||';
NOT: '!';

GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
NEQ: '!=';

ASSIGN: '=';


COMMA: ',';

NUMBER
:
	[+-]?([0-9]*[.])?[0-9]+ 
;


WS
:
	[ \t\r\n]+ -> skip
;
