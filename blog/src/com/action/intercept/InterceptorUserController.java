package com.action.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//import com.po.User;

import com.po.User;
import com.util.Constant;

public class InterceptorUserController implements HandlerInterceptor{
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {

		User user = (User)request.getSession().getAttribute(Constant.USER);
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}
	
}
