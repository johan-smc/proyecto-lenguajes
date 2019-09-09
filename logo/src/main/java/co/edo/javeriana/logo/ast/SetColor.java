package co.edo.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class SetColor implements ASTNode {

	private ASTNode red;
	private ASTNode green;
	private ASTNode blue;
	private ASTNode alpha;
	
	
	
	public SetColor(ASTNode red, ASTNode green, ASTNode blue, ASTNode alpha) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}



	@Override
	public Object execute(Turtle turtle) {
		turtle.color(
				(float)red.execute(turtle),
				(float)green.execute(turtle),
				(float)blue.execute(turtle),
				(float)alpha.execute(turtle)
				);
		return null;
	}

}
