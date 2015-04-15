package rmi.persona;

import java.util.List;

public class PersonaImpl {
	private List<Persona> personas;
	
	public List<Persona> obtenerTodos() {
		return personas;
	}
	
	public void agregar(Persona p) {
		personas.add(p);
	}
}
