package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

//	private static DataSource ds;
//	static int counter = 0;
//	
//	public static Connection getConn() throws SQLException {
//		counter++;
//		System.out.println("Got conn " + counter);
//		return getInstance().getConnection();
//		
//	}
//
//	private static DataSource getInstance() {
//		if (ds == null) {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/crm");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//		return ds;
//	}
	
	public static Connection getConn() throws SQLException{
		Connection conn = null;
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm?useSSL=false&useUnicode=true&characterEncoding=UTF8","root", "coderslab");
			
		return conn;
	}
}
