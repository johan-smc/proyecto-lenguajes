package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class FunctionExecution implements ASTNode {

	private String name;
	private List<ASTNode> params;
	
	public FunctionExecution(String name, List<ASTNode> params) {
		super();
		this.name = name;
		this.params = params;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		FunctionSignature functionSignature = (FunctionSignature) listOfFunctions.get(this.name); 
		
		symbolTable.subirNivel();
		
		
		List<String> arguments = functionSignature.getArgumments();
		if( arguments.size() != this.params.size() )
		{
			throw new Exception("Numero de argumentos invalido.");
		}
		
		for( int i = 0; i < arguments.size() ; ++i )
		{
			symbolTable.create((String)arguments.get(i), this.params.get(i).execute(turtle, symbolTable, listOfFunctions));
		}
		
		List<ASTNode> block = functionSignature.getBlock();
		
		for(ASTNode n: block){
			n.execute(turtle, symbolTable, listOfFunctions);
		}
		
		
		symbolTable.bajarNivel();
		
		return null;
	}
	

}
