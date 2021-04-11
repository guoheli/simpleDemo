import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by zengxiaoning on 2021/4/10.
 */
public class SimpleByteBuf {

	public static void main(String[] args) {
		String tt = "nihao444444444444444444444444444444444444444444444444444444444444444444444444nihao444444444444444444444444444444444444444444444444444444444444444444444444nihao444444444444444444444444444444444444444444444444444444444444444444444444";
		byte[] str = tt.getBytes();
		System.out.println(tt.length());
		new SimpleByteBuf().send(str);
	}

	public byte[] send(byte[] sendData)  {

			// 定义一个发送消息协议格式：|--header:4 byte--|--content:10MB--|
		ByteBuffer byteBuffer = ByteBuffer.allocate(4).putInt(255);
		// 获取一个4字节长度的协议体头
			byte[] dataLength = byteBuffer.array(); //intToByteArray(4, sendData.length);
		int anInt = byteBuffer.getInt(0);
		// 和请求的数据组成一个请求数据包
			byte[] requestMessage = combineByteArray(dataLength, sendData);
			//发送数据-------------------------------
		ByteBuffer b = ByteBuffer.wrap(requestMessage);
		int anInt1 = b.getInt(0);
		byte[] array = b.array();
		;

		//接收数据-------------------------------
			// resultArray = IOUtils.toByteArray(is);

		return requestMessage;
	}
	private static byte[] intToByteArray(int byteLength, int intValue) {
		return ByteBuffer.allocate(byteLength).putInt(intValue).array();
	}
	private static byte[] combineByteArray(byte[] array1, byte[] array2) {
		byte[] combined = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, combined, 0, array1.length);
		System.arraycopy(array2, 0, combined, array1.length, array2.length);
		return combined;
	}


	public void tt() {
//		InputStream inputStream = new FileInputStream(new File("tcpradio.dat"));
//		OutputStream outputStream = socket.getOutputStream();
//		byte[] readBuf = new byte[10240];
//		int readLen;
//		readLen= inputStream.read(readBuf,0,10240);
//		System.out.println(readLen);
//		outputStream.write(readBuf,0,readLen);
	}


/*	switch (length) {
		case 1:
			frameLength = buf.getUnsignedByte(offset);
			break;
		case 2:
			frameLength = buf.getUnsignedShort(offset);
			break;
		case 3:
			frameLength = buf.getUnsignedMedium(offset);
			break;
		case 4:
			frameLength = buf.getUnsignedInt(offset);
			break;
		case 8:
			frameLength = buf.getLong(offset);
			break;
		default:*/


//	int length = byteBuf.readableBytes();
//	byte[] bytes = new byte[length];
//	byteBuf.readBytes(bytes);
//
//	Object obj = SerializingUtil.deserialize(bytes, typeClass);

	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

//	@Override
//	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
//		byte[] body = convertToBytes(msg);  //将对象转换为byte，伪代码，具体用什么进行序列化，你们自行选择。可以使用我上面说的一些
//		int dataLength = body.length;  //读取消息的长度
//		out.writeInt(dataLength);  //先将消息长度写入，也就是消息头
//		out.writeBytes(body);  //消息体中包含我们要发送的数据
//	}
//	————————————————


//	if (in.readableBytes() < HEAD_LENGTH) {  //这个HEAD_LENGTH是我们用于表示头长度的字节数。  由于上面我们传的是一个int类型的值，所以这里HEAD_LENGTH的值为4.
//		return;
//	}
//	in.markReaderIndex();                  //我们标记一下当前的readIndex的位置
//	int dataLength = in.readInt();       // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让他的readIndex增加4
//	if (dataLength < 0) { // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
//		ctx.close();
//	}
//
//	if (in.readableBytes() < dataLength) { //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
//		in.resetReaderIndex();
//		return;
//	}
//
//	byte[] body = new byte[dataLength];  //  嗯，这时候，我们读到的长度，满足我们的要求了，把传送过来的数据，取出来吧~~
//	in.readBytes(body);  //
//	————————————————
//	版权声明：本文为CSDN博主「luckystar2008」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//	原文链接：https://blog.csdn.net/qincidong/article/details/82656593
}
