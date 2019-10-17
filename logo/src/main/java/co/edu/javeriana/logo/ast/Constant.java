package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class Constant implements ASTNode {

	private Object value;
	
	public Constant(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		return value;
	}

}
