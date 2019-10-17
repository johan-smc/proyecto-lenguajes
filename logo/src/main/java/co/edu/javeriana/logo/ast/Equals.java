package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Equals implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public Equals(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	@Override
	public Object execute(Turtle turtle) {
		return operand1.execute(turtle).equals(operand2.execute(turtle));
	}

}
