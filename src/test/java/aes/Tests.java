package aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Tests {
//    private static final String key = "aesEncryptionKey";
//    private static final String initVector = "encryptionIntVec";

//	private static final String key = "aesEncryptionKey";
	private static final String initVector = "aesEncryptionKey";

	public static void main(String[] args) throws Exception {
		CorruptionTest();
	}
    // 对长度要要求
	public static final String key = "aesEncryptionKey";
	public static void CorruptionTest() throws Exception {
        String originalString = "This is an example sentence that checks the corruption of each mode of the AES cipher";
        System.out.println("Original: " + originalString);
		String encryptedString =  encrypt(originalString);
       // String encryptedString = new String(AesTools.encrypt(originalString, "CTR", key));
        System.out.println("Encrypted: " + encryptedString);
        // 解密
		String decrypt = decrypt(encryptedString);
//	    String decrypt = AesTools.decrypt(encryptedString.getBytes(), "CTR", key);
	    System.err.println("Original: "  + decrypt);
    }


    public static String encrypt(String value) {
        try {
            //CBC dziala z PKCS5PADDING
            //ECB dziala bez IV
            //OFB dziala z PKCS5PADDING
            //CFB dziala z PKCS5PADDING
            //CTR dziala z NoPadding
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
