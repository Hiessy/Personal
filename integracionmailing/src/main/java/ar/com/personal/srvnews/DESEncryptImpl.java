package ar.com.personal.srvnews;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESEncryptImpl implements HasEncryption {
	private String key = "";
	private static String algoritmo = "DES";
	private static String cipheralg = "DES/ECB/PKCS5Padding";

	public DESEncryptImpl(String key) {
		this.key = key;
	}

	public String encrypt(String source) {
		try {
			// Get our secret key
			Key key = getKey();

			// Create the cipher
			Cipher desCipher = Cipher.getInstance(cipheralg);

			// Initialize the cipher for encryption
			desCipher.init(Cipher.ENCRYPT_MODE, key);

			// Our cleartext as bytes
			byte[] cleartext = source.getBytes();

			// Encrypt the cleartext
			byte[] ciphertext = desCipher.doFinal(cleartext);

			// Return a String representation of the cipher text
			return getString(ciphertext);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String generateKey() {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(algoritmo);
			SecretKey desKey = keygen.generateKey();
			byte[] bytes = desKey.getEncoded();

			setKey(getString(bytes));
			return key;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String decrypt(String source) {
		try {
			// Get our secret key
			Key key = getKey();

			// Create the cipher
			Cipher desCipher = Cipher.getInstance(cipheralg);

			// Encrypt the cleartext
			byte[] ciphertext = getBytes(source);

			// Initialize the same cipher for decryption
			desCipher.init(Cipher.DECRYPT_MODE, key);

			// Decrypt the ciphertext
			byte[] cleartext = desCipher.doFinal(ciphertext);

			// Return the clear text
			return new String(cleartext);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String showKey() {
		return key;
	}

	public Key getKey() {
		try {
			byte[] bytes = getBytes(key);
			DESKeySpec pass = new DESKeySpec(bytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(algoritmo);
			SecretKey s = skf.generateSecret(pass);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns true if the specified text is encrypted, false otherwise
	 */
	public boolean isEncrypted(String text) {
		// If the string does not have any separators then it is not
		// encrypted
		if (text.indexOf('-') == -1) {
			// /System.out.println( "text is not encrypted: no dashes" );
			return false;
		}

		StringTokenizer st = new StringTokenizer(text, "-", false);
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.length() > 3) {
				// System.out.println(
				// "text is not encrypted: length of token greater than 3: " +
				// token );
				return false;
			}
			for (int i = 0; i < token.length(); i++) {
				if (!Character.isDigit(token.charAt(i))) {
					// System.out.println(
					// "text is not encrypted: token is not a digit" );
					return false;
				}
			}
		}
		// System.out.println( "text is encrypted" );
		return true;
	}

	private String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			sb.append((int) (0x00FF & b));
			if (i + 1 < bytes.length) {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	private byte[] getBytes(String str) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StringTokenizer st = new StringTokenizer(str, "-", false);
		while (st.hasMoreTokens()) {
			int i = Integer.parseInt(st.nextToken());
			bos.write((byte) i);
		}
		return bos.toByteArray();
	}

	public void showProviders() {
		try {
			Provider[] providers = Security.getProviders();
			for (int i = 0; i < providers.length; i++) {
				System.out.println("Provider: " + providers[i].getName() + ", " + providers[i].getInfo());
				for (Iterator<?> itr = providers[i].keySet().iterator(); itr.hasNext();) {
					String key = (String) itr.next();
					String value = (String) providers[i].get(key);
					System.out.println("\t" + key + " = " + value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
