package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class MoveBack implements ASTNode {

	private ASTNode amount;
	
	public MoveBack(ASTNode amount) {
		super();
		this.amount = amount;
	}

	@Override
	public Object execute(Turtle turtle) {
		turtle.backwards((float)amount.execute(turtle));
		return null;
	}

}
