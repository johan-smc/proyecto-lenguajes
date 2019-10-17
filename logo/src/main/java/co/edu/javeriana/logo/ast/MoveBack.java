package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class MoveBack implements ASTNode {

	private ASTNode amount;
	
	public MoveBack(ASTNode amount) {
		super();
		this.amount = amount;
	}

	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		turtle.backwards((float)amount.execute(turtle, symbolTable));
		return null;
	}

}
