package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class FunctionSignature implements ASTNode {
	private String name;
	private List<String> argumments;
	private List<ASTNode> block;
	 
	public List<String> getArgumments() {
		return argumments;
	}

	public List<ASTNode> getBlock() {
		return block;
	}

	public FunctionSignature(String name, List<String> argumments, List<ASTNode> block) {
		super();
		this.name = name;
		this.argumments = argumments;
		this.block = block;
	}

	public String getName() {
		return name;
	}

	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		if( listOfFunctions.exist(this.name) )
		{
			throw new Exception("Funcion ya creada.");
			//System.err.println("Funcion ya creada");
			
		}
		
		listOfFunctions.create(this.name, this);
		return null;
	}



	
	
}