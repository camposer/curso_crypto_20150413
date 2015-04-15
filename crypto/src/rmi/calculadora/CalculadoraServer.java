package rmi.calculadora;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraServer {
	public static final int PUERTO_RMI = 1099;
	public static final int TIMEOUT = 5 * 1000;
	private static Registry registry;
	
	public static void main(String[] args) throws Exception {
		new CalculadoraServer().iniciar();
	}

	private void iniciar() throws Exception {
		// 1.- Iniciando registro RMI
		registry = LocateRegistry.createRegistry(PUERTO_RMI);
		System.out.println("Registro RMI iniciado...");
		
		// 2.- Creando instancia del objeto remoto
		Calculadora calculadora = CalculadoraFactory.createCalculadora();
		
		// 3.- Enlazando objeto remoto
		registry.rebind("calculadora", calculadora);
		System.out.println("Objeto enlazado");
	}
}
