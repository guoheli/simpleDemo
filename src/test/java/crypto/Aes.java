package crypto;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Random;

/**
 * https://cloud.githubusercontent.com/assets/8476366/25062156/b105fc88-21f0-11e7-9b06-59e0dae9a3a8.png
 * https://github.com/harticocaesario/CTR-AES-Calculator/blob/master/src/CtrAES.java
 */
public class Aes {
	private Cipher cipher;
	private SecretKey key;
	private byte[] iv = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x01 };

	public Aes() {
		try {
			cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*ENCRYPTION*/
	public byte[] encrypt(byte[] plain, byte[] key) throws Exception{
		SecretKey key_encrypt = generateKey(key);
		this.key = key_encrypt;
		cipher.init(Cipher.ENCRYPT_MODE, this.key, new IvParameterSpec(iv));
		byte[] cipher_bytes = cipher.doFinal(plain);

		return cipher_bytes;
	}

	/*DECRYPTION*/
	public byte[] decrypt(byte[] cipher_bytes, byte[] key) throws Exception{
		SecretKey key_decrypt = generateKey(key);
		this.key = key_decrypt;
		cipher.init(Cipher.DECRYPT_MODE, this.key, new IvParameterSpec(iv));
		byte[] plain_bytes = cipher.doFinal(cipher_bytes);
		return plain_bytes;
	}

	private SecretKey generateKey(byte[] key) {
		return new SecretKeySpec(key, "AES");
	}
}