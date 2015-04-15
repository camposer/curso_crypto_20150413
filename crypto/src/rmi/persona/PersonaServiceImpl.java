package rmi.persona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class PersonaServiceImpl extends UnicastRemoteObject implements PersonaService {
	private static final long serialVersionUID = -3333502387502098921L;

	private long contador = 1;
	private List<Persona> personas = new ArrayList<Persona>();
	
	protected PersonaServiceImpl() throws RemoteException {
		//super(); // No SSL
		//super(0, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory()); // SSL - 1 way
		super(0, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, true)); // SSL - 2 way
	}

	@Override
	public List<Persona> obtenerTodos() throws RemoteException {
		return personas;
	}
	
	@Override
	public void agregar(Persona p) throws RemoteException {
		p.setId(contador++);
		personas.add(p);
	}
}
