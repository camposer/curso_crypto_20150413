package rmi.persona;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

/**
 * -Djavax.net.ssl.trustStore=stores/keystore.jks
 * -Djavax.net.ssl.trustStorePassword=123456
 * -Djavax.net.ssl.keyStore=stores/keystore.jks
 * -Djavax.net.ssl.keyStorePassword=123456
 */
public class PersonaGui {
	private Scanner scanner;
	private PersonaService personaService;
	
	public PersonaGui() {
		this.scanner = new Scanner(System.in);
		try {
			Registry registry = LocateRegistry.getRegistry();
			this.personaService = (PersonaService)registry.lookup("personaService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		new PersonaGui().iniciar();
	}

	private void iniciar() throws RemoteException {

		while (true) {
			System.out.println();
			System.out.println("1. Agregar");
			System.out.println("2. Obtener todos");
			System.out.println("3. Salir");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("1"))
				agregar();
			else if (opcion.equals("2"))
				obtenerTodos();
			else if (opcion.equals("3"))
				break;

		}
	}

	private void obtenerTodos() {
		try {
			List<Persona> personas = personaService.obtenerTodos();
			if (personas != null) for (Persona p : personas)
				System.out.println(p);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void agregar() {
		System.out.print("Nombre? ");
		String nombre = scanner.nextLine();
		System.out.print("Apellido? ");
		String apellido = scanner.nextLine();
		System.out.print("Edad? ");
		Integer edad = Integer.parseInt(scanner.nextLine());
		
		try {
			personaService.agregar(new Persona(nombre, apellido, edad));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}



