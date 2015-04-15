package rmi.calculadora;

public abstract class CalculadoraFactory {
	public static Calculadora createCalculadora() {
		return new CalculadoraImpl();
	}
}
