package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html字符串处理类
 * 
 * @author cloud
 * */
public final class HtmlHandle {

	/** 转义尖括号等为字符 */
	public static String filter(String htmlContent) {
		if (htmlContent == null) {
			return "";
		}
		char content[] = new char[htmlContent.length()];
		htmlContent.getChars(0, htmlContent.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());
	}

	/** 将尖括号等字符转会尖括号等 */
	public static String filterTextToHTML(String htmlContent) {
		if (htmlContent == null) {
			return "";
		}
		htmlContent = htmlContent.replaceAll("&lt;", "<");
		htmlContent = htmlContent.replaceAll("&gt;", ">");
		htmlContent = htmlContent.replaceAll("&amp;", "&");
		htmlContent = htmlContent.replaceAll("&quot;", "\"");
		return (htmlContent);
	}

	/** 除去HTML代码 或 \f\n\r\t特殊字符 */
	public static String removeHTMLTag(String value) {
		String regEx = "<[^>]+>|</[^>]|[\f\n\r\t]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(value);
		return m.replaceAll("");
	}

	public static String transBlank(String content) {
		return content.replaceAll(" ", "&nbsp;");
	}

	public static String inDatabase(String str) {
		while (str.indexOf("\n") != -1) {
			str = str.substring(0, str.indexOf("\n")) + "<br>"
					+ str.substring(str.indexOf("\n") + 1);
		}
		while (str.indexOf(" ") != -1) {
			str = str.substring(0, str.indexOf(" ")) + "&nbsp;"
					+ str.substring(str.indexOf(" ") + 1);
		}
		return str;
	}

	public static String inTextarea(String str) {
		while (str.indexOf("<br>") != -1) {
			str = str.substring(0, str.indexOf("<br>")) + "\n"
					+ str.substring(str.indexOf("<br>") + 4);
		}
		while (str.indexOf("&nbsp;") != -1) {
			str = str.substring(0, str.indexOf("&nbsp;")) + " "
					+ str.substring(str.indexOf("&nbsp;") + 6);
		}
		return str;
	}

	public static String inJsp(String str) {
		while (str.indexOf("\r") != -1) {
			str = str.substring(0, str.indexOf("\r")) + ""
					+ str.substring(str.indexOf("\r") + 1);
		}
		return str;
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
	
}
