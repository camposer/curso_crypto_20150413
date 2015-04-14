package signature;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

import javax.xml.bind.DatatypeConverter;

public class TestSignatureJks {
	public static final String ALGORITMO = "RSA";
	public static final String ALGORITMO_FIRMA = "SHA256with" + ALGORITMO;
	public static final String ARCHIVO = "tmp/quijote.txt";
	public static final String KEYSTORE = "stores/keystore.jks"; 
	public static final String ALIAS_KEYSTORE = "mycert";
	public static final String CLAVE_KEYSTORE = "123456";
	
	private Signature signature;
	private KeyPair keyPair;

	public TestSignatureJks() {
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
			FileInputStream is = new FileInputStream(KEYSTORE);

			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(is, CLAVE_KEYSTORE.toCharArray());

			Key key = keystore.getKey(ALIAS_KEYSTORE, CLAVE_KEYSTORE.toCharArray());
			if (key instanceof PrivateKey) {
				// Get certificate of public key
				Certificate cert = keystore.getCertificate(ALIAS_KEYSTORE);

				// Get public key
				PublicKey publicKey = cert.getPublicKey();

				// Return a key pair
				keyPair = new KeyPair(publicKey, (PrivateKey) key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void iniciar() throws Exception {
		byte[] texto = Files.readAllBytes(new File(ARCHIVO).toPath());

		generarClaves();
		byte[] firma = firmar(texto);
		System.out.println("firma = "
				+ DatatypeConverter.printBase64Binary(firma));
		System.out.println("verificar = " + verificar(texto, firma));
	}

	public static void main(String[] args) throws Exception {
		new TestSignatureJks().iniciar();
	}
}
