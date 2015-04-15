package rmi.calculadora;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote, Serializable {
	public float sumar(float a, float b) throws RemoteException;
	public float restar(float a, float b) throws RemoteException;
	public float multiplicar(float a, float b) throws RemoteException;
	public float dividir(float a, float b) throws RemoteException;
}
