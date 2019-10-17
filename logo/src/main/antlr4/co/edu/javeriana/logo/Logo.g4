grammar Logo;

@parser::header {
	import java.util.Map;
	import co.edu.javeriana.logo.ast.*;
	import utils.*;
	import java.util.List;
	import java.util.ArrayList;
}

@parser::members {

private Turtle turtle;
private SymbolTable symbolTable = new SymbolTable();
private ListOfFunctions listOfFunctions = new ListOfFunctions();

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
			try{
				n.execute(turtle, symbolTable, listOfFunctions);
			}catch (Exception e )
			{
				System.err.println(e.getMessage());
			}
		
		}
	}
;

sentence returns [ASTNode node]:
	var_assign {$node = $var_assign.node;}
	|
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
 	loop {$node = $loop.node;}
 	|
 	read {$node = $read.node;}
 	|
 	function_declaration {$node = $function_declaration.node;}
 	|
 	function_execution {$node = $function_execution.node;}
	;


function_declaration returns [ASTNode node]:
	DEF name=ID
	{
		List<ASTNode> block = new ArrayList<ASTNode>();
		List<String> params = new ArrayList<String>();
	}
	PAR_OPEN 
	( 
		(
			p = ID
			{
				params.add($p.text);
			}
		)
		(
			COMMA
			p1 = ID
			{
				params.add($p1.text);
			}
		)*
		
	)?
	PAR_CLOSE COLON
	(
		s1 = sentence {block.add($s1.node);}
	)*
	END_DEF
	{
		$node = new FunctionSignature($name.text, params, block);
	}
	;

function_execution returns [ASTNode node]:
	name=ID
	{
		List<ASTNode> params = new ArrayList<ASTNode>();
	}
	PAR_OPEN 
	( 
		(
			p = expression
			{
				params.add($p.node);
			}
		)
		(
			COMMA
			p1 = expression
			{
				params.add($p1.node);
			}
		)*
	)?
	PAR_CLOSE
	{
		$node = new FunctionExecution($name.text, params);
	}
	;

var_declaration returns [ASTNode node]:
	LET ID 
	{$node = new VarDeclaration($ID.text);}
	|
	LET ID ASSIGN expression
	{$node = new VarDeclaration($ID.text, $expression.node);}
	;

var_assign returns [ASTNode node]:
	ID ASSIGN expression
	{$node = new VarAssign($ID.text, $expression.node );}
	;

loop returns [ASTNode node]:
	WHILE condition
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
	}
	DO
	(
		s1 = sentence {body.add($s1.node);}
	)*
	{
		$node = new While($condition.node, body);
	}
	END_WHILE
	;

conditional returns [ASTNode node]:
	IF condition
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		List<ASTNode> elseBody = new ArrayList<ASTNode>();
	}
	THEN
	(
		s1 = sentence {body.add($s1.node);}
		
	)*
	ELSE? 
	(
		s2 = sentence {elseBody.add($s2.node);}
	)*
	{
		$node = new If($condition.node, body, elseBody);
	}
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

read returns [ASTNode node]:
	READ ID
	{$node = new Read($ID.text);}
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
	)?
	;


expression returns [ASTNode node]:
	t1 = terms {$node = $terms.node;}
	(
		PLUS t2 = factor {$node = new Addition($node, $t2.node);}
		|
		MINUS t2 = factor {$node = new Subtraction($node, $t2.node);}
	)*
	|
	MINUS terms {$node = new Subtraction(new Constant(0.0f), $terms.node);}
	|
	condition {$node=$condition.node}
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
	PAR_OPEN expression PAR_CLOSE
	{
		$node = $expression.node;	
	}
	|
	PAR_OPEN condition PAR_CLOSE
	{
		$node = $condition.node;
	}
	|
	NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
	|
	BOOL {$node = new Constant(Boolean.parseBoolean($BOOL.text));}
	|
	STRING
	{$node = new Constant(
		org.antlr.v4.misc.CharSupport.getStringFromGrammarStringLiteral($STRING.text)
	);}
	|
	ID {$node = new VarReference($ID.text);}
	;



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

COLON: ':';

COMMA: ',';

NUMBER
:
	([0-9]*[.])?[0-9]+ 
;

BOOL: 'true' | 'false';

STRING: '"' ('\\"' | .)*? '"';

ID: [A-Za-z][A-Za-z0-9_]*;



WS
:
	[ \t\r\n]+ -> skip
;
