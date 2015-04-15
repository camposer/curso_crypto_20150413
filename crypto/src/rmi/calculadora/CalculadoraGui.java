package rmi.calculadora;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculadoraGui {
	private Scanner scanner;
	private Calculadora calculadora;
	
	public CalculadoraGui() {
		this.scanner = new Scanner(System.in);
		try {
			Registry registry = LocateRegistry.getRegistry();
			this.calculadora = (Calculadora)registry.lookup("calculadora");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new CalculadoraGui().iniciar();
	}

	private void iniciar() {

		while (true) {
			System.out.println();
			System.out.println("1. Sumar");
			System.out.println("2. Restar");
			System.out.println("3. Multiplicar");
			System.out.println("4. Dividir");
			System.out.println("5. Salir");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("5"))
				break;
			
			System.out.print("a? ");
			Float a = Float.parseFloat(scanner.nextLine());
			
			System.out.print("b? ");
			Float b = Float.parseFloat(scanner.nextLine());
			
			if (opcion.equals("1"))
				System.out.println(a + " + " + b + " = " + calculadora.sumar(a, b));
			else if (opcion.equals("2"))
				System.out.println(a + " - " + b + " = " + calculadora.restar(a, b));
			else if (opcion.equals("3"))
				System.out.println(a + " * " + b + " = " + calculadora.multiplicar(a, b));
			else if (opcion.equals("4"))
				System.out.println(a + " / " + b + " = " + calculadora.dividir(a, b));
		}
	}

}
