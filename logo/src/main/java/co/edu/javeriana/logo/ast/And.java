package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class And implements ASTNode {

	private ASTNode condition1;
	private ASTNode condition2;
	
	public And(ASTNode condition1, ASTNode condition2) {
		super();
		this.condition1 = condition1;
		this.condition2 = condition2;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		return (boolean)condition1.execute(turtle, symbolTable, listOfFunctions) && (boolean)condition2.execute(turtle, symbolTable, listOfFunctions);
	}

}
