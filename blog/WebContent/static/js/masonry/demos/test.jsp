<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	StringBuffer content = new StringBuffer();
	for(int i = 0; i < 15; i++){
		int colnum = new Random().nextInt(5);
		int imgnum = new Random().nextInt(12);
		content.append("<div class=\"box photo col"+colnum+"\">");
		content.append("<p><img src=\"/FastJavaProject/image/"+imgnum+".jpg\"></p>");
		content.append("</div>");
	}
	out.print(content.toString());
%>