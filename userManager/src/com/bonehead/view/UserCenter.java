package com.bonehead.view;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCenter extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function goToPage(){var pageNow = document.getElementById('pageNow');window.open('/userManager/UserCenter?pageNow='+pageNow.value,'_self')}");
		out.println("</script>");
		out.println("<h1 align=center>�û��б�</h1>");
		Connection ct = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int pageCount = 1;//�ܹ�����ҳ
		int pageSize = 3;//ҳ���С
		int rowCount = 1;//�ܹ������м�¼
		int pageNow = 1;//��ǰ�ǵڼ�ҳ
		
		if(request.getSession().getAttribute("Cuser") == null){
			request.getRequestDispatcher("/Error").forward(request, response);
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","bonehead");
			ps = ct.prepareStatement("select count(ID) from users");
			rs = ps.executeQuery();
			if(rs.next())rowCount = rs.getInt(1);
			pageCount = (rowCount-1)/pageSize+1;
			if(request.getParameter("pageNow")==null){
				pageNow = 1;
			}else{
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
				if(pageNow < 1) pageNow = 1;
				if(pageNow > pageCount) pageNow = pageCount;
			}
			int min = (pageNow-1)*pageSize +1;
			int max = pageNow*pageSize;
			ps = ct.prepareStatement(" SELECT * FROM (SELECT A1.*,ROWNUM RN FROM (SELECT * FROM USERS) A1 WHERE ROWNUM <= ?)WHERE RN >= ?");
			ps.setInt(1, max);
			ps.setInt(2, min);
			System.out.print(min+"\t"+max);
			rs = ps.executeQuery();
			out.println("<table border=1 width=500px align=center>");
			out.println("<tr><td>ID</td><td>�û���</td><td>Emali</td><td>����</td></tr>");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td></tr>");
			}
			out.println("</table>");
			int prefix = pageNow-1;
			int profix = pageNow+1;
			out.println("<h5 align=center>"+"<a href='/userManager/UserCenter?pageNow="+prefix +"'>��һҳ</a>"+pageNow+"/"+pageCount+"ҳ"+"<a href='/userManager/UserCenter?pageNow="+profix+"'>��һҳ</a></h5>");
			out.println("<h5 align=center >�ܼ�"+rowCount+"����¼��Ϊ"+pageCount+"ҳ��ǰ��"+pageNow+"ҳ��ÿҳ��"+pageSize+"����¼||"+
					"<a href ='/userManager/UserCenter?pageNow=1'>��һҳ</a>"+
					"<a href='/userManager/UserCenter?pageNow="+prefix +"'>��һҳ </a>"+"||"+
					"<a href='/userManager/UserCenter?pageNow="+profix+"'>��һҳ </a>"+"||"+
					"<a href='/userManager/UserCenter?pageNow="+pageCount+"'>��ĩҳ</a></h5>");
			out.println("<h5 align=center>��Ҫ��ת<input type='text' name='pageNow' /> <input type='button' id='pageNow' onClick='goToPage()' value='go'></h5>");
			
			String rowId = request.getParameter("rowId");
			ps = ct.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
			if(rowId==null){
				ps.setInt(1, min);
			}else{
				ps.setInt(1, Integer.parseInt(rowId));

			}
				
			rs= ps.executeQuery();
			out.println("<h5 align=center>"+"ID || �û��� || Email || �û��ȼ�</h5><br/>");
			if(rs.next())
				out.println("<h5 align=center>"+rs.getInt(1)+" ## "+rs.getString(2)+" ## "+rs.getString(4)+" ## "+rs.getInt(5)+"<br/></h5>");
			out.println("<h5 align='center'>"+"<a href='/userManager/UserCenter?pageNow="+prefix+"'><<</a>");
			for(int i = min; i <= max ;i++){
				out.println("<a href='/userManager/UserCenter?rowId="+i+"'>"+i+"</a>");
			}
			out.println("<a href='/userManager/UserCenter?pageNow="+profix+"'>>></a></h5>");
		

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
