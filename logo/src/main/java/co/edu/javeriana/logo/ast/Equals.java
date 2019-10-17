package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class Equals implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public Equals(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		if( !operand1.getClass().equals(operand2.getClass()) )
		{
			return false;
		}
		return operand1.execute(turtle, symbolTable, listOfFunctions).equals(operand2.execute(turtle, symbolTable, listOfFunctions));
	}

}
