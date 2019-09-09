package co.edo.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class MoveForw implements ASTNode {

	private ASTNode amount;
	
	public MoveForw(ASTNode amount) {
		super();
		this.amount = amount;
	}
	
	@Override
	public Object execute(Turtle turtle) {
		turtle.forward((float)amount.execute(turtle));
		return null;
	}

}
