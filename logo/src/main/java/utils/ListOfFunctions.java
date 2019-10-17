package utils;

import java.util.HashMap;
import java.util.Map;

public class ListOfFunctions {
	private Map<String, FunctionSignature> functions;
	public ListOfFunctions() {
		functions = new HashMap<String, FunctionSignature>();
	}
	
	// 
	
	// agrega el nuevo objeto al mapa de variables superior 
	public void create(String nombre, FunctionSignature function)
	{
		functions.put(nombre, function);
	}
	
	// busca un objeto en el conjunto de mapas de variables y retorna el primero en ser encontrado
	public Object get(String nombre)
	{
		return functions.get(nombre);
	}

}
