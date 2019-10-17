package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class Not implements ASTNode {

	private ASTNode condition;
	
	public Not(ASTNode condition) {
		super();
		this.condition = condition;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		return !((boolean)this.condition.execute(turtle, symbolTable, listOfFunctions));
	}

}
