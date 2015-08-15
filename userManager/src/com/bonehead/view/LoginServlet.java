package com.bonehead.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<form action = '/userManager/LoginServletCL' method = 'post'>");
		out.println("用户名：<input type='text' name='username'/><br/>");
		out.println("密　码：<input type='password' name = 'passwd'/><br/>");
		out.println("<input type='submit' value='提交'><input type='reset' value='重置'>");
		out.println("</form>");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
