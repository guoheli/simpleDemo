/**
 * Created by zengxiaoning on 2021/4/10.
 */
public class SimpleString {

	//java中一个char型的数据（也就是一个字符）占两个字节。而Java中常用的字符包括数字、英文字母、英文符号、中文汉字、中文符号等，若在字符串中包含里面的多种字符，它们是否都占两个字符呢？答案是否定的。

	public static void main(String[] args) {

		System.out.println();
		String s1 = "1234567";// 7个数字字符
		byte[] b1 = s1.getBytes();
		System.out.println("7个数字字符1234567所占的字节数为:" + b1.length);
		String s2 = "abcdefg";// 7个英文字符
		byte[] b2 = s2.getBytes();
		System.out.println("7个英文字符abcdefg所占的字节数为:" + b2.length);
		String s3 = "::<>{}?";// 7个英文符号字符
		byte[] b3 = s3.getBytes();
		System.out.println("7个英文符号字符::<>{}?所占的字节数为:" + b3.length);
		String s4 = "钓鱼岛是中国的";// 7个中文汉字字符
		byte[] b4 = s4.getBytes();
		System.out.println("钓鱼岛是中国的所占的字节数为:" + b4.length);
		String s5 = "【】《》？：！";// 7个中文符号字符
		byte[] b5 = s5.getBytes();
		System.out.println("7个中文符号字符  【】《》？：！  所占的字节数为:" + b5.length);
		String s6 = "/n";
		byte[] b6 = s6.getBytes();
		System.out.println("/n所占的字节数为:" + b6.length);
	}
//	运行结果为：
//
//			7个数字字符1234567所占的字节数为:7
//			7个英文字符abcdefg所占的字节数为:7
//			7个英文符号字符::<>{}?所占的字节数为:7
//	钓鱼岛是中国的所占的字节数为:14
//			7个中文符号字符  【】《》？：！  所占的字节数为:14
//			/n所占的字节数为:2
}
