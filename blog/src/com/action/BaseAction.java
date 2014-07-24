package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.po.User;
import com.util.Constant;

public class BaseAction {

	public User returnUser(HttpSession session){
		return (User) session.getAttribute(Constant.USER);
	}
	
	public JSONObject createJosnObject(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		return jsonObject;
	}
	
	public JSONObject errorJsonObject(JSONObject jsonObject,String message){
		jsonObject.put("result", "error");
		jsonObject.put("message", message);
		return jsonObject;
	}
	
	public void writeResult(HttpServletResponse response,JSONObject jsonObject) throws IOException{
		response.getWriter().write(jsonObject.toString());
	}
	
}
