package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * taylor 2014-7-20下午11:11:24
 */
public class DataHandle {
	
	//log
	private static Logger log = Logger.getLogger(DataHandle.class);
	
	/**
	 * 判断一个对象是否为空
	 * @param object 传入对象
	 * @return
	 */
	public static boolean isNullOrEmpty(Object object){
		if(object == null){
			return true;
		}else if(object instanceof Collection){
			Collection<?> collection = (Collection<?>) object;
			if(collection != null && collection.size() > 0){
				return false;
			}else{
				return true;
			}
		}else if(object instanceof String){
			String string = (String) object;
			if("".equals(string) || string.length() == 0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取字段长度、全角算一个长度，半角算半个长度
	 * @param text 字段
	 */
	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/** 转换空值和去除空格 */
	public static String handleValue(String value){
		if(value == null || "null".equals(value)){
			return "";
		} 
		value = value.trim();
		return value;
	}
	
	/** 将字符串转化为整数 */
	public static int StringToInt(String value){
		if(value == null){
			return 0;
		}
		int returnValue = 0;
		try{
			returnValue = Integer.parseInt(value);
		}catch(Exception e){
			log.debug(e.getMessage());
			return 0;
		}
		return returnValue;
	}
	
	/** 将字符串转化为浮点 */
	public static float StringToFloat(String value){
		float returnValue = 0;
		try{
			returnValue = Float.parseFloat(value);
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		return returnValue;
	}
	
	/** 去除尾部最后一位 */
	public static String cutEnd(String value){
		value = value.substring(0, value.length()-1);
		return value;
	}
	
	/** 保留两位小数 */
	public static String decimalFloat(float value){
		DecimalFormat format = new DecimalFormat("#.00");
		String retuValue = format.format(value); 
		if(retuValue.startsWith(".")){
			retuValue = "0"+retuValue;
		}
		return retuValue;
	}
	
	/** 字符串替换 */
	public static String replaceString(String strData, String strFrom, String strTo)
	{
	    if ((strData == null) || (strData.trim().length() == 0)) {
	        return "";
	    }
	    if (strFrom.toLowerCase().trim().equals(strTo.toLowerCase().trim())) {
	        return strData;
	    }
	    if (strFrom.length() == 0) {
	        return strData;
	    }
	    int nIndex = strData.toUpperCase().indexOf(strFrom.toUpperCase());
	    String strResult = "";
	    while (nIndex != -1) {
	        strResult = strResult + strData.substring(0, nIndex) + strTo;
	        strData = strData.substring(nIndex + strFrom.length());
	        nIndex = strData.toUpperCase().indexOf(strFrom.toUpperCase());
	    }
	    strResult = strResult + strData;
	    return strResult;
	}
	
	/** 跨站脚本处理 */
	public static String handleXSS(String strContent)
	{
	    strContent = replaceString(strContent, "/**/", "");
	    strContent = replaceString(strContent, "<", "&#60;");
	    strContent = replaceString(strContent, ">", "&#62;");
	    strContent = replaceString(strContent, "%3C", "&#60;");
	    strContent = replaceString(strContent, "%3E", "&#62;");
	    strContent = replaceString(strContent, "expression", "ｅｘｐｒｅｓｓｉｏｎ");
	    strContent = replaceString(strContent, "javascript", "ｊａｖａｓｃｒｉｐｔ");
	    return strContent;
	}
	
	/** 处理sql注入 */
	public static String handleSql(String str){
	    if ((str == null) || (str.trim().length() == 0)){
	  	    return str;
	    }
	    str = replaceString(str, "'", "''");
	    str = replaceString(str, "%2B", "＋");
	    str = replaceString(str, "+", "＋");
	    str = replaceString(str, "DECLARE", "ＤＥＣＬＡＲＥ");
	    str = replaceString(str, "CAST", "ＣＡＳＴ");
	    str = replaceString(str, "EXEC", "ＥＸＥＣ");
	    return str;
	}
	
	/** 获得值 */
	public static String returnValue(HttpServletRequest request,String param){
		String value = request.getParameter(param);
		if(value == null){
			return "";
		}else{
//			value = handleXSS(value);
//			value = handleSql(value);
			return value;
		}
	}
	
	public static int returnValueInt(HttpServletRequest request,String param){
		String value = request.getParameter(param);
		if(value == null){
			return 0;
		}else{
			value = handleXSS(value);
			value = handleSql(value);
			return StringToInt(value);
		}
	}
	
	public static int returnValueInt(HttpServletRequest request,String param,int defaultValue){
		String value = request.getParameter(param);
		if(value == null || value.equals("undefined") || value.length() == 0){
			return defaultValue;
		}else{
			value = handleXSS(value);
			value = handleSql(value);
			return StringToInt(value);
		}
	}
	
	/** 
	 * 获得值，加默认 
	 **/
	public static String returnValue(HttpServletRequest request,String param,String defaultValue){
		String value = request.getParameter(param);
		if(value == null){
			return defaultValue;
		}else{
			value = handleXSS(value);
			value = handleSql(value);
			return value;
		}
	}
	
	/** 
	 * 得出相乘结果
	 **/
	public static String valuePlus(String str1,String...strn){
		if("".equals(str1)){
			str1 = "0";
		}
		BigDecimal b = new BigDecimal(str1);
		for(String str : strn){
			if("".equals(str)){
				continue;
			}
			BigDecimal c = new BigDecimal(str);
			b = b.multiply(c);
		}
		String s = b.setScale(2,2).toString();
		return s;
	}
	
	/** 得出相除结果*/
	public static String ValueDivide(String str1,String...strn){
		if("".equals(str1)){
			str1 = "0";
		}
		BigDecimal b = new BigDecimal(str1);
		for(String str : strn){
			if("".equals(str)){
				continue;
			}
			BigDecimal c = new BigDecimal(str);
			b = b.divide(c,2, BigDecimal.ROUND_DOWN);
		}
		String s = b.setScale(2,2).toString();
		return s;
	}
	
	/** 得出相加结果 */
	public static String ValueAdd(String str1,String...strn){
		if("".equals(str1)){
			str1 = "0";
		}
		BigDecimal b = new BigDecimal(str1);
		for(String str : strn){
			if("".equals(str)){
				continue;
			}
			BigDecimal c = new BigDecimal(str);
			b = b.add(c);
		}
		String s = b.setScale(2,2).toString();
		return s;
	}

	/** 得出相减结果 */
	public static String ValueMinus(String str1,String...strn){
		if("".equals(str1)){
			str1 = "0";
		}
		BigDecimal b = new BigDecimal(str1);
		for(String str : strn){
			if("".equals(str)){
				continue;
			}
			BigDecimal c = new BigDecimal(str);
			b = b.subtract(c);
		}
		String s = b.setScale(2,2).toString();
		return s;
	}
	
	public static String passwordGen(){
		String str = (int)(89999999*Math.random())+10000000 + "";
		return str;
	}
	
	/**
	 * 自动补全没有结尾符的地址
	 * */
	public static String autoFillPath(String path){
		if(path != null && !path.endsWith("/")){
			return path + "/";
		}
		return  path;
	}
	
	public String genrateRandomChar(int lon){
		if(lon < 0){return "";};
		String str = "";
		for(int i = 0; i < lon; i++){
			char ran = (char)('a' + new Random().nextInt(26));
			str += ran;
		}
		return str;
	}
	
	public String genrateRandomChar(){
		String str = "";
		for(int i = 0; i < 5; i++){
			char ran = (char)('a' + new Random().nextInt(26));
			str += ran;
		}
		return str;
	}
	
	/**
	 * UTF-8读取页面数据
	 * */
	public static String file_get_contents(String target_filename){
		BufferedReader bis = null;
		StringBuffer content = new StringBuffer();
		try{
		    URL url = new URL(target_filename);
		   
		    bis = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8"));
			int tempbyte;
			while((tempbyte = bis.read()) != -1){
				content.append((char)tempbyte);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		    try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	
	/**
	 * 获得session中用户id
	 * 暂时默认为系统管理员1
	 * */
	public static int returnUserId(HttpSession session){
		//String userid = (String) session.getAttribute("userid");
		return 1;
	}
	
}
