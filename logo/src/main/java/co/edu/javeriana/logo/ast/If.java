package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
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
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		symbolTable.subirNivel();
		Object obj = null;
		if( (boolean)condition.execute(turtle, symbolTable, listOfFunctions) )
		{
			for(ASTNode n: body){
				obj = n.execute(turtle, symbolTable, listOfFunctions);
				if (obj != null) {
					symbolTable.bajarNivel();
					return obj;
				}
			}
		}else{
			for(ASTNode n: elseBody){
				obj=n.execute(turtle, symbolTable, listOfFunctions);
				if (obj != null) {
					symbolTable.bajarNivel();
					return obj;
				}
			}
		}
		symbolTable.bajarNivel();
		return null;
	}

}
