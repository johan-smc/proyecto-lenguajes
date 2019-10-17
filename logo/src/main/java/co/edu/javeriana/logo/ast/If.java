package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class If implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	
	public If(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		symbolTable.subirNivel();
		if( (boolean)condition.execute(turtle, symbolTable))
		{
			for(ASTNode n: body){
				n.execute(turtle, symbolTable);
			}
		}else{
			for(ASTNode n: elseBody){
				n.execute(turtle, symbolTable);
			}
		}
		symbolTable.bajarNivel();
		return null;
	}

}
