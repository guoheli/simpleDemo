import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by zengxiaoning on 2021/4/10.
 */
public class CharByte {

	public static void main(String[] args) {
		int i = 258;
		byte b = (byte) i;
		int z = b;
		System.out.println();
		String str = "abc231";
		byte[] bytes = str.getBytes();
		char c = byteToChar(bytes);
		System.out.println("--" + bytes.length);
		System.out.println("=================");
		char[] chars = getChars(bytes);
		System.out.println(chars.length);
		byte[] ss = getBytes(chars);
		System.out.println("=================" + ss.length);
		
		char ccc = 'c';
		byte[] charToByte = charToByte(ccc);
		System.out.println(charToByte.length);
		byte[] bytes1 = getBytes(new char[]{'c', '1'});
		System.out.println("=================");

	}


	public static char[] getChars(byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}

	public static byte[] getBytes(char[] chars) {
		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}


	public static byte[] charToByte(char c) {
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xFF00) >> 8);
		b[1] = (byte) (c & 0xFF);
		return b;
	}

	public static char byteToChar(byte[] b) {
		char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		return c;
	}
}
