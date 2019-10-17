package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public interface ASTNode {
	public Object execute(Turtle turtle, SymbolTable symbolTable);
}
