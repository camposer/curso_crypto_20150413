package rmi.calculadora;

import java.io.Serializable;
import java.rmi.Remote;

public interface Calculadora extends Remote, Serializable {
	public float sumar(float a, float b);
	public float restar(float a, float b);
	public float multiplicar(float a, float b);
	public float dividir(float a, float b);
}
