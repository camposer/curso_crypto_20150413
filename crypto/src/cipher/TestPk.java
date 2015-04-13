package cipher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class TestPk {
	public static final String ALGORITMO = "RSA";
	public static final String ARCHIVO = "tmp/quijote.txt";
	public static final int MAX_BLOQUE_CIFRADO = 117; // El resto de bytes los
														// llena con información
														// del algoritmo!!
	public static final int MAX_BLOQUE_DESCIFRADO = 128;
	private Cipher cipher;
	private Key publica;
	private Key privada;

	public TestPk() {
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

			int bloques = (int) Math.ceil((double) texto.length
					/ MAX_BLOQUE_CIFRADO);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int maxBloqueCifradoLen = MAX_BLOQUE_CIFRADO;

			for (int i = 0; i < bloques; i++) {
				if (i + 1 == bloques)
					maxBloqueCifradoLen = texto.length
							- (i * MAX_BLOQUE_CIFRADO);

				byte[] bloqueCifrado = cipher.doFinal(texto, i
						* MAX_BLOQUE_CIFRADO, maxBloqueCifradoLen);

				baos.write(bloqueCifrado);
			}

			resultado = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public byte[] descifrar(byte[] texto, Key clave) {
		byte[] resultado = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, clave);

			int bloques = (int) Math.ceil((double) texto.length
					/ MAX_BLOQUE_DESCIFRADO);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int maxBloqueDescifradoLen = MAX_BLOQUE_DESCIFRADO;
			for (int i = 0; i < bloques; i++) {
				if (i + 1 == bloques)
					maxBloqueDescifradoLen = texto.length
							- (i * MAX_BLOQUE_DESCIFRADO);

				byte[] bloque = cipher.doFinal(texto,
						i * MAX_BLOQUE_DESCIFRADO, maxBloqueDescifradoLen);

				baos.write(bloque);
			}

			resultado = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private void generarClaves() {
		try {
			KeyPairGenerator generator = KeyPairGenerator
					.getInstance(ALGORITMO);

			KeyPair keyPair = generator.generateKeyPair();
			publica = keyPair.getPublic();
			privada = keyPair.getPrivate();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void iniciar() throws Exception {
		byte[] texto = Files.readAllBytes(new File(ARCHIVO).toPath());

		generarClaves();

		byte[] cifrado = cifrar(texto, publica);
		System.out.println("cifrado (base 64) = "
				+ DatatypeConverter.printBase64Binary(cifrado));

		byte[] descifrado = descifrar(cifrado, privada);
		System.out.println("descifrado = " + new String(descifrado));
	}

	public static void main(String[] args) throws Exception {
		new TestPk().iniciar();
	}
}
