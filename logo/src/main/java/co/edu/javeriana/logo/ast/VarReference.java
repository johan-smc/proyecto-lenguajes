package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class VarReference implements ASTNode {

	private String name;
	
	public VarReference(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		return symbolTable.get(this.name);
	}

}
