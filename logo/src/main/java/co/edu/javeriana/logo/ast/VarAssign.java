package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class VarAssign implements ASTNode {

	private String name;
	private ASTNode expression;
	
	
	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		boolean found = symbolTable.assign(name, expression.execute(turtle, symbolTable));
		if( !found )
		{
			// TODO - do error
		}
		return null;
	}

}
