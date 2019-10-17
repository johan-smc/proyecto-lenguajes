package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;

public class Println implements ASTNode {
	
	private ASTNode data;
	
	public Println(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Turtle turtle) {
		System.out.println(data.execute(turtle));
		return null;
	}

}
