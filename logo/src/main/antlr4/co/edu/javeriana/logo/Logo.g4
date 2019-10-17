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
 	|
 	println {$node = $println.node;}
 	|
 	conditional {$node = $conditional.node;}
 	|
 	var_declaration {$node = $var_declaration.node;}
 	|
 	var_assign {$node = $var_assign.node;}
 	|
 	loop {$node = $loop.node;}
	;


var_declaration returns [ASTNode node]:
	LET ID 
	{$node = new ;}
	;

var_assign returns [ASTNode node]:
	ID ASSIGN expression
	{$node = new;}
	;

loop returns [ASTNode node]:
	WHILE condition
	{}
	DO
	(
		s1 = sentence {}
	)*
	{}
	END_WHILE
	;

conditional returns [ASTNode node]:
	IF condition
	{}
	THEN
	(
		s1 = sentence {}
		
	)*
	ELSE {}
	(
		s2 = sentence {}
	)*
	{}
	END_IF
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

println returns [ASTNode node]:
	PRINTLN expression
	{$node = new Println($expression.node);}
	;


condition returns [ASTNode node]:
	c1 = condition_comparation {$node = $c1.node;}
	(
		AND c2 = condition_comparation {$node = new And($node, $c2.node);}
		|
		OR c2 = condition_comparation {$node = new Or($node, $c2.node);}
	)*
	|
	NOT condition 
	{$node = new Not($condition.node);}
	;

condition_comparation returns [ASTNode node]:
	e1 = expression {$node = $expression.node;}
	(
		GT e2 = expression {$node = new Greater($node, $e2.node);}
		|
		LT e2 = expression {$node = new Less($node, $e2.node);}
		|
		GEQ e2 = expression {$node = new GreaterOrEquals($node, $e2.node);}
		|
		LEQ e2 = expression {$node = new LessOrEquals($node, $e2.node);}
		|
		EQ e2 = expression {$node = new Equals($node, $e2.node);}
		|
		NEQ e2 = expression {$node = new NotEquals($node, $e2.node);}
	)
	;


expression returns [ASTNode node]:
	t1 = terms {$node = $terms.node;}
	(
		PLUS t2 = factor {$node = new Addition($node, $t2.node);}
		|
		MINUS t2 = factor {$node = new Subtraction($node, $t2.node);}
	)*
	|
	MINUS terms {$node = new Subtraction(0.0, $terms.node);}
	; 


factor returns [ASTNode node]:
	t1 = terms {$node = $t1.node;}
	(
		MULT t2 = terms {$node = new Multiplication($node, $t2.node);}
		|
		DIV t2 = terms {$node = new Division($node, $t2.node);}
	)*
	;
	
terms returns [ASTNode node]: 
	NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
	|
	BOOL {$node = new Constant(Boolean.parseBoolean($BOOL.text));}
	|
	STRING {$node = new Constant(String.parseString($STRING.text));}
	|
	ID {$node = new VarReference($ID.text);}
	;


ID: [A-Za-z][A-Za-z0-9]*;
/// conditionals
IF: 'if';
THEN: 'then';
ELSE: 'else';
END_IF: 'end_if';

/// loop
WHILE: 'while';
DO: 'do';
END_WHILE: 'end_while';

// functions
DEF: 'def';
END_DEF: 'end_def';



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


PAR_OPEN: '(';
PAR_CLOSE: ')';

COMMA: ',';

NUMBER
:
	[+-]?([0-9]*[.])?[0-9]+ 
;

BOOL: 'true' | 'false';

STRING: '"' ('\\"' | .*?) '"';


WS
:
	[ \t\r\n]+ -> skip
;
