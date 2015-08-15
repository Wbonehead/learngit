package com.bonehead.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrame extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("username");
		out.println("<h1>登录成功</h1>");
		out.println("<h2>欢迎"+user+"登录成功</h2>");
		out.println("<a href ='/userManager/UserCenter'>管理用户</a>");
		out.println("<a href =''>添加用户</a>");
		out.println("<a href =''>删除用户</a>");
		out.println("<a href =''>退出系统</a>");
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
