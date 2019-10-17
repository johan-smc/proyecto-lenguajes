package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Multiplication implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public Multiplication(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	@Override
	public Object execute(Turtle turtle) {
		return (float)operand1.execute(turtle) * (float)operand2.execute(turtle);
	}

}
