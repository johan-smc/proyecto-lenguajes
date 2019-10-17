package co.edu.javeriana.logo.ast;

import java.util.Scanner;

import co.edu.javeriana.logo.Turtle;
import utils.ListOfFunctions;
import utils.SymbolTable;

public class Read implements ASTNode {

	private String name;
	static final private Scanner sc = new Scanner(System.in);
	
	public Read(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable, ListOfFunctions listOfFunctions) throws Exception {
		
		
		Object var = sc.nextLine();
		
		try {
			var = Float.valueOf((String)var) ;
		} catch ( NumberFormatException number_e) {
			try {
				var = Boolean.valueOf((String)var) ;
			} catch ( Exception e) {
				
			}
		}
		
		
		symbolTable.assign(name, var );
		
		return null;
	}

}
