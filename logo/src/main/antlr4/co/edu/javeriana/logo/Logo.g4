grammar Logo;


@parser::members {

private Turtle turtle;

public LogoParser(TokenStream input, Turtle turtle) {
    this(input);
    this.turtle = turtle;
}

}

start
:
	'hello' 'world' 
;

WS
:
	[ \t\r\n]+ -> skip
;