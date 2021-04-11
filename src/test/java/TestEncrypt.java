import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;

/**
 * Created by zengxiaoning on 2021/4/11.
 */
public class TestEncrypt {

	public static void main(String[] args) {
		String ab= "1s21";
		ByteBuffer byteBuffer = ByteBuffer.allocate(50).putInt(4).put(ab.getBytes());
		int anInt = byteBuffer.getInt();
		System.out.println("===="+ anInt);
		if(true) {
			return;
		}
		int code = 1000;
		int aglf = 0x1;
		System.out.println( 0x1 + "---" +  0x2 + "----" +  0x3);
		// 提前合并
		long attach = combineToLong(aglf, code);
		String str = "dfsfaf{}242";
		byte[] encrypt = encrypt(str.getBytes(), attach, "342$1");
		// 对外输出
		String base64String = Base64.encodeBase64String(encrypt);
		System.out.println("===密文" + base64String);

		// 开始解密
		byte[] bytes = Base64.decodeBase64(base64String);
		ByteBuffer wrap = ByteBuffer.wrap(bytes);
		int l = wrap.getInt();
		if(l == 0) {
			System.out.println("错误格式");
			return;
		}
		long aLong = wrap.getLong();
		int[] intByLong = getIntByLong(aLong);
		if(aLong <= 0) {
			System.out.println("错误格式2");
			return;
		}
		int remaining = wrap.remaining();
		if(l != remaining) {
			System.out.println("错误格式2");
			return;
		}
		byte[] b = new byte[l];
		wrap.get(b);
		System.out.println("解密内容：" + new String(b));

	}


	/**
	 *
	 * @param context 明文
	 * @param attach
	 * @param password
	 * @return
	 */
	private static byte[] encrypt(byte[] context, long attach, String password) {
		// 进行加密
		ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 8 + context.length);
		byteBuffer.put(context);
		byteBuffer.putInt(context.length);
		byteBuffer.putLong(attach);

		return  byteBuffer.array();

	}

	private static String decrypt(byte[] text, String password) {
        return new String(text);
	}

	/**
	 * 将两个int 组合为 long
	 *
	 * @param high 高位, 存算法
	 * @param low  低位 存编码
	 * @return
	 */
	public static long combineToLong(int high, int low) {
		return ((long) low & 0xFFFFFFFFl) | (((long) high << 32) & 0xFFFFFFFF00000000l);
	}

	/**
	 * 将一个long 拆分为两个 int，返回int[高位，低位]
	 *
	 * @param val long数字
	 */
	public static int[] getIntByLong(long val) {

		return new int[]{(int) ((0xFFFFFFFF00000000l & val) >> 32), (int) (0xFFFFFFFFl & val)};
	}

}
