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
		// 解决从JSP页面接受的中文参数乱码
		request.setCharacterEncoding("UTF-8");
		// 接受从JSP页面传来的三个参数
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String categoryId = request.getParameter("category");
		// 数据源对象作为连接池的管理者。可以获得数据库的连接
		DataSource ds = null;
		// 建立DataSource对象获取数据源
		try {
			Context context = new InitialContext();
			// 使用MYSQL时
			// 通过在tomcat的context.xml文件中设定的数据源对象的名字，找到数据源对象
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysqlds");
			// 使用ORCALE数据库时
			// ds = (DataSource) context.lookup("java:/comp/env/jdbc/oracleds");
		} catch (NamingException e) {
			System.out.println("获取数据源出错");
		}
		int result = 0;
		try {
			// 注入SQL语句
			/*
			 * PreparedStatement pstmt = conn.prepareStatement(sql);
			 * pstmt.setString(1, title); pstmt.setString(2, content);
			 * pstmt.setInt(3, Integer.parseInt(categoryId)); // 执行SQL result =
			 * pstmt.executeUpdate(); System.out.println(result);
			 */
			// 使用DBUtil来进行数据库的操作
			// 获取链接。QueryRunner是DBUtils中的核心类，生成对象时传递数据源对象
			QueryRunner qr = new QueryRunner(ds);
			// 定义SQL语句
			// 使用MYSQL数据库时
			String sql = "insert into blog(title,content,category_id,createdtime) values (?,?,?,now())";
			// 使用ORCALE数据库时
			// String sql =
			// "insert into blog(id,title,content,category_id,create_time) values (blog_id.nextval,?,?,?,SYSDATE)";
			// 为？设定值
			String params[] = { title, content, categoryId };
			// 完成sql的运行
			result = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = "";
		if (result == 1) {
			message = "添加博文成功";
		} else {
			message = "添加博文失败";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("/addBlogRequest.jsp").forward(request,
				response);
	}

}
