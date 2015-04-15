package rmi.calculadora;

import java.rmi.RemoteException;

public abstract class CalculadoraFactory {
	public static Calculadora createCalculadora() throws RemoteException {
		return new CalculadoraImpl();
	}
}
