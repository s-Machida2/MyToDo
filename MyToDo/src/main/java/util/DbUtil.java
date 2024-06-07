package util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtil {

	public static Connection open() {

		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mariadb");
			con = ds.getConnection();
			System.out.println("接続成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//	Context initContext = new InitialContext();
	//	Context envContext = (Context)initContext.lookup("java:/comp/env");
	//	DataSource ds = (DataSource)envContext.lookup("jdbc/mariadb");
	//	con = ds.getConnection();

	public static void close(Connection con) {

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
