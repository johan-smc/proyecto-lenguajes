package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class Println implements ASTNode {
	
	private ASTNode data;
	
	public Println(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		System.out.println(data.execute(turtle, symbolTable));
		return null;
	}

}
