package cipher;

import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.xml.bind.DatatypeConverter;

public class TestSk {
	public static final String ALGORITMO = "AES"; // "DES"
	public static final String ARCHIVO = "tmp/quijote.txt";
	private Cipher cipher;
	
	public TestSk() {
		try {
			this.cipher = Cipher.getInstance(ALGORITMO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] cifrar(byte[] texto, Key clave) {
		byte[] resultado = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, clave);
			resultado = cipher.doFinal(texto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public byte[] descifrar(byte[] texto, Key clave) {
		byte[] resultado = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, clave);
			resultado = cipher.doFinal(texto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	private Key obtenerClave() {
		Key resultado = null;
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITMO);
			resultado = generator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public void iniciar() throws Exception {
		byte[] texto = Files.readAllBytes(new File(ARCHIVO).toPath());
		
		Key clave = obtenerClave();
		System.out.println("clave = " + clave);
		
		byte[] cifrado = cifrar(texto, clave);
		System.out.println("cifrado (base 64) = " + DatatypeConverter.printBase64Binary(cifrado));
		
		byte[] descifrado = descifrar(cifrado, clave);
		System.out.println("descifrado = " + new String(descifrado));
	}
	
	public static void main(String[] args) throws Exception {
		new TestSk().iniciar();
	}
}
