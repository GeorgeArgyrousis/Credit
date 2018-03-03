package utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private static final String ALGORITHM = "AES";

	public Encryption() {
	}

	/**
	 * Encrypts the given plain text
	 *
	 * @param plainText
	 *            The plain text to encrypt
	 */
	public byte[] encrypt(byte key[], byte text[]) {
		Cipher cipher;
		SecretKeySpec secretKey;
		byte t[] = new byte[text.length];
		try {
			secretKey = new SecretKeySpec(key, ALGORITHM);
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			t = cipher.doFinal(text);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Decrypts the given byte array
	 *
	 * @param cipherText The data to decrypt
	 */
	public byte[] decrypt(byte key[], byte text[]) {
		SecretKeySpec secretKey;
		Cipher cipher;
		byte t[] = new byte[text.length];
		try {
			secretKey = new SecretKeySpec(key, ALGORITHM);
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			t = cipher.doFinal(text);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return t;
	}
}
