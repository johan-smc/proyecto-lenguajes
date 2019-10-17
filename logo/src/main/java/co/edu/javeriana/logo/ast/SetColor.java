package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class SetColor implements ASTNode {

	private ASTNode red;
	private ASTNode green;
	private ASTNode blue;
	private ASTNode alpha;
	
	
	
	public SetColor(ASTNode red, ASTNode green, ASTNode blue, ASTNode alpha) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}



	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		turtle.color(
				(float)red.execute(turtle, symbolTable),
				(float)green.execute(turtle, symbolTable),
				(float)blue.execute(turtle, symbolTable),
				(float)alpha.execute(turtle, symbolTable)
				);
		return null;
	}

}
