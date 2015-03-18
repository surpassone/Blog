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

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����JSPҳ����ܵ����Ĳ�������
		request.setCharacterEncoding("UTF-8");
		// ���ܴ�JSPҳ�洫������������
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String categoryId = request.getParameter("category");
		// ����Դ������Ϊ���ӳصĹ����ߡ����Ի�����ݿ������
		DataSource ds = null;
		// ����DataSource�����ȡ����Դ
		try {
			Context context = new InitialContext();
			// ʹ��MYSQLʱ
			// ͨ����tomcat��context.xml�ļ����趨������Դ��������֣��ҵ�����Դ����
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysqlds");
			// ʹ��ORCALE���ݿ�ʱ
			// ds = (DataSource) context.lookup("java:/comp/env/jdbc/oracleds");
		} catch (NamingException e) {
			System.out.println("��ȡ����Դ����");
		}
		int result = 0;
		try {
			// ע��SQL���
			/*
			 * PreparedStatement pstmt = conn.prepareStatement(sql);
			 * pstmt.setString(1, title); pstmt.setString(2, content);
			 * pstmt.setInt(3, Integer.parseInt(categoryId)); // ִ��SQL result =
			 * pstmt.executeUpdate(); System.out.println(result);
			 */
			// ʹ��DBUtil���������ݿ�Ĳ���
			// ��ȡ���ӡ�QueryRunner��DBUtils�еĺ����࣬���ɶ���ʱ��������Դ����
			QueryRunner qr = new QueryRunner(ds);
			// ����SQL���
			// ʹ��MYSQL���ݿ�ʱ
			String sql = "insert into blog(title,content,category_id,createdtime) values (?,?,?,now())";
			// ʹ��ORCALE���ݿ�ʱ
			// String sql =
			// "insert into blog(id,title,content,category_id,create_time) values (blog_id.nextval,?,?,?,SYSDATE)";
			// Ϊ���趨ֵ
			String params[] = { title, content, categoryId };
			// ���sql������
			result = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = "";
		if (result == 1) {
			message = "��Ӳ��ĳɹ�";
		} else {
			message = "��Ӳ���ʧ��";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("/addBlogRequest.jsp").forward(request,
				response);
	}

}
