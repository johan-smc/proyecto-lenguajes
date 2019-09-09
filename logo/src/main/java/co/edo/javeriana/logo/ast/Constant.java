package co.edo.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Constant implements ASTNode {

	private Object value;
	
	public Constant(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object execute(Turtle turtle) {
		return value;
	}

}
