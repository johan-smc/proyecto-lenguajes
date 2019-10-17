package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class MoveForw implements ASTNode {

	private ASTNode amount;
	
	public MoveForw(ASTNode amount) {
		super();
		this.amount = amount;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		turtle.forward((float)amount.execute(turtle, symbolTable));
		return null;
	}

}
