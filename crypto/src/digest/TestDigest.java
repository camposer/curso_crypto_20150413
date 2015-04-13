package digest;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

public class TestDigest {
	public static final String ALGORITMO = "SHA-1";
	public static final String ARCHIVO = "tmp/quijote.txt";
	private MessageDigest messageDigest;

	public TestDigest() {
		try {
			this.messageDigest = MessageDigest.getInstance(ALGORITMO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String digest(byte[] texto) {
		return byteArrayToHex(messageDigest.digest(texto));
	}

	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}

	public void iniciar() throws Exception {
		byte[] texto = Files.readAllBytes(new File(ARCHIVO).toPath());

		System.out.println("digest = " + digest(texto));
	}

	public static void main(String[] args) throws Exception {
		new TestDigest().iniciar();
	}
}
