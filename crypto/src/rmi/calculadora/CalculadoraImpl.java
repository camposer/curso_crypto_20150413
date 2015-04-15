package rmi.calculadora;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora {
	protected CalculadoraImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public float sumar(float a, float b) throws RemoteException {
		return a + b;
	}

	@Override
	public float restar(float a, float b) throws RemoteException {
		return a - b;
	}

	@Override
	public float multiplicar(float a, float b) throws RemoteException {
		return a * b;
	}

	@Override
	public float dividir(float a, float b) throws RemoteException {
		return a / b;
	}

}
