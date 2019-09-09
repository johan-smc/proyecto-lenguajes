grammar Logo;

@parser::header {
	import java.util.Map;
	import co.edo.javeriana.logo.ast.*;
	import java.util.List;
	import java.util.ArrayList;
}

@parser::members {

private Turtle turtle;

public LogoParser(TokenStream input, Turtle turtle) {
    this(input);
    this.turtle = turtle;
}

}


program:
	{
		List<ASTNode> body = new ArrayList<>();
	}
	(sentence {body.add($sentence.node);})* 
	{
		for( ASTNode n : body ){
			
			n.execute(turtle);
		}
	}
;

sentence returns [ASTNode node]:
 	move {$node = $move.node;} 
 	| 
 	rotate {$node = $rotate.node;}
 	| 
 	set_color {$node = $set_color.node;}
	;

move returns [ASTNode node]:
	move_forw {$node = $move_forw.node;} 
	|
	move_back {$node = $move_back.node;}
	;

move_forw returns [ASTNode node]:
	MOVE_FORW expression
	{$node = new MoveForw($expression.node);}
	;
	
move_back returns [ASTNode node]:
	MOVE_BACK expression
	{$node = new MoveBack($expression.node);}
	;

rotate returns [ASTNode node]:
	rot_l {$node = $rot_l.node;} 
	|
	rot_r {$node = $rot_r.node;}
	;

rot_l returns [ASTNode node]:
	ROT_L expression
	{$node = new RotL($expression.node);}
	;
	
rot_r returns [ASTNode node]:
	ROT_R expression
	{$node = new RotR($expression.node);}
	;

set_color returns [ASTNode node]:
	SET_COLOR red = expression COMMA  green = expression COMMA blue = expression COMMA alpha = expression
	{$node = new SetColor($red.node, $green.node, $blue.node, $alpha.node);}
	;

expression returns [ASTNode node]:
	terms {$node = $terms.node;}
	; 

terms returns [ASTNode node]: 
	NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
	;


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
