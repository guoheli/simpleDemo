import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;

/**
 * Created by zengxiaoning on 2021/4/11.
 */
public class EncryptDemo {

	/**
	 * [0][1][2][3] [4][5][6][7][8][9][10][11] [12 ...]
	 *   length           attach                 text
 	 */

	public static void main(String[] args) {
		ByteBuffer tss = ByteBuffer.allocate(10);
		tss.put("2b".getBytes());
		tss.putInt(3);
		int anInt1 = tss.getInt();

		if(true) {
			return;
		}
		// 加密后数据
		byte[] text = "ddd".getBytes();
		// length + context + encrypt + code
		ByteBuffer byteBuffer = ByteBuffer.allocate(4 + text.length + 4 + 4).putInt(text.length);
		byteBuffer.put(text);
		byteBuffer.putInt(33);
		byteBuffer.putInt(4444);

		byte[] array = byteBuffer.array();
		String t = Base64.encodeBase64String(array);
		System.out.println("==========加密后内容" + t);
		byte[] bytes = Base64.decodeBase64(t);
		System.out.println(new String(bytes));
		ByteBuffer wrap = ByteBuffer.wrap(bytes);
		
		int length = wrap.getInt();
		int remaining = wrap.remaining();
		byte[] context = new byte[length];
//		 wrap.get(context, 4, length);
		wrap.get(context);
		System.out.println("===解密：" + new String(context));
		int anInt = wrap.getInt();
		int cdoe = wrap.getInt();
	}
}
