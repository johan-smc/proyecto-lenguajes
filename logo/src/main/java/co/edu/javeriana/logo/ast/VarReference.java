package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class VarReference implements ASTNode {

	private String name;
	
	public VarReference(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public Object execute(Turtle turtle) {
		// TODO Auto-generated method stub
		return null;
	}

}
