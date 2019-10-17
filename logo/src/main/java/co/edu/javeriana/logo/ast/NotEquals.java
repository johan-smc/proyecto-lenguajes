package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class NotEquals implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public NotEquals(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		return !operand1.execute(turtle, symbolTable).equals(operand2.execute(turtle, symbolTable));
	}

}
