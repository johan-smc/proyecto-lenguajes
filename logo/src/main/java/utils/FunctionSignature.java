package utils;

import java.util.List;

import co.edu.javeriana.logo.ast.ASTNode;

public class FunctionSignature {
	private String name;
	private List<String> argumments;
	private ASTNode block;
	
	public FunctionSignature(String name, List<String> argumments, ASTNode block) {
		super();
		this.name = name;
		this.argumments = argumments;
		this.block = block;
	}

	public String getName() {
		return name;
	}

	public List<String> getArgumments() {
		return argumments;
	}


	public ASTNode getBlock() {
		return block;
	}

	
	
}