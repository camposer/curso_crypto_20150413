package rmi.calculadora;

public class CalculadoraImpl implements Calculadora {
	private static final long serialVersionUID = 1L;

	@Override
	public float sumar(float a, float b) {
		return a + b;
	}

	@Override
	public float restar(float a, float b) {
		return a - b;
	}

	@Override
	public float multiplicar(float a, float b) {
		return a * b;
	}

	@Override
	public float dividir(float a, float b) {
		return a / b;
	}

}
