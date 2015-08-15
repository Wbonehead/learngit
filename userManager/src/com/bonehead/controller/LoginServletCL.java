package com.bonehead.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletCL extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user = request.getParameter("username");
		String pwd = request.getParameter("passwd");
		
		Connection ct = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","bonehead");
			ps = ct.prepareStatement("SELECT * FROM USERS WHERE username = ? AND passwd = ?");
			ps.setObject(1, user);
			ps.setObject(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()){
				//该用户是存在的，存在session里面
				request.getSession().setAttribute("Cuser", user);
				request.getRequestDispatcher("/MainFrame").forward(request, response);
			}else{
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(ct!=null)
						try {
							ct.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		}
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
