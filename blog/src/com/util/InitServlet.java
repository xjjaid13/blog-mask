package com.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Repository;

import com.po.WebLinktype;

@Repository
public class InitServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 1L;
	
	//private static Logger log = Logger.getLogger(Init.class);
	
	public static Map<Integer,LinkedList<WebLinktype>> webLinkTypeMap = new HashMap<Integer, LinkedList<WebLinktype>>();
	
	public static Set<Integer> changeList = new HashSet<Integer>();

	public void init(ServletConfig servletConfig){
		
	}
}
