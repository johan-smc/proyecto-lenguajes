package co.edu.javeriana.logo.ast;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import co.edu.javeriana.logo.Turtle;
import utils.SymbolTable;

public class Read implements ASTNode {

	private String name;
	
	public Read(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public Object execute(Turtle turtle, SymbolTable symbolTable) {
		Scanner sc = new Scanner(System.in);
		Class<? extends Object> toCast = symbolTable.get(name).getClass();
		try {
			symbolTable.assign(name, toCast.getConstructor(new Class[] {String.class}).newInstance(sc.next()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		return null;
	}

}
