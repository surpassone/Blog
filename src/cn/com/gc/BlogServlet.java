package cn.com.gc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("category");
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(categoryId);
		
		DataSource ds=null;
		//����DataSource�����ȡ����Դ
		try {
			Context context=new InitialContext();
			ds= (DataSource)context.lookup("java:/comp/env/jdbc/mysqlds");
		} catch (NamingException e) {
            System.out.println("��ȡ����Դ����");
		} 
		try {
			//ʹ������Դ��������
			Connection conn = ds.getConnection();
			//����SQL���
			String sql="insert into blog(title,content,category_id,createdtime) values (?,?,?,now())";
			//ע��SQL���
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setInt(3,Integer.parseInt(categoryId));
			//ִ��SQL
			int result=pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e){
			e.printStackTrace();
		}  
	}
	
}
