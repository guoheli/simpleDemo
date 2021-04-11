package aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by zengxiaoning on 2021/4/11.
 */
public class AesTools {

//	private static final String initVector = "desensitization-vector";

	private static final String initVector="encryptionIntVec";

	public static String decrypt(byte[] encrypted, String mode, String key) throws Exception{
		IvParameterSpec ivSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
	    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = getDefaultCipher(mode, keySpec, ivSpec, Cipher.DECRYPT_MODE);
		byte[] bytes = cipher.doFinal(encrypted);
		return new String(bytes);
	}


	public static byte[] encrypt(String input, String mode, String key) throws Exception
	{
		IvParameterSpec ivSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = getDefaultCipher(mode, keySpec, ivSpec, Cipher.ENCRYPT_MODE);
		if(mode == "ECB")
			return cipher.doFinal(input.getBytes());
		else
			return cipher.doFinal(input.getBytes("UTF-8"));
	}

	private static Cipher getDefaultCipher(String mode, SecretKeySpec keySpec,  IvParameterSpec ivSpec, int cipherMode) throws Exception {
		Cipher cipher;
		switch (mode){
			case "ECB":
				cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(cipherMode, keySpec);
				break;
			case "CBC":
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(cipherMode, keySpec, ivSpec);
				break;
			case "OFB":
				cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
				cipher.init(cipherMode, keySpec, ivSpec);
				break;
			case "CFB":
				cipher= Cipher.getInstance("AES/CFB/PKCS5Padding");
				cipher.init(cipherMode, keySpec, ivSpec);
				break;
			case "CTR":
				cipher = Cipher.getInstance("AES/CTR/NoPadding");
				cipher.init(cipherMode, keySpec, ivSpec);
				break;
			default:
				cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(cipherMode, keySpec);
				break;
		}
		return cipher;
	}

	public static byte[] generateIV() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] seeds = secureRandom.generateSeed(16);
		secureRandom.setSeed(seeds);

		byte[] ivBytes = new byte[16];
		secureRandom.nextBytes(ivBytes);

		return ivBytes;
	}
}
