package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SymbolTable {

	
	private List<Map<String, Object>> variables;

	public SymbolTable() {
		super();
		this.variables = new ArrayList<>();
		subirNivel();
	}

	public void subirNivel() {
		Map<String, Object> syTb = new HashMap<String, Object>();
		variables.add(syTb);
	}
	public void bajarNivel() {
		variables.remove(variables.size() - 1);
	}

	public Map<String, Object> getVariables() {
		return variables.get(variables.size()-1);
	}
	
	public boolean assign(String nombre, Object obj)
	{
		
		boolean found = false;
		for( int pos = variables.size() -1 ; pos >= 0  && !found ; pos --)
		{
			if( variables.get( pos ).containsKey(nombre) )
			{
				variables.get( pos ).put(nombre, obj);
				found = true;
			}
		}
		return found;
	}
	public Object get(String nombre)
	{
		//Se revisan todas las variables porque se puede acceder a variables de instancias anteriores
		for( int pos = variables.size() -1 ; pos >= 0   ; pos --) 
		{
			if( variables.get( pos ).containsKey(nombre) )
			{
				return variables.get( pos ).get(nombre);
			}
		}
		return null;
	}
	
	// agrega el nuevo objeto al mapa de variables superior 
	public void create(String nombre, Object obj)
	{
		this.getVariables().put(nombre, obj);	
	}
	
}
