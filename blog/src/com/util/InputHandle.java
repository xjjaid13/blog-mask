package com.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 输入信息的处理
 * */
public class InputHandle {

	public static InputHandle inputHandle = null;
	
	public synchronized static InputHandle getInstance(){
		if(inputHandle == null)
		{
			return new InputHandle();
		}
		else
		{
			return inputHandle;
		}
	}
	
	/**
	 * @param content
	 * @return
	 */
	public String[] handleContent(String content){
		return handleCount(content);
	}
	
	/**
	 * 按段落分页
	 * */
	public String[] handle(String content){
		return content.replaceAll("'", "''").split("\n");
	}
	
	/**
	 * 按字数分页
	 * */
	public String[] handleCount(String content){
		content = content.replaceAll("'", "''");
		int count = 200; 
		Pattern pattern = Pattern.compile("<p>([\\s\\S]*?)</p>");
		Matcher matcher = pattern.matcher(content);
		/** 记录字数 */
		int countTem = 0;
		/** 临时存放总文件 */
		ArrayList<String> array = new ArrayList<String>();
		/** 临时存放文件 */
		StringBuffer bufferTem = new StringBuffer();
		while(matcher.find()){
			String groupContent = matcher.group(1);
			countTem += groupContent.length();
			if(countTem > count){
				bufferTem.append("<p>\\n"+groupContent+"\\n</p>");
				array.add(bufferTem.toString());
				/** 临时记录清0 */
				bufferTem = bufferTem.delete(0, bufferTem.length());
				countTem = 0;
			}else{
				bufferTem.append("<p>\\n"+groupContent+"\\n</p>");
			}
		}
		String[] str = new String[array.size()];
		for(int i = 0; array != null && i < array.size(); i++){
			str[i] = array.get(i);
		}
		return str;
	}
	
}
