package signature;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import javax.xml.bind.DatatypeConverter;

public class TestSignature {
	public static final String ALGORITMO = "RSA";
	public static final String ALGORITMO_FIRMA = "SHA256with" + ALGORITMO;
	public static final String ARCHIVO = "tmp/quijote.txt";
	private Signature signature;
	private KeyPair keyPair;

	public TestSignature() {
		try {
			this.signature = Signature.getInstance(ALGORITMO_FIRMA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] firmar(byte[] texto) {
		byte[] resultado = null;
		
		try {
			signature.initSign(keyPair.getPrivate());
			signature.update(texto);
			resultado = signature.sign();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	private boolean verificar(byte[] texto, byte[] firma) {
		boolean resultado = false;
		
		try {
			signature.initVerify(keyPair.getPublic());
			signature.update(texto);
			resultado = signature.verify(firma);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	private void generarClaves() {
		try {
			keyPair = KeyPairGenerator.getInstance(ALGORITMO).generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciar() throws Exception {
		byte[] texto = Files.readAllBytes(new File(ARCHIVO).toPath());

		generarClaves();
		byte[] firma = firmar(texto);
		System.out.println("firma = " + DatatypeConverter.printBase64Binary(firma));
		System.out.println("verificar = " + verificar(texto, firma));
	}

	public static void main(String[] args) throws Exception {
		new TestSignature().iniciar();
	}
}
