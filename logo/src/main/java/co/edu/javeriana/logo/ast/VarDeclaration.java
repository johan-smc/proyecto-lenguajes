package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class VarDeclaration implements ASTNode {

	private String name;
	private ASTNode expression;
	
	public VarDeclaration(String name) {
		super();
		this.name = name;
		this.expression = null;
	}
	
	public VarDeclaration(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		Object value = null;
		if( this.expression != null )
		{
			value = this.expression.execute(turtle, symbolTable, listOfFunctions);
		}
		symbolTable.create(name, value);
		return null;
	}

}
