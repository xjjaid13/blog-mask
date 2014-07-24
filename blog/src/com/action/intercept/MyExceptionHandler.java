package com.action.intercept;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class MyExceptionHandler implements HandlerExceptionResolver {  
    
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {
        try{
            if(isAjax(request)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "error");
                jsonObject.put("message", ex.getMessage());
                PrintWriter writer = response.getWriter();
                writer.write(jsonObject.toString());  
                writer.flush(); 
            }else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("message", ex.getMessage());
                return new ModelAndView("error", map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
    
}  