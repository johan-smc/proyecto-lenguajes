package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class While implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	
	public While(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		symbolTable.subirNivel();
		while( (boolean)condition.execute(turtle, symbolTable) ) {
			for(ASTNode n: body){
				n.execute(turtle, symbolTable);
			}
		}
		symbolTable.bajarNivel();
		return null;
	}

}
