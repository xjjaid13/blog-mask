package test.mainTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "http%3A%2F%2Fnews.baidu.com%2Fn%3Fcmd%3D4%26class%3Dsportnews%26tn%3Drss";
		System.out.println(URLDecoder.decode(s, "utf-8"));
	}
	
}
