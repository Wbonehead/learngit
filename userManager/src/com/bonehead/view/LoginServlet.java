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
		out.println("�û�����<input type='text' name='username'/><br/>");
		out.println("�ܡ��룺<input type='password' name = 'passwd'/><br/>");
		out.println("<input type='submit' value='�ύ'><input type='reset' value='����'>");
		out.println("</form>");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
