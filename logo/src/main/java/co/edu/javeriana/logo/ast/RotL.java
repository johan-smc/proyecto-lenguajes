package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class RotL implements ASTNode {

	private ASTNode degrees;
	
	public RotL(ASTNode degrees) {
		super();
		this.degrees = degrees;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		turtle.left((float)degrees.execute(turtle, symbolTable, listOfFunctions));
		return null;
	}

}
