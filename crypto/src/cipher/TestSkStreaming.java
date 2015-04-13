package cipher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class TestSkStreaming {
	public static final String ALGORITMO = "DESede"; 
	public static final String ARCHIVO = "tmp/quijote.txt";
	public static final String ARCHIVO_CIFRADO = "tmp/quijote-cifrado.txt";
	public static final String ARCHIVO_DESCIFRADO = "tmp/quijote-descifrado.txt";
	
	private Cipher cipher;
	
	public TestSkStreaming() {
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
			
			FileOutputStream fos = new FileOutputStream(ARCHIVO_CIFRADO);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			
			cos.write(texto);
			
			cos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public byte[] descifrar(Key clave) {
		byte[] resultado = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, clave);
			
			FileInputStream fis = new FileInputStream(ARCHIVO_CIFRADO);
			CipherInputStream cis = new CipherInputStream(fis, cipher);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int b = cis.read();
			while (b > -1) {
				baos.write(b);
				b = cis.read();
			}
			
			Files.write(new File(ARCHIVO_DESCIFRADO).toPath(), 
					baos.toByteArray(), StandardOpenOption.CREATE);
			
			cis.close();
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
		
		cifrar(texto, clave);
		System.out.println("Cifrado...");
		
		descifrar(clave);
		System.out.println("Descifrado...");
	}
	
	public static void main(String[] args) throws Exception {
		new TestSkStreaming().iniciar();
	}
}
