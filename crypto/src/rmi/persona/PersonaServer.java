package rmi.persona;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * -Djavax.net.ssl.trustStore=stores/keystore.jks
 * -Djavax.net.ssl.trustStorePassword=123456
 * -Djavax.net.ssl.keyStore=stores/keystore.jks
 * -Djavax.net.ssl.keyStorePassword=123456
 */
public class PersonaServer {
	public static final int PUERTO_RMI = 1099;
	
	public static void main(String[] args) throws Exception {
		new PersonaServer().iniciar();
	}

	private void iniciar() throws Exception {
		// 1.- Iniciando registro RMI
		Registry registry = LocateRegistry.createRegistry(PUERTO_RMI);
		System.out.println("Registro RMI iniciado...");
		
		// 2.- Creando instancia del objeto remoto
		PersonaService personaService = new PersonaServiceImpl();
		
		// 3.- Enlazando objeto remoto
		registry.rebind("personaService", personaService);
		System.out.println("Objeto enlazado");
	}
}
