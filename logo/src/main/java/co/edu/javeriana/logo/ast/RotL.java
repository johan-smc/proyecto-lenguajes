package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class RotL implements ASTNode {

	private ASTNode degrees;
	
	public RotL(ASTNode degrees) {
		super();
		this.degrees = degrees;
	}
	
	@Override
	public Object execute(Turtle turtle) {
		turtle.left((float)degrees.execute(turtle));
		return null;
	}

}
