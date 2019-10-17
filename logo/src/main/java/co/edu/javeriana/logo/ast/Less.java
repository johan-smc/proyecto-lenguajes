package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Less implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public Less(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	@Override
	public Object execute(Turtle turtle) {
		/// TODO verify if is a float
		return (float)operand1.execute(turtle) < (float)operand2.execute(turtle);
	}

}
