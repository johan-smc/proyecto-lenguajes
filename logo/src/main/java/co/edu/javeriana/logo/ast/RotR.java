package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class RotR implements ASTNode {

	private ASTNode degrees;
	
	public RotR(ASTNode degrees) {
		super();
		this.degrees = degrees;
	}
	
	@Override
	public Object execute(Turtle turtle) {
		turtle.right((float)degrees.execute(turtle));
		return null;
	}

}
