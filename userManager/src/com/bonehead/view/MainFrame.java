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
		out.println("<h1>��¼�ɹ�</h1>");
		out.println("<h2>��ӭ"+user+"��¼�ɹ�</h2>");
		out.println("<a href ='/userManager/UserCenter'>�����û�</a>");
		out.println("<a href =''>����û�</a>");
		out.println("<a href =''>ɾ���û�</a>");
		out.println("<a href =''>�˳�ϵͳ</a>");
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
