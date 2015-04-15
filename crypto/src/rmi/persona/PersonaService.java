package rmi.persona;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PersonaService extends Remote, Serializable {
	public List<Persona> obtenerTodos() throws RemoteException;
	public void agregar(Persona p) throws RemoteException;
}
