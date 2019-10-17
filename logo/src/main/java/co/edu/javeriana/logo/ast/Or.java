package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Or implements ASTNode {

	private ASTNode condition1;
	private ASTNode condition2;
	
	public Or(ASTNode condition1, ASTNode condition2) {
		super();
		this.condition1 = condition1;
		this.condition2 = condition2;
	}
	
	@Override
	public Object execute(Turtle turtle) {
		return (boolean)condition1.execute(turtle) || (boolean)condition2.execute(turtle);
	}


}
