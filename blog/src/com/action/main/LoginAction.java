package com.action.main;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.action.BaseAction;
import com.po.User;
import com.service.UserMapperService;
import com.util.Constant;

/**
 * 登陆action
 * taylor 2014-7-23下午11:32:37
 */
@Controller
@RequestMapping("login")
public class LoginAction extends BaseAction{

	@Autowired
	UserMapperService userMapperService;
	
	@RequestMapping
	public String login(){
		return "login";
	}
	
	@RequestMapping("validLogin")
	public void validLogin(User user,HttpServletResponse response,HttpSession session) throws IOException{
		user = userMapperService.validUser(user);
		boolean result = false;
		if(user != null){
			result = true;
			session.setAttribute(Constant.USER, user);
		}
		JSONObject jsonObject = createJosnObject();
		if(!result){
			errorJsonObject(jsonObject, "账号或密码错误");
		}
		writeResult(response, jsonObject);
	}
	
	@RequestMapping("out")
	public String out(HttpSession session){
		session.removeAttribute(Constant.USER);
		return "login";
	}
	
}
