package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
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
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		symbolTable.subirNivel();
		Object obj = null;
		while ((boolean) condition.execute(turtle, symbolTable, listOfFunctions)) {
			for (ASTNode n : body) {
				obj = n.execute(turtle, symbolTable, listOfFunctions);
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
